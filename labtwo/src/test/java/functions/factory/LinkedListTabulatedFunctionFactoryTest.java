package functions.factory;

import functions.LinkedListTabulatedFunction;
import functions.StrictTabulatedFunction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTabulatedFunctionFactoryTest {
    private final LinkedListTabulatedFunctionFactory factory=new LinkedListTabulatedFunctionFactory();
    @Test
    void create_ExpectLinkedListTabulated(){
        assertInstanceOf(LinkedListTabulatedFunction.class,factory.create(new double[]{1,2,3},new double[] {1,2,3}));
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