package operations;

import concurrent.SynchronizedTabulatedFunction;
import functions.Point;
import functions.TabulatedFunction;
import functions.factory.ArrayTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;

public class TabulatedDifferentialOperator implements DifferentialOperator<TabulatedFunction>{
    TabulatedFunctionFactory factory;
    public TabulatedDifferentialOperator(TabulatedFunctionFactory fac){
        this.factory=fac;
    }
    public TabulatedDifferentialOperator(){
        this.factory=new ArrayTabulatedFunctionFactory();
    }

    public TabulatedFunctionFactory getFactory() {
        return factory;
    }

    public void setFactory(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    @Override
    public TabulatedFunction derive(TabulatedFunction func){
        int count=func.getCount();
        Point[] p=TabulatedFunctionOperationService.asPoints(func);
        double[] xValues=new double[count];
        double[] yValues=new double[count];
        for(int i=0;i<count-1;i++){
            xValues[i]=p[i].x;
            yValues[i]=p[i+1].y/(p[i+1].x-p[i].x)-p[i].y/(p[i+1].x-p[i].x);
        }
        xValues[count-1]=p[count-1].x;
        yValues[count-1]=yValues[count-2];
        return factory.create(xValues,yValues);
    }
    public TabulatedFunction deriveSynchronously(TabulatedFunction func){
        boolean isSynchronizedTabulatedFunction = func instanceof SynchronizedTabulatedFunction;
        SynchronizedTabulatedFunction.Operation<TabulatedFunction> synchronizedAnon = new SynchronizedTabulatedFunction.Operation<>() {
            @Override
            public TabulatedFunction apply(SynchronizedTabulatedFunction synchronizedTabulatedFunction) {
                return derive(synchronizedTabulatedFunction);
            }
        };
        if(!isSynchronizedTabulatedFunction){
            SynchronizedTabulatedFunction synchronizedTabulatedFunction = new SynchronizedTabulatedFunction(func);
            return synchronizedTabulatedFunction.doSynchronously(synchronizedAnon);
        }
        else {
            return ((SynchronizedTabulatedFunction) func).doSynchronously(synchronizedAnon);
        }





    }
}
