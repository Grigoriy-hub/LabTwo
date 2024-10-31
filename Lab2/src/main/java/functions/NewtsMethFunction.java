package functions;

public class NewtsMethFunction implements MathFunction
{
    private final  MathFunction in_func;
    private final  double min_error;
    public NewtsMethFunction(MathFunction func,double error)
    {
     this.in_func=func;
     this.min_error=error;
    }
    private double inaccDeriv(double x)
    {
        return in_func.apply(x+EPSILON)/2.0/EPSILON-in_func.apply(x-EPSILON)/2.0/EPSILON;

    }

    @Override
    public double apply(double x){
        double x_old;
        do{
            x_old =x;
            double y=in_func.apply(x);
            double y_deriv=inaccDeriv(x);
            if (Math.abs(y_deriv)<EPSILON)return Double.NaN;
            x=x_old-y/y_deriv;
        }while(Math.abs(x_old-x)>min_error);
        return x;

    }
}
