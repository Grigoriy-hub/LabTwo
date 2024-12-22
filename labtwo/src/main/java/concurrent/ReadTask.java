package concurrent;

import functions.TabulatedFunction;
import functions.Point;
import operations.TabulatedFunctionOperationService;

public class ReadTask implements Runnable {
    private final TabulatedFunction readFunc;

    public ReadTask(TabulatedFunction func) {
        this.readFunc = func;
    }

    @Override
    public void run() {
        Point[] p = TabulatedFunctionOperationService.asPoints(readFunc);
        for (int i = 0; i < readFunc.getCount(); i++) {
            synchronized (readFunc) {
                System.out.println("After read: " +
                        "i = " + String.format("%d", i) +
                        ", x = " + String.format("%f", readFunc.getX(i)) +
                        ", y = " + String.format("%f", readFunc.getY(i)));
            }
        }
    }
}
