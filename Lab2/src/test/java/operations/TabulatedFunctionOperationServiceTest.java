package operations;
import exceptions.InconsistentFunctionsException;
import functions.Point;
import functions.TabulatedFunction;
import functions.ArrayTabulatedFunction;
import functions.LinkedListTabulatedFunction;
import functions.factory.LinkedListTabulatedFunctionFactory;
import functions.factory.ArrayTabulatedFunctionFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;


class TabulatedFunctionOperationServiceTest {

    @Test
    void asPoints() {
        ArrayTabulatedFunctionFactory test1 = new ArrayTabulatedFunctionFactory();
        TabulatedFunction help1 = test1.create(new double[]{1, 2, 3, 4}, new double[]{1, 2, 3, 4});
        Point[] help2 = TabulatedFunctionOperationService.asPoints(help1);
        Point h1 = help2[0];
        Point h2 = help2[1];
        Point h3 = help2[2];
        Point h4 = help2[3];
        assertEquals(1,h1.x);
        assertEquals(2,h2.x);
        assertEquals(3,h3.x);
        assertEquals( 4,h4.x);
        assertEquals(1,h1.y);
        assertEquals(2,h2.y);
        assertEquals(3,h3.y);
        assertEquals( 4,h4.y);
    }
    @Test
    void multiply_ExpectArray(){
        TabulatedFunctionOperationService serviceT=new TabulatedFunctionOperationService();
        TabulatedFunction factRes=serviceT.multiply(new LinkedListTabulatedFunction((double x)->{return x/2.0;},0,1000,1001),
                          new ArrayTabulatedFunction((double x)->{return 2.0;},0,1000,1001));
        assertInstanceOf(ArrayTabulatedFunction.class,factRes);
        assertEquals(400,factRes.getY(400));
    }
    @Test
    void divide_ExpectLinkedList(){
        TabulatedFunctionOperationService serviceT=new TabulatedFunctionOperationService(new LinkedListTabulatedFunctionFactory());
        TabulatedFunction factRes=serviceT.divide(new LinkedListTabulatedFunction((double x)->{return 2.0*x;},0,1000,1001),
                new ArrayTabulatedFunction((double x)->{return 2.0;},0,1000,1001));
        assertInstanceOf(LinkedListTabulatedFunction.class,factRes);
        assertEquals(400,factRes.getY(400));
    }
    @Test
    void multiply_ExpectException_DifferentX(){
        assertThrows(InconsistentFunctionsException.class,()->{
            TabulatedFunctionOperationService serviceT=new TabulatedFunctionOperationService();
            TabulatedFunction factRes=serviceT.multiply(new LinkedListTabulatedFunction((double x)->{return 2.0*x;},0,100,1001),
                    new ArrayTabulatedFunction((double x)->{return 2.0;},0,1000,1001));
        });
    }
    @Test
    void divide_ExpectException_DifferentX(){
        assertThrows(InconsistentFunctionsException.class,()->{
            TabulatedFunctionOperationService serviceT=new TabulatedFunctionOperationService();
            TabulatedFunction factRes=serviceT.divide(new LinkedListTabulatedFunction((double x)->{return 2.0*x;},0,100,1001),
                    new ArrayTabulatedFunction((double x)->{return 2.0;},0,1000,1001));
        });
    }
    @Test
    void divide_ExpectException_DifferentNumberOfElem(){
        assertThrows(InconsistentFunctionsException.class,()->{
            TabulatedFunctionOperationService serviceT=new TabulatedFunctionOperationService();
            TabulatedFunction factRes=serviceT.divide(new LinkedListTabulatedFunction((double x)->{return 2.0*x;},0,100,1001),
                    new ArrayTabulatedFunction((double x)->{return 2.0;},0,1000,100));
        });
    }
    @Test
    void multiply_ExpectException_DifferentNumberOfElem(){
        assertThrows(InconsistentFunctionsException.class,()->{
            TabulatedFunctionOperationService serviceT=new TabulatedFunctionOperationService();
            TabulatedFunction factRes=serviceT.multiply(new LinkedListTabulatedFunction((double x)->{return 2.0*x;},0,100,1001),
                    new ArrayTabulatedFunction((double x)->{return 2.0;},0,1000,100));
        });
    }
    @Test
    void getFactory_ExpectLinkedList(){
        TabulatedFunctionOperationService serviceT=new TabulatedFunctionOperationService(new LinkedListTabulatedFunctionFactory());
        assertInstanceOf(LinkedListTabulatedFunctionFactory.class,serviceT.getFactory());

    }
    @Test
    void setFactory_ExpectLinkedList(){
        TabulatedFunctionOperationService serviceT=new TabulatedFunctionOperationService();
        serviceT.setFactory(new LinkedListTabulatedFunctionFactory());
        assertInstanceOf(LinkedListTabulatedFunctionFactory.class,serviceT.getFactory());

    }

