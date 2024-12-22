package functions;

import java.util.Iterator;

public class MockTabulatedFunction extends AbstractTabulatedFunction implements Iterable<Point>{
    private final double x0;
    private final double x1;
    private double y0;
    private double y1;
    MockTabulatedFunction(double x0,double x1,double y0,double y1){
        this.x0=x0;
        this.x1=x1;
        this.y0=y0;
        this.y1=y1;
    }
    @Override
    public int getCount(){return 2;}
    @Override
    public double getX(int index){return (index==0)?this.x0:this.x1;}
    @Override
    public double getY(int index){return (index==0)?this.y0:this.y1;}
    @Override
    public void setY(int index,double value){
        if(index==0){this.y0=value;}
        else{this.y1=value;}
    }
    @Override
    public int indexOfX(double x){
        if(x!=x0 && x!=x1)return -1;
        return (x0==x)?0:1;
    }
    @Override
    public int indexOfY(double y){return (y0==y)?0:1;}
    @Override
    public double leftBound(){return x0;}
    @Override
    public double rightBound(){return x1;}
    @Override
    protected int floorIndexOfX(double x) {
        if (x == x1) return 1;
        return 0;
    }

    @Override
    protected double extrapolateLeft(double x) {
        return interpolate(x, x0, x1, y0, y1);
    }

    @Override
    protected double extrapolateRight(double x) {
        return interpolate(x, x0, x1, y0, y1);
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        return interpolate(x, getX(floorIndex), getX(floorIndex + 1), getY(floorIndex), getY(floorIndex + 1));
    }
    @Override
    public Iterator<Point> iterator(){
        return null;
    }

}
