package functions;



public interface MathFunction {
    double EPSILON=1e-9;
    double apply(double x);
    default long HashName() {
        return this.Name().hashCode();
    }
    default String Name() {
        return this.getClass().getSimpleName();
    }
    default CompositeFunction andThen(MathFunction afterFunction){
        return new CompositeFunction(afterFunction, this);
    }
}
