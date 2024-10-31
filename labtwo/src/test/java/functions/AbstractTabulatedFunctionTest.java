package functions;

import exceptions.ArrayIsNotSortedException;
import exceptions.DifferentLengthOfArraysException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AbstractTabulatedFunctionTest {

    @Test
    void checkLengthIsTheSame() {
        Assertions.assertThrows(DifferentLengthOfArraysException.class, () ->
                ArrayTabulatedFunction.checkLengthIsTheSame(new double[]{1, 2, 3, 4}, new double[]{1, 2, 3, 4, 4}));
        Assertions.assertThrows(DifferentLengthOfArraysException.class, () ->
                ArrayTabulatedFunction.checkLengthIsTheSame(new double[]{1, 2, 3, 4}, new double[]{1, 2, 3}));
        Assertions.assertThrows(DifferentLengthOfArraysException.class, () ->
                LinkedListTabulatedFunction.checkLengthIsTheSame(new double[]{1, 2, 3, 4}, new double[]{1, 2, 3, 4, 4}));
        Assertions.assertThrows(DifferentLengthOfArraysException.class, () ->
                LinkedListTabulatedFunction.checkLengthIsTheSame(new double[]{1, 2, 3, 4}, new double[]{1, 2, 3}));
        Assertions.assertThrows(DifferentLengthOfArraysException.class, () ->
                new ArrayTabulatedFunction(new double[]{1, 2, 3, 4}, new double[]{1, 2, 3, 4, 4}));
        Assertions.assertThrows(DifferentLengthOfArraysException.class, () ->
                new ArrayTabulatedFunction(new double[]{1, 2, 3}, new double[]{1, 2, 3, 4}));
        Assertions.assertThrows(DifferentLengthOfArraysException.class, () ->
                new LinkedListTabulatedFunction(new double[]{1, 2, 3, 4}, new double[]{1, 2, 3, 4, 4}));
        Assertions.assertThrows(DifferentLengthOfArraysException.class, () ->
                new LinkedListTabulatedFunction(new double[]{1, 2, 3}, new double[]{1, 2, 3, 4}));
    }

    @Test
    void checkSorted() {
        Assertions.assertThrows(ArrayIsNotSortedException.class, () ->
                ArrayTabulatedFunction.checkSorted(new double[]{1, 3, 3, 4}));
        Assertions.assertThrows(ArrayIsNotSortedException.class, () ->
                ArrayTabulatedFunction.checkSorted(new double[]{1, 3, 2, 4}));
        Assertions.assertThrows(ArrayIsNotSortedException.class, () ->
                ArrayTabulatedFunction.checkSorted(new double[]{1, 2, 3, 1}));
        Assertions.assertThrows(ArrayIsNotSortedException.class, () ->
                ArrayTabulatedFunction.checkSorted(new double[]{4, 2, 3, 1}));
        Assertions.assertThrows(ArrayIsNotSortedException.class, () ->
                LinkedListTabulatedFunction.checkSorted(new double[]{1, 2, 3, 3}));
        Assertions.assertThrows(ArrayIsNotSortedException.class, () ->
                LinkedListTabulatedFunction.checkSorted(new double[]{1, 3, 2, 4}));
        Assertions.assertThrows(ArrayIsNotSortedException.class, () ->
                LinkedListTabulatedFunction.checkSorted(new double[]{1, 2, 3, 1}));
        Assertions.assertThrows(ArrayIsNotSortedException.class, () ->
                LinkedListTabulatedFunction.checkSorted(new double[]{4, 2, 3, 1}));
        Assertions.assertThrows(ArrayIsNotSortedException.class, () ->
                new ArrayTabulatedFunction(new double[]{1, 2, 3, 3}, new double[]{1, 2, 3, 4}));
        Assertions.assertThrows(ArrayIsNotSortedException.class, () ->
                new ArrayTabulatedFunction(new double[]{1, 3, 2, 4}, new double[]{1, 2, 3, 4}));
        Assertions.assertThrows(ArrayIsNotSortedException.class, () ->
                new ArrayTabulatedFunction(new double[]{1, 2, 3, 1}, new double[]{1, 2, 3, 4}));
        Assertions.assertThrows(ArrayIsNotSortedException.class, () ->
                new ArrayTabulatedFunction(new double[]{4, 2, 3, 1}, new double[]{1, 2, 3, 4}));
        Assertions.assertThrows(ArrayIsNotSortedException.class, () ->
                new LinkedListTabulatedFunction(new double[]{1, 2, 3, 3}, new double[]{1, 2, 3, 4}));
        Assertions.assertThrows(ArrayIsNotSortedException.class, () ->
                new LinkedListTabulatedFunction(new double[]{1, 3, 2, 4}, new double[]{1, 2, 3, 4}));
        Assertions.assertThrows(ArrayIsNotSortedException.class, () ->
                new LinkedListTabulatedFunction(new double[]{1, 2, 3, 1}, new double[]{1, 2, 3, 4}));
        Assertions.assertThrows(ArrayIsNotSortedException.class, () ->
                new LinkedListTabulatedFunction(new double[]{4, 2, 3, 1}, new double[]{1, 2, 3, 4}));
    }
    @Test
    void toString_ExpectEqual_LinkedList(){
        var func=new LinkedListTabulatedFunction(new double[]{0.0,0.5,1.0},new double[]{0.0,0.25,1.0});
        Assertions.assertEquals("LinkedListTabulatedFunction size = 3\n[0.0; 0.0]\n[0.5; 0.25]\n[1.0; 1.0]",func.toString());
    }
    @Test
    void toString_ExpectEqual_Array(){
        ArrayTabulatedFunction func=new ArrayTabulatedFunction(new double[]{0.0,0.5,1.0},new double[]{0.0,0.25,1.0});
        Assertions.assertEquals("ArrayTabulatedFunction size = 3\n[0.0; 0.0]\n[0.5; 0.25]\n[1.0; 1.0]",func.toString());
        System.out.println(func.toString());
    }
}