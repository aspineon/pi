package com.nibado.project.pi;


import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class PIServiceTest {
    @Test
    public void testRun() throws Exception {
        PIService service  = new PIService();
        service.start();
        service.start();

        Thread.sleep(2000);

        List<BigDecimal> results = service.results();
        service.stop();

        System.out.println(results);
    }
}
