package functions;

public class CompositeFunction implements MathFunction {
    private final MathFunction firstFunction;
    private final MathFunction secondFunction;
    public CompositeFunction(MathFunction f1,MathFunction f2){
        this.firstFunction=f1;
        this.secondFunction=f2;
    }
    @Override
    public double apply(double x)
    {
        return secondFunction.apply(firstFunction.apply(x));
    }
}
