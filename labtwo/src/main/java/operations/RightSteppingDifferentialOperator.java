package operations;

import functions.MathFunction;

public class RightSteppingDifferentialOperator  extends SteppingDifferentialOperator {

        public RightSteppingDifferentialOperator(double step) throws IllegalArgumentException {
            super(step);
        }

        @Override
        public MathFunction derive(MathFunction function) {

            MathFunction derivative = new MathFunction() {
                @Override
                public double apply(double x){
                    return ((function.apply(x + step)-function.apply(x))/step);
                }
            };
            return derivative;
        }
    }

