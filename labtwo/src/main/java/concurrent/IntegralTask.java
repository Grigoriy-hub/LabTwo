package concurrent;

import functions.MathFunction;

import java.util.concurrent.Callable;

public class IntegralTask implements Callable<Double> {
    private final double lowerBound;
    private final double upperBound;
    private final MathFunction intFunc;
    private int sections = 10000;

    public IntegralTask(MathFunction func, double lower, double upper, int secs) {
        if (secs <= 0) throw new IllegalArgumentException();
        if (lower > upper) {
            double temp = lower;
            lower = upper;
            upper = temp;
        }
        this.lowerBound = lower;
        this.upperBound = upper;
        this.intFunc = func;
        this.sections = secs;
    }

    public IntegralTask(MathFunction func, double lower, double upper) {
        if (lower > upper) {
            double temp = lower;
            lower = upper;
            upper = temp;
        }
        this.lowerBound = lower;
        this.upperBound = upper;
        this.intFunc = func;
    }

    private double TrapezoidMethod(int sections) {
        double totalArea = 0.0;
        double sectionWidth = (upperBound - lowerBound) / sections;
        for (int i = 0; i < sections; i++) {
            double x0 = lowerBound + i * sectionWidth;
            double x1 = lowerBound + (i + 1) * sectionWidth;
            double area = (intFunc.apply(x0) + intFunc.apply(x1)) * sectionWidth / 2;
            totalArea += area;
        }
        return totalArea;
    }

    @Override
    public Double call() {
        return TrapezoidMethod(sections);
    }
}