    @Test
    void testAsPoints() {
        ArrayTabulatedFunction test1 = new ArrayTabulatedFunction(new double[]{1, 2, 3, 4}, new double[]{1, 4, 9, 16});
        Point[] test1Point = TabulatedFunctionOperationService.asPoints(test1);
        assertEquals(test1Point.length, test1.getCount());
        for(int i = 0; i < test1Point.length; i++){
            assertEquals(test1Point[i].x, test1.getX(i));
            assertEquals(test1Point[i].y, test1.getY(i));
        }
        LinkedListTabulatedFunction test2 = new LinkedListTabulatedFunction(new double[]{12, 34, 39, 42}, new double[]{10, 1, 9, 6});
        Point[] test2Point = TabulatedFunctionOperationService.asPoints(test2 );
        assertEquals(test1Point.length, test1.getCount());
        for(int i = 0; i < test2Point.length; i++) {
            assertEquals(test2Point[i].x, test2.getX(i));
            assertEquals(test2Point[i].y, test2.getY(i));
        }
    }

    @Test
    void getFactory() {
        ArrayTabulatedFunctionFactory test1 = new ArrayTabulatedFunctionFactory();
        TabulatedFunctionOperationService test1F = new TabulatedFunctionOperationService(test1);
         assertInstanceOf(ArrayTabulatedFunctionFactory.class, test1F.getFactory());

    }

    @Test
    void setFactory() {
        ArrayTabulatedFunctionFactory test2 = new ArrayTabulatedFunctionFactory();
        TabulatedFunctionOperationService test2F = new TabulatedFunctionOperationService(test2);
        LinkedListTabulatedFunctionFactory test2FL = new LinkedListTabulatedFunctionFactory();
        test2F.setFactory(test2FL);
        assertInstanceOf(LinkedListTabulatedFunctionFactory.class, test2F.getFactory());
    }

    @Test
    void plusFunction() {
        ArrayTabulatedFunction test1 = new ArrayTabulatedFunction(new double[]{1, 2, 3, 4}, new double[]{1, 4, 9, 16});
        LinkedListTabulatedFunction test1LL = new LinkedListTabulatedFunction(new double[]{1, 2, 3, 4}, new double[]{1, 4, 9, 16});
        TabulatedFunctionOperationService test1TOS = new TabulatedFunctionOperationService();
        TabulatedFunction test1TT = test1TOS.PlusFunction(test1, test1LL);
        assertEquals(test1TT.getY(0), 2);
        assertEquals(test1TT.getY(1), 8);
        assertEquals(test1TT.getY(2), 18);
        assertEquals(test1TT.getY(3), 32);
        assertEquals(test1TT.getX(0), 1);
        assertEquals(test1TT.getX(1), 2);
        assertEquals(test1TT.getX(2), 3);
        assertEquals(test1TT.getX(3), 4);

        ArrayTabulatedFunction test2 = new ArrayTabulatedFunction(new double[]{1, 2, 3, 4}, new double[]{1, 1, 1, 1});
        ArrayTabulatedFunction test2LL = new ArrayTabulatedFunction(new double[]{1, 2, 3, 4}, new double[]{1, 1, 1, 1});
        TabulatedFunctionOperationService test2TOS = new TabulatedFunctionOperationService();
        TabulatedFunction test2TT = test2TOS.PlusFunction(test2, test2LL);
        assertEquals(test2TT.getY(0), 2);
        assertEquals(test2TT.getY(1), 2);
        assertEquals(test2TT.getY(2), 2);
        assertEquals(test2TT.getY(3), 2);
        assertEquals(test2TT.getX(0), 1);
        assertEquals(test2TT.getX(1), 2);
        assertEquals(test2TT.getX(2), 3);
        assertEquals(test2TT.getX(3), 4);



    }

    @Test
    void minusFunction() {
        ArrayTabulatedFunction test1 = new ArrayTabulatedFunction(new double[]{1, 2, 3, 4}, new double[]{1, 4, 9, 16});
        LinkedListTabulatedFunction test1LL = new LinkedListTabulatedFunction(new double[]{1, 2, 3, 4}, new double[]{1, 4, 9, 16});
        TabulatedFunctionOperationService test1TOS = new TabulatedFunctionOperationService();
        TabulatedFunction test1TT = test1TOS.MinusFunction(test1, test1LL);
        assertEquals(test1TT.getY(0), 0);
        assertEquals(test1TT.getY(1), 0);
        assertEquals(test1TT.getY(2), 0);
        assertEquals(test1TT.getY(3), 0);
        assertEquals(test1TT.getX(0), 1);
        assertEquals(test1TT.getX(1), 2);
        assertEquals(test1TT.getX(2), 3);
        assertEquals(test1TT.getX(3), 4);

        ArrayTabulatedFunction test2 = new ArrayTabulatedFunction(new double[]{1, 2, 3, 4}, new double[]{2, 2, 2, 2});
        ArrayTabulatedFunction test2LL = new ArrayTabulatedFunction(new double[]{1, 2, 3, 4}, new double[]{1, 1, 1, 1});
        TabulatedFunctionOperationService test2TOS = new TabulatedFunctionOperationService();
        TabulatedFunction test2TT = test2TOS.MinusFunction(test2, test2LL);
        assertEquals(test2TT.getY(0), 1);
        assertEquals(test2TT.getY(1), 1);
        assertEquals(test2TT.getY(2), 1);
        assertEquals(test2TT.getY(3), 1);
        assertEquals(test2TT.getX(0), 1);
        assertEquals(test2TT.getX(1), 2);
        assertEquals(test2TT.getX(2), 3);
        assertEquals(test2TT.getX(3), 4);
    }
}