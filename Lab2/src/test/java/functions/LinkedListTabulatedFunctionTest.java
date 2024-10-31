package functions;

import exceptions.InterpolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTabulatedFunctionTest {
    private LinkedListTabulatedFunction t_func1;
    private LinkedListTabulatedFunction t_func2;
    private final MathFunction exp=new MathFunction() {
        @Override
        public double apply(double arg) {
            return Math.exp(arg);
        }
    };
    @BeforeEach
    void t_constructor() {
        t_func1 = new LinkedListTabulatedFunction(new double[]{1.0, 2.5, 4.0, 5.0},new double[]{4.0, 5.0, 6.0, 2.0});
        t_func2 = new LinkedListTabulatedFunction(exp, 2.0, 4.0, 4);
    }
    @Test
    void constructorIllegalArgumentExceptionThrow_ExpectThrow_TwoParamConstructor(){
        assertThrows(IllegalArgumentException.class,()->{LinkedListTabulatedFunction t_func=new LinkedListTabulatedFunction(new double[]{0},new double[]{0});});
    }
    @Test
    void constructorIllegalArgumentExceptionThrow_ExpectThrow_FourParamConstructor(){
        assertThrows(IllegalArgumentException.class,()->{LinkedListTabulatedFunction t_func=new LinkedListTabulatedFunction(exp,0,1,1);});
    }
    @Test
    void getCount_ExpectEqual_4countFunc(){
        assertEquals(4,t_func1.getCount());
        assertEquals(4,t_func2.getCount());
    }
    @Test
    void getXIllegalArgumentException_ExpectThrown_IndexLessThanZero(){
        assertThrows(IllegalArgumentException.class,()->t_func1.getX(-1));
        assertThrows(IllegalArgumentException.class,()->t_func2.getX(-1));
    }
    @Test
    void getXIllegalArgumentException_ExpectThrown_IndexGreaterThanCount(){
        assertThrows(IllegalArgumentException.class,()->t_func1.getX(4));
        assertThrows(IllegalArgumentException.class,()->t_func2.getX(4));
    }
    @Test
    void getX_ExpectFirstElem_4ElemFunc(){
        assertEquals(1.0,t_func1.getX(0));
        assertEquals(2.0,t_func2.getX(0));
    }
    @Test
    void getYIllegalArgumentException_ExpectThrown_IndexLessThanZero(){
        assertThrows(IllegalArgumentException.class,()->t_func1.getY(-1));
        assertThrows(IllegalArgumentException.class,()->t_func2.getY(-1));
    }
    @Test
    void getYIllegalArgumentException_ExpectThrown_IndexGreaterThanCount(){
        assertThrows(IllegalArgumentException.class,()->t_func1.getY(4));
        assertThrows(IllegalArgumentException.class,()->t_func2.getY(4));
    }
    @Test
    void getY_ExpectFirstElem_4ElemFunc(){
        assertEquals(4.0,t_func1.getY(0));
        assertEquals(exp.apply(2.0),t_func2.getY(0),0.00001);
    }
    @Test
    void setYIllegalArgumentException_ExpectThrown_IndexLessThanZero(){
        assertThrows(IllegalArgumentException.class,()->t_func1.setY(-1,0));
        assertThrows(IllegalArgumentException.class,()->t_func2.setY(-1,0));
    }
    @Test
    void setYIllegalArgumentException_ExpectThrown_IndexGreaterThanCount(){
        assertThrows(IllegalArgumentException.class,()->t_func1.setY(4,0));
        assertThrows(IllegalArgumentException.class,()->t_func2.setY(4,0));
    }
    @Test
    void setY_ExpectUnequal_SettingFirstElem(){
        double oldYTfunc1=t_func1.getY(0);
        double oldYTfunc2=t_func2.getY(0);
        t_func1.setY(0,2.0);
        t_func2.setY(0,3.0);
        assertTrue(oldYTfunc1!=t_func1.getY(0));
        assertTrue(oldYTfunc2!=t_func2.getY(0));
    }
    @Test
    void leftBound_ExpectFirstElem_4countFunc(){
        assertEquals(1.0,t_func1.leftBound());
        assertEquals(2.0,t_func2.leftBound());
    }
    @Test
    void rightBound_ExpectLastElem_4countFunc(){
        assertEquals(5.0,t_func1.rightBound());
        assertEquals(4.0,t_func2.rightBound());
    }
    @Test
    void indexOfX_ExpectFound_ElemInList(){
        assertEquals(3,t_func1.indexOfX(t_func1.rightBound()));
        assertEquals(3,t_func2.indexOfX(t_func2.rightBound()));
    }
    @Test
    void indexOfX_ExpectNotFound_ElemNotInList(){
        assertEquals(-1,t_func1.indexOfX(27.5));
        assertEquals(-1,t_func2.indexOfX(27.5));
    }
    @Test
    void indexOfY_ExpectFound_ElemNotInList(){
        assertEquals(0,t_func1.indexOfY(t_func1.getY(0)));
        assertEquals(0,t_func2.indexOfY(t_func2.getY(0)));
    }
    @Test
    void indexOfY_ExpectNotFound_ElemNotInList(){
        assertEquals(-1,t_func1.indexOfX(27.5));
        assertEquals(-1,t_func2.indexOfX(27.5));
    }
    @Test
    void floorIndexOfXIllegalArgument_ExpectThrown_ElemLessThanLeft(){
        assertThrows(IllegalArgumentException.class,()->t_func1.floorIndexOfX(0));
        assertThrows(IllegalArgumentException.class,()->t_func1.floorIndexOfX(0));
    }
    @Test
    void floorIndexOfX_ExpectCount_ElemGreaterThanRight(){
        assertEquals(t_func1.count,t_func1.floorIndexOfX(27.5));
        assertEquals(t_func2.count,t_func2.floorIndexOfX(27.5));
    }
    @Test
    void floorIndexOfX_ExpectZero_ElemLessThanSecond(){
        assertEquals(0,t_func1.floorIndexOfX(1.5));
        assertEquals(0,t_func2.floorIndexOfX(2.5));
    }
    @Test
    void removeIllegalArgumentException_ExpectThrown_IndexLessThanZero(){
        assertThrows(IllegalArgumentException.class,()->t_func1.remove(-1));
        assertThrows(IllegalArgumentException.class,()->t_func2.remove(-1));
    }
    @Test
    void removeIllegalArgumentException_ExpectThrown_IndexGreaterThanCount(){
        assertThrows(IllegalArgumentException.class,()->t_func1.remove(4));
        assertThrows(IllegalArgumentException.class,()->t_func2.remove(4));
    }
    @Test
    void remove_ExpectSecondToLast_RemoveLast(){
        t_func1.remove(t_func1.count-1);
        t_func2.remove(t_func2.count-1);
        assertEquals(3,t_func1.count);
        assertEquals(3,t_func2.count);
        assertEquals(4.0,t_func1.rightBound());
        assertEquals(t_func2.getX(2),t_func2.rightBound());
    }
    @Test
    void remove_ExpectThird_RemoveSecond(){
        t_func1.remove(1);
        double Arg= t_func2.getX(2);
        t_func2.remove(1);
        assertEquals(3,t_func1.count);
        assertEquals(3,t_func2.count);
        assertEquals(4.0,t_func1.getX(1));
        assertEquals(Arg,t_func2.getX(1));
    }
    @Test
    void remove_ExpectSecond_RemoveFirst(){
        t_func1.remove(0);
        t_func2.remove(0);
        assertEquals(3,t_func1.count);
        assertEquals(3,t_func2.count);
        assertEquals(2.5,t_func1.leftBound());
        assertEquals(t_func2.getX(0),t_func2.leftBound());
    }
    @Test
    void insert_ExpectFirst_insertFirst(){
        t_func1.insert(0,2.0);
        t_func2.insert(0,3.0);
        assertEquals(5,t_func1.count);
        assertEquals(5,t_func2.count);
        assertEquals(0,t_func1.leftBound());
        assertEquals(0,t_func2.leftBound());
    }
    @Test
    void insert_ExpectLast_insertLast(){
        t_func1.insert(27.0,2.0);
        t_func2.insert(30.0,3.0);
        assertEquals(5,t_func1.count);
        assertEquals(5,t_func2.count);
        assertEquals(27.0,t_func1.rightBound());
        assertEquals(30.0,t_func2.rightBound());
    }
    @Test
    void insert_ExpectThird_insertMiddle(){
        t_func1.insert(2.6,2.0);
        t_func2.insert(3.0,3.0);
        assertEquals(5,t_func1.count);
        assertEquals(5,t_func2.count);
        assertEquals(2.6,t_func1.getX(2));
        assertEquals(3.0,t_func2.getX(2));
    }
    @Test
    void extrapolateLeft_ExpectEqual(){
        assertEquals(3.333333333,t_func1.extrapolateLeft(0),0.000001);
    }
    @Test
    void extrapolateRight_ExpectEqual(){
        assertEquals(-14,t_func1.extrapolateRight(9),0.000001);
    }
    @Test
    void applyInterpolate4Param_ExpectEqual_Interpolate(){
        double x0=t_func1.getX(0);
        double x1=t_func1.getX(1);
        double y0=t_func1.getY(0);
        double y1=t_func1.getY(1);
        assertEquals(t_func1.interpolate(1.5,x0,x1,y0,y1),t_func1.apply(1.5));
        /**Assertions.assertThrows(InterpolationException.class, () -> {
            LinkedListTabulatedFunction test1 = new LinkedListTabulatedFunction(new double[]{1, 2, 3, 4}, new double[]{1, 2, 3, 4});
            int help1 = test1.floorIndexOfX(-6);
            test1.interpolate(-6, help1);
        });**/
        Assertions.assertThrows(InterpolationException.class, () -> {
            LinkedListTabulatedFunction test2 = new LinkedListTabulatedFunction(new double[]{1, 2, 3, 4}, new double[]{1, 2, 3, 4});
            int help2 = test2.floorIndexOfX(1);
            test2.interpolate(1, help2);
        });
        Assertions.assertThrows(InterpolationException.class, () -> {
            LinkedListTabulatedFunction test3 = new LinkedListTabulatedFunction(new double[]{1, 2, 3, 4}, new double[]{1, 2, 3, 4});
            int help3 = test3.floorIndexOfX(3);
            test3.interpolate(3, help3);
        });
        Assertions.assertThrows(InterpolationException.class, () -> {
            LinkedListTabulatedFunction test4 = new LinkedListTabulatedFunction(new double[]{1, 2, 3, 4}, new double[]{1, 2, 3, 4});
            int help4 = test4.floorIndexOfX(4);
            test4.interpolate(4, help4);
        });
        Assertions.assertThrows(InterpolationException.class, () -> {
            LinkedListTabulatedFunction test5 = new LinkedListTabulatedFunction(new double[]{1, 2, 3, 4}, new double[]{1, 2, 3, 4});
            int help5 = test5.floorIndexOfX(7);
            test5.interpolate(7, help5);
        });
    }
    @Test
    void applyInterpolate2Param_ExpectEqual_Interpolate(){
        assertEquals(t_func1.interpolate(1.5,0),t_func1.apply(1.5));
    }
    @Test
    void LinkedListIterator_ExpectThrown_While(){
        assertThrows(NoSuchElementException.class,()-> {
            Iterator<Point> iterator = t_func1.iterator();
            Point point = new Point(0, 0);
            while (iterator.hasNext()) {
                point = iterator.next();
            }
            point=iterator.next();
        });
    }
    @Test
    void LinkedListIterator_ExpectNoException_While(){
        Iterator<Point> iterator=t_func1.iterator();
        Point point=new Point(0,0);
        while (iterator.hasNext()){
            point=iterator.next();
        }
        assertEquals(point.x,t_func1.rightBound());
    }
    @Test
    void LinkedListIterator_ExpectNoException_For(){
        double x=0;
        for (Point point:t_func1){
            x=point.x;
        }
        assertEquals(x,t_func1.rightBound());
    }

}