package functions;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StrictTabulatedFunctionTest {
    private  StrictTabulatedFunction strictLinked;
    private StrictTabulatedFunction strictArray;
    @BeforeEach
    void t_constructor() {
         final LinkedListTabulatedFunction linked = new LinkedListTabulatedFunction(new double[]{1, 2, 3}, new double[]{1, 2, 3});
         final ArrayTabulatedFunction array = new ArrayTabulatedFunction(new double[]{1, 2, 3}, new double[]{1, 2, 3});
         strictLinked = new StrictTabulatedFunction(linked);
         strictArray = new StrictTabulatedFunction(array);
    }
    @Test
    void getCount_ExpectThree_LinkedStrict() {
        assertEquals(3,strictLinked.getCount());
    }
    @Test
    void getCount_ExpectThree_ArrayStrict() {
        assertEquals(3,strictArray.getCount());
    }
    @Test
    void getX_ExpectOne_LinkedStrict(){
        assertEquals(1,strictLinked.getX(0));
    }
    @Test
    void getX_ExpectOne_ArrayStrict(){
        assertEquals(1,strictArray.getX(0));
    }
    @Test
    void getX_ExpectException_LinkedStrictOutOfBounds(){
        assertThrows(IllegalArgumentException.class,()->strictLinked.getX(3));
    }
    @Test
    void getX_ExpectException_LinkedStrictLessThanZero(){
        assertThrows(IllegalArgumentException.class,()->strictLinked.getX(-1));
    }
    @Test
    void getY_ExpectOne_LinkedStrict(){
        assertEquals(1,strictLinked.getY(0));
    }
    @Test
    void getY_ExpectOne_ArrayStrict(){
        assertEquals(1,strictArray.getY(0));
    }
    @Test
    void getY_ExpectException_LinkedStrictOutOfBounds(){
        assertThrows(IllegalArgumentException.class,()->strictLinked.getY(3));
    }
    @Test
    void getY_ExpectException_LinkedStrictLessThanZero(){
        assertThrows(IllegalArgumentException.class,()->strictLinked.getY(-1));
    }
    @Test
    void setY_ExpectZero_LinkedStrict(){
        strictLinked.setY(0,0);
        assertEquals(0,strictLinked.getY(0));
    }
    @Test
    void setY_ExpectZero_ArrayStrict(){
        strictArray.setY(0,0);
        assertEquals(0,strictArray.getY(0));
    }

    @Test
    void setY_ExpectException_LinkedStrictOutOfBounds(){
        assertThrows(IllegalArgumentException.class,()->strictLinked.setY(3,0));
    }
    @Test
    void setY_ExpectException_LinkedStrictLessThanZero(){
        assertThrows(IllegalArgumentException.class,()->strictLinked.setY(-1,0));
    }
    @Test
    void indexOfX_ExpectZero_LinkedStrict(){
        assertEquals(0,strictLinked.indexOfX(1));
    }
    @Test
    void indexOfX_ExpectZero_ArrayStrict(){
        assertEquals(0,strictArray.indexOfX(1));
    }
    @Test
    void indexOfX_ExpectNotFound_LinkedStrict(){
        assertEquals(-1,strictLinked.indexOfX(-1));
    }
    @Test
    void indexOfX_ExpectNotFound_ArrayStrict(){
        assertEquals(-1,strictArray.indexOfX(-1));
    }
    @Test
    void indexOfY_ExpectZero_LinkedStrict(){
        assertEquals(0,strictLinked.indexOfY(1));
    }
    @Test
    void indexOfY_ExpectZero_ArrayStrict(){
        assertEquals(0,strictArray.indexOfY(1));
    }
    @Test
    void leftBound_ExpectOne_LinkedStrict(){
        assertEquals(1,strictLinked.leftBound());
    }
    @Test
    void leftBound_ExpectOne_ArrayStrict(){
        assertEquals(1,strictArray.leftBound());
    }
    @Test
    void rightBound_ExpectThree_LinkedStrict(){
        assertEquals(3,strictLinked.rightBound());
    }
    @Test
    void rightBound_ExpectThree_ArrayStrict(){
        assertEquals(3,strictArray.rightBound());
    }
    @Test
    void apply_ExpectOne_LinkedStrictFound(){
        assertEquals(1,strictLinked.apply(1));
    }
    @Test
    void apply_ExpectOne_ArrayStrictFound(){
        assertEquals(1,strictArray.apply(1));
    }
    @Test
    void apply_ExpectException_LinkedStrictNotFound(){
        assertThrows(NoSuchElementException.class,()->strictLinked.apply(5));
    }
    @Test
    void apply_ExpectException_ArrayStrictNotFound(){
        assertThrows(NoSuchElementException.class,()->strictArray.apply(5));
    }
    @Test
    void iterator_ExpectEqual_LinkedStrict(){
        Iterator<Point> iterator=strictLinked.iterator();
        Point point=new Point(0,0);
        while (iterator.hasNext()){
            point=iterator.next();
        }
        assertEquals(point.x,strictLinked.rightBound());
    }
    @Test
    void iterator_ExpectException_LinkedStrict(){
        assertThrows(NoSuchElementException.class,()->{
            Iterator<Point> iterator=strictLinked.iterator();
            Point point=new Point(0,0);
            while (iterator.hasNext()){
                point=iterator.next();
            }
            point=iterator.next();
        });
    }
}