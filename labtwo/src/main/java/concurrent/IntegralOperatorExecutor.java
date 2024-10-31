package concurrent;

import functions.LinkedListTabulatedFunction;
import functions.TabulatedFunction;
import functions.SqrFunction;

public class IntegralOperatorExecutor
{
    public static void main(String[] args)throws InterruptedException {
        IntegralFunctional_Troglodyte_Edition op = new IntegralFunctional_Troglodyte_Edition();
        TabulatedFunction func = new LinkedListTabulatedFunction(new SqrFunction(), 1, 100000000, 1000);
        System.out.println(op.integrate(func, func.leftBound(), func.rightBound(), 1000000, 7));
    }
}
