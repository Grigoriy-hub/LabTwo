package operations;

import functions.MathFunction;

public class LeftSteppingDifferentialOperator extends SteppingDifferentialOperator {

    public LeftSteppingDifferentialOperator(double step) throws IllegalArgumentException {
        super(step);
    }

    @Override
    public MathFunction derive(MathFunction function) {

        MathFunction derivative = new MathFunction() {
            @Override
            public double apply(double x){
                return ((function.apply(x)-function.apply(x-step))/step);
            }
        };
        return derivative;
    }
}
