package functions;

import java.util.NoSuchElementException;
import java.util.Iterator;

public class StrictTabulatedFunction implements TabulatedFunction{
    private TabulatedFunction strict_func;
    public StrictTabulatedFunction(TabulatedFunction func){
        this.strict_func=func;
    }
    @Override
    public int getCount() {
        return strict_func.getCount();
    }
    @Override
    public double getX(int index){
        return strict_func.getX(index);
    }
    @Override
    public double getY(int index){
        return strict_func.getY(index);
    }
    @Override
    public void setY(int index,double arg){
        strict_func.setY(index,arg);
    }
    @Override
    public int indexOfX(double arg){
        return strict_func.indexOfX(arg);
    }
    @Override
    public int indexOfY(double arg){
        return strict_func.indexOfY(arg);
    }
    @Override
    public double leftBound(){
        return strict_func.leftBound();
    }
    @Override
    public double rightBound(){
        return strict_func.rightBound();
    }
    @Override
    public double apply(double x){
        int indexOfX=strict_func.indexOfX(x);
        if(indexOfX==-1)throw new NoSuchElementException();
        return strict_func.getY(indexOfX);
    }
    @Override
    public Iterator<Point> iterator(){
        return strict_func.iterator();
    }
}
