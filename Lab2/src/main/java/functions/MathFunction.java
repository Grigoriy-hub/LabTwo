package functions;



public interface MathFunction {
    double EPSILON=1e-9;
    double apply(double x);
    default CompositeFunction andThen(MathFunction afterFunction){
        return new CompositeFunction(afterFunction, this);
    }
}
