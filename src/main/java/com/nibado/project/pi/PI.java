package com.nibado.project.pi;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class PI {
    private final String pi;
    private final List<String> results;

    public static PI of(List<BigDecimal> results) {
        return new PI(
                PIService.average(results).toString(),
                results.stream().map(BigDecimal::toString).collect(Collectors.toList()));
    }
}
