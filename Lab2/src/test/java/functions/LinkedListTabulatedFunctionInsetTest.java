package functions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LinkedListTabulatedFunctionInsetTest {

    @Test
    void insert() {
        LinkedListTabulatedFunction test1 = new LinkedListTabulatedFunction(new double[]{-1, 0.1111, 78.1, 97.3459}, new double[]{-198, -45, -198, -200.22229});
       test1.insert(100, -99);
        assertEquals(test1.indexOfX(100), 4);
        assertEquals(test1.indexOfY(-99), 4);
        LinkedListTabulatedFunction test2 = new LinkedListTabulatedFunction(new double[]{-1, 0.1111, 78.1, 97.3459}, new double[]{-198, -45, -198, -200.22229});
        test2.insert(-2, -9);
        assertEquals(test2.indexOfX(-2), 0);
        assertEquals(test2.indexOfY(-9), 0);
        LinkedListTabulatedFunction test3 = new LinkedListTabulatedFunction(new double[]{-1, 0.1111, 78.1, 97.3459}, new double[]{-198, -45, -198, -200.22229});
        test3.insert(61.8909, 34);
        assertEquals(test3.indexOfX(61.8909), 2);
        assertEquals(test3.indexOfY(34), 2);
    }
}