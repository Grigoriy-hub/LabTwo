package functions;

import exceptions.ArrayIsNotSortedException;
import exceptions.DifferentLengthOfArraysException;

public abstract class AbstractTabulatedFunction implements TabulatedFunction {
    protected int count = 0;
    @Override
    public String toString(){
        StringBuilder strokostroitel=new StringBuilder();
        strokostroitel.append(getClass().getSimpleName()).append(" size = ").append(this.getCount()).append('\n');
        for(Point p: this){
            strokostroitel.append('[').append(p.x).append("; ").append(p.y).append(']').append('\n');
        }
        strokostroitel.deleteCharAt(strokostroitel.length()-1);
        return strokostroitel.toString();
    }
    protected abstract int floorIndexOfX(double x);

    protected abstract double extrapolateRight(double x);

    protected abstract double extrapolateLeft(double x);

    protected abstract double interpolate(double x, int floorIndex);

    protected double interpolate(double x, double leftX, double rightX, double leftY, double rightY) {
        if (rightX == leftX) return leftY;
        return leftY + (rightY - leftY) / (rightX - leftX) * (x - leftX);
    }

    @Override
    public double apply(double x) {
        if (x < this.leftBound()) return extrapolateLeft(x);
        else if (x > this.rightBound()) return extrapolateRight(x);
        else if (indexOfX(x) == -1) return interpolate(x, floorIndexOfX(x));
        return getY(indexOfX(x));
    }

    public static void checkLengthIsTheSame(double[] xValues, double[] yValues) throws DifferentLengthOfArraysException {
        if (xValues.length != yValues.length) {
            throw new DifferentLengthOfArraysException();
        }
    }


    public static void checkSorted(double[] xValues) throws ArrayIsNotSortedException {
        for (int i = 0; i <= (xValues.length - 2); i++) {
            if (xValues[i] >= xValues[i + 1]) {
                throw new ArrayIsNotSortedException();
            }
        }
    }
}
