package concurrent;
import functions.*;
import org.junit.jupiter.api.Test;

import static java.lang.Math.sqrt;
import static org.junit.jupiter.api.Assertions.*;

class IntegralTaskTest {
    private final IntegralTask task1=new IntegralTask((x)->sqrt(x),10000,0);
    private final IntegralTask task2=new IntegralTask((x)->sqrt(x),10000,0,100);
    @Test
    void call_ExpectEqual_HighNumberOfSections(){
        assertEquals(666666,task1.call(),1);
    }
    @Test
    void call_ExpectEqual_LowNumberOfSections(){
        assertEquals(666666,task2.call(),250);
    }
    @Test
    void constructor_ExpectException(){
        assertThrows(IllegalArgumentException.class,()->new IntegralTask(new ZeroFunction(),1,2 ,-1));

    }
}