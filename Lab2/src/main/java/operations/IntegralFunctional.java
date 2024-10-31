package operations;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.*;
import java.util.List;

import concurrent.IntegralTask;
import functions.MathFunction;
import functions.TabulatedFunction;

public class IntegralFunctional {
    private final int numberOfThreads;
    public IntegralFunctional(){
        this.numberOfThreads=Runtime.getRuntime().availableProcessors()-1;
    }
    public IntegralFunctional(int numThreads){
        if (numThreads<=0) throw new IllegalArgumentException();
        this.numberOfThreads=numThreads;
    }
    public double integrate(MathFunction function,double lowerBound,double upperBound) throws ExecutionException, InterruptedException{
        double integral=0;
        try(ExecutorService exec = Executors.newFixedThreadPool(numberOfThreads)) {
            double delta = (upperBound - lowerBound) / numberOfThreads;
            List<Future<Double>> futureList = new ArrayList<>();
            for (int i = 0; i < numberOfThreads; i++) {
                double lower = lowerBound + i * delta;
                double upper = lower + delta;
                IntegralTask task = new IntegralTask(function, lower, upper);
                futureList.add(exec.submit(task));
            }
            for (Future<Double> point : futureList) {
                integral += point.get();
            }
        }
        return integral;
    }
    public double integrate(TabulatedFunction function) throws ExecutionException, InterruptedException{
        return this.integrate(function,function.leftBound(),function.rightBound());
    }
}
