package operations;

import exceptions.InconsistentFunctionsException;
import functions.Point;
import functions.TabulatedFunction;
import functions.factory.ArrayTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;

import java.util.Iterator;

public class TabulatedFunctionOperationService {
    TabulatedFunctionFactory factory;
    public TabulatedFunctionOperationService(TabulatedFunctionFactory factory){
        this.factory = factory;
    }
    public TabulatedFunctionOperationService(){
        this.factory = new ArrayTabulatedFunctionFactory();
    }

    public TabulatedFunctionFactory getFactory() {
        return factory;
    }

    public void setFactory(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public static Point[] asPoints(TabulatedFunction tabulatedFunction){
        Point[] arrayP = new Point[tabulatedFunction.getCount()];
        Iterator<Point> iterator = tabulatedFunction.iterator();
        int i = 0;
        for (Point point : arrayP) {
            point = iterator.next();
            arrayP[i] = point;
            i++;
        }
        return arrayP;
    }
    private interface BiOperation{
        double apply(double u, double v);
    }

    BiOperation Plus = (u, v) -> u + v;

    BiOperation Minus = (u, v) -> u - v;

    private TabulatedFunction doOperation(TabulatedFunction a, TabulatedFunction b, BiOperation operation){
        if(a.getCount() != b.getCount()){
            throw new InconsistentFunctionsException();
        }
        Point[] array1 = asPoints(a);
        Point[] array2 = asPoints(b);
        double[] xValues = new double[a.getCount()];
        double[] yValues = new double[a.getCount()];
        for (int i = 0; i < a.getCount(); i++){
            if(array1[i].x != array2[i].x){
                throw new InconsistentFunctionsException();
            }
            xValues[i] = array1[i].x;
            yValues[i] = operation.apply(array1[i].y, array2[i].y);
        }
        return factory.create(xValues, yValues);
    }
    public TabulatedFunction PlusFunction(TabulatedFunction tabulatedFunction1, TabulatedFunction tabulatedFunction2){
        return doOperation(tabulatedFunction1, tabulatedFunction2, Plus);
    }
    public TabulatedFunction MinusFunction(TabulatedFunction tabulatedFunction1, TabulatedFunction tabulatedFunction2){
        return doOperation(tabulatedFunction1, tabulatedFunction2, Minus);
    }
    public TabulatedFunction multiply (TabulatedFunction a,TabulatedFunction b){
        return doOperation(a,b,(u,v)->u*v);
    }
    public TabulatedFunction divide (TabulatedFunction a,TabulatedFunction b){
        return doOperation(a,b,(u,v)->u/v);
    }
}

