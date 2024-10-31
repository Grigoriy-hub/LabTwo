package concurrent;

import functions.Point;
import functions.TabulatedFunction;

import java.util.Iterator;
import java.util.NoSuchElementException;
import operations.TabulatedFunctionOperationService;

public class SynchronizedTabulatedFunction implements TabulatedFunction {
    private final TabulatedFunction synchFunc;
    public SynchronizedTabulatedFunction(TabulatedFunction func){
        this.synchFunc=func;
    }
    @Override
    public int getCount(){
        synchronized (synchFunc){
            return this.synchFunc.getCount();
        }
    }
    @Override
    public double getX(int index){
        synchronized (synchFunc){
            return this.synchFunc.getX(index);
        }
    }
    @Override
    public double getY(int index){
        synchronized (synchFunc){
            return this.synchFunc.getY(index);
        }
    }
    @Override
    public void setY(int index,double y){
        synchronized (synchFunc){
            synchFunc.setY(index,y);
        }
    }
    @Override
    public int indexOfX(double x){
        synchronized (synchFunc){
            return this.synchFunc.indexOfX(x);
        }
    }
    @Override
    public int indexOfY(double y){
        synchronized (synchFunc){
            return this.synchFunc.indexOfY(y);
        }
    }
    @Override
    public double leftBound(){
        synchronized (synchFunc){
            return this.synchFunc.leftBound();
        }
    }
    @Override
    public double rightBound(){
        synchronized (synchFunc){
            return this.synchFunc.leftBound();
        }
    }
    @Override
    public double apply(double x){
        synchronized (synchFunc){
            return this.synchFunc.apply(x);
        }
    }
    @Override
    public Iterator<Point> iterator(){
        synchronized (synchFunc){
            Point[] p= TabulatedFunctionOperationService.asPoints(synchFunc);
            return new Iterator<Point>() {
                private int i;
                @Override
                public boolean hasNext() {
                    return i<p.length;
                }

                @Override
                public Point next() {
                    if(!hasNext()) throw new NoSuchElementException();
                    Point point = new Point(p[i].x,p[i].y);
                    ++i;
                    return point;
                }
            };
        }
    }

}
