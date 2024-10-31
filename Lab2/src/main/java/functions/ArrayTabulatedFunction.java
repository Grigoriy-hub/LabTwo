package functions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import exceptions.ArrayIsNotSortedException;
import exceptions.DifferentLengthOfArraysException;
import exceptions.InterpolationException;
import org.apache.commons.lang3.ArrayUtils;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import com.fasterxml.jackson.annotation.JsonFormat;


public class ArrayTabulatedFunction extends AbstractTabulatedFunction implements Insertable, Removable, Serializable {
    @Serial
    private static final long serialVersionUID = 5812983799324394135L;
    @JsonFormat(shape=JsonFormat.Shape.ARRAY)
    protected double[] xValues;
    @JsonFormat(shape=JsonFormat.Shape.ARRAY)
    protected double[] yValues;
    protected int count;

    @JsonCreator
    public ArrayTabulatedFunction(@JsonProperty(value="xValues") double[] xValues,@JsonProperty(value="yValues") double[] yValues) throws DifferentLengthOfArraysException, ArrayIsNotSortedException {
        if(xValues.length<2)throw new IllegalArgumentException("Construction of a Tabulated function requires at least 2 points");
        checkLengthIsTheSame(xValues, yValues);
        checkSorted(xValues);
        this.xValues = Arrays.copyOf(xValues, xValues.length);
        this.yValues =Arrays.copyOf(yValues, yValues.length);
        this.count = xValues.length;
    }

    public ArrayTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if(count<2)throw new IllegalArgumentException("Construction of a Tabulated function requires at least 2 points");
        this.count = count;
        if (xFrom > xTo) {
            double temp = xFrom;
            xFrom = xTo;
            xTo = temp;
        }
        xValues = new double[this.count];
        yValues = new double[this.count];
        if (xFrom == xTo) {
            double help = source.apply(xFrom);
            for (int i = 0; i < (count); i++) {
                xValues[i] = xFrom;
                yValues[i] = help;
            }
        } else {
            double step = (xTo - xFrom) / (count - 1);
            for (int i = 0; i < count; i++) {
                xValues[i] = xFrom + i * step;
                yValues[i] = source.apply(xValues[i]);
            }
        }
    }

    public int getCount() {
        return count;
    }

    @Override
    public double getX(int index) {
        return xValues[index];
    }

    @Override
    public double getY(int index) {
        return yValues[index];
    }

    @Override
    public void setY(int index, double value) {
        yValues[index] = value;
    }

    @Override
    public double leftBound() {
        return xValues[0];
    }

    @Override
    public double rightBound() {
        return xValues[count - 1];
    }

    @Override
    public int indexOfX(double x) {
        for (int i = 0; i <= (count - 1); i++) {
            if (xValues[i] == x) {
                return i;
            }
        }
        return -1;
    }

    public int indexOfY(double y) {
        for (int i = 0; i <= (count - 1); i++) {
            if (yValues[i] == y) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected int floorIndexOfX(double x) {
        for (int i = 0; i < (count - 1); i++) {
            if ((x > xValues[i]) && (x < xValues[i + 1])) {
                return i;
            }
        }
        if (x < xValues[0]) {
            return 0;
        }

        return count;
    }

    @Override
    protected double extrapolateRight(double x) {
        if (count == 1) {
            return yValues[0];
        }
        return (yValues[count - 2] + ((yValues[count - 1] - yValues[count - 2]) / (xValues[count - 1] - xValues[count - 2])) * (x - xValues[count - 2]));
    }

    @Override
    protected double extrapolateLeft(double x) {
        if (count == 1) {
            return yValues[0];
        }
        return (yValues[0] + ((yValues[1] - yValues[0]) / (xValues[1] - xValues[0])) * (x - xValues[0]));
    }

    @Override
    protected double interpolate(double x, int floorIndex) throws InterpolationException {
        if (floorIndex >= (count - 1)) {
            throw new InterpolationException();
        }
        for (int i = 0; i <= (count - 1); i++) {
            if (x == xValues[i]) {
                throw new InterpolationException();
            }
        }
        if (!(x > xValues[floorIndex]) && (x < xValues[floorIndex + 1])) {
            throw new InterpolationException();
        }

        if (count == 1) {
            return yValues[0];
        }
        return (yValues[floorIndex] + ((yValues[floorIndex + 1] - yValues[floorIndex]) / (xValues[floorIndex + 1] - xValues[floorIndex])) * (x - xValues[floorIndex]));
    }

    @Override
    public void insert(double x, double y) {
        int index = indexOfX(x);
        if (index == -1) {
            int before;
            int after;
            double[] nu_arrX = new double[count + 1];
            double[] nu_arrY = new double[count + 1];
            if (x < leftBound()) {
                before = 0;
            } else if (x > rightBound()) {
                before = count;
            } else {
                before = floorIndexOfX(x) + 1;
            }
            nu_arrX[before] = x;
            nu_arrY[before] = y;
            for (int i = 0; i < before; i++) {
                nu_arrX[i] = this.xValues[i];
                nu_arrY[i] = this.yValues[i];
            }
            for (int i = before; i < count; i++) {
                nu_arrX[i + 1] = this.xValues[i];
                nu_arrY[i + 1] = this.yValues[i];
            }
            count++;
            this.xValues = nu_arrX;
            this.yValues = nu_arrY;
        } else {
            this.yValues[index] = y;
        }
    }

    public void remove(int index) {
        if(this.count==2)throw new UnsupportedOperationException("Cannot remove from a function with only two elements");
        this.xValues = ArrayUtils.remove(this.xValues, index);
        this.yValues = ArrayUtils.remove(this.yValues, index);
    }

    @Override
    public Iterator<Point> iterator() throws NoSuchElementException {

        Iterator<Point> Iterator1 = new Iterator<>(){

            private int i = 0;

            public boolean hasNext() {
                if(i < count){
                    return true;
                } else {
                    return false;
                }
            }

            public Point next() throws NoSuchElementException {
                if(hasNext()){
                    i++;
                    return new Point(xValues[i-1], yValues[i-1]);
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
        return Iterator1;
    }
}
