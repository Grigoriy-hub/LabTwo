package operations;

import functions.MathFunction;

import static java.lang.Double.*;

public abstract class SteppingDifferentialOperator implements DifferentialOperator<MathFunction> {
    protected double step;
    public SteppingDifferentialOperator(double step) throws IllegalArgumentException{
        if(step <= 0){
            throw new IllegalArgumentException();
        }
        if(step == POSITIVE_INFINITY){
            throw new IllegalArgumentException();
        }
        if(Double.isNaN(step)){
            throw new IllegalArgumentException();
        }
        this.step = step;
    }

    public double getStep() {
        return step;
    }

    public void setStep(double step) {
        this.step = step;
    }


}
