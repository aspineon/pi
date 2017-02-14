package com.nibado.project.pi;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static java.math.BigDecimal.ROUND_HALF_UP;
import static java.math.BigDecimal.ZERO;

@Service
public class PIService {
    private static final int SCALE = 100;
    private static final int POOL_SIZE = 2;
    private ExecutorService executorService = Executors.newFixedThreadPool(POOL_SIZE);
    private List<PIWorker> workers = new ArrayList<>();

    public void start() {
        if (workers.size() == POOL_SIZE) {
            return;
        }
        PIWorker worker = new PIWorker();
        workers.add(worker);
        executorService.submit(worker);
    }

    public void stop() {
        for (PIWorker worker : workers) {
            worker.stop();
        }
        workers.clear();
    }

    public void stop(final int index) {
        PIWorker worker = workers.remove(index);
        worker.stop();
    }

    public List<BigDecimal> results() {
        return workers.stream().map(PIWorker::pi).collect(Collectors.toList());
    }

    public static BigDecimal average(List<BigDecimal> results) {
        if (results.isEmpty()) {
            return ZERO;
        }
        return results.stream()
                .reduce(ZERO, BigDecimal::add)
                .divide(new BigDecimal(results.size()), SCALE, ROUND_HALF_UP);
    }

    public static class PIWorker implements Runnable {
        private static final BigDecimal FOUR = new BigDecimal(4);
        private final AtomicInteger iterations = new AtomicInteger();
        private final AtomicInteger inside = new AtomicInteger();
        private final AtomicBoolean cont = new AtomicBoolean();

        @Override
        public void run() {
            cont.set(true);

            while (cont.get()) {
                iterations.incrementAndGet();
                double x = Math.random() * 2 - 1;
                double y = Math.random() * 2 - 1;

                if (x * x + y * y < 1) {
                    inside.incrementAndGet();
                }
            }
        }

        public void stop() {
            cont.set(false);
        }

        public BigDecimal pi() {
            return FOUR.multiply(new BigDecimal(inside.get())).divide(new BigDecimal(iterations.get()), SCALE, ROUND_HALF_UP);
        }
    }
}
