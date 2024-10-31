package functions.factory;

import functions.ArrayTabulatedFunction;
import org.junit.jupiter.api.Test;
import functions.StrictTabulatedFunction;

import static org.junit.jupiter.api.Assertions.*;

class ArrayTabulatedFunctionFactoryTest {
    private final ArrayTabulatedFunctionFactory factory= new ArrayTabulatedFunctionFactory();
    @Test
    void create_ExpectArrayTabulated(){
        assertInstanceOf(ArrayTabulatedFunction.class,factory.create(new double[]{1,2,3},new double[] {1,2,3}));
    }
    @Test
    void create_ExpectStrictTabulated(){
        assertInstanceOf(StrictTabulatedFunction.class,factory.createStrictTabulatedFunction(new double[]{1,2,3},new double[] {1,2,3}));
    }
    @Test
    void create_ExpectIllegalArgumentException_OnePointFunc(){
        assertThrows(IllegalArgumentException.class,()->factory.create(new double[]{0},new double[]{0}));
    }
}