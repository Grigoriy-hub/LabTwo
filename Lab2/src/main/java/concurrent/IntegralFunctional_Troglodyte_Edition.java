package concurrent;
import functions.TabulatedFunction;

public class IntegralFunctional_Troglodyte_Edition {
    private  volatile double integral=0;

    private class TrapezoidMethod implements Runnable{
        private double totalArea=0;
        private final TabulatedFunction func;
        private final double lowerBound;
        private final double upperBound;
        private final int sections;
        public TrapezoidMethod(TabulatedFunction func,double lowerBound,double upperBound,int sections){
            this.sections=sections;
            this.func=func;
            this.lowerBound=lowerBound;
            this.upperBound=upperBound;
        }
        @Override
        public void run(){
            double sectionWidth =(upperBound-lowerBound)/sections;
            for(int i=0;i<sections;i++){
                double x0=lowerBound+i* sectionWidth;
                double x1=lowerBound+(i+1)* sectionWidth;
                double area =(func.apply(x0)+func.apply(x1))*sectionWidth/2;
                totalArea+=area;
            }
            synchronized (this) {
                integral += totalArea;
            }

        }
    }
    public double integrate(TabulatedFunction func,double lowerBound,double upperBound,int sections,int numberOfThreads) throws InterruptedException{
        if(lowerBound>=upperBound) throw new IllegalArgumentException();
        if(sections<=0)throw new IllegalArgumentException();
        Thread[] threads=new Thread[numberOfThreads];
        double deltaX=(upperBound-lowerBound)/numberOfThreads;
        for(int i=0;i<numberOfThreads;i++){
            double lower=lowerBound+i*deltaX;
            double upper=lower+deltaX;
            threads[i]=new Thread(new TrapezoidMethod(func,lower,upper,sections/numberOfThreads));
            threads[i].start();
        }
        for(int i=0;i<numberOfThreads;i++){
            threads[i].join();
        }
        return integral;
    }
}
