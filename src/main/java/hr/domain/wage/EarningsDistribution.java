package hr.domain.wage;

import lombok.Value;

import java.util.List;

@Value
public class EarningsDistribution {
    private double min;
    private double max;
    private int[] distribution;
}
