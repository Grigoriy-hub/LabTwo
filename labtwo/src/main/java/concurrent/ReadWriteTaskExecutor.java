package concurrent;

import functions.ConstantFunction;
import functions.LinkedListTabulatedFunction;

public class ReadWriteTaskExecutor {
    public static void main(String[] args) {
        var func = new LinkedListTabulatedFunction(new ConstantFunction(-1), 1, 1000, 1000);
        Thread thread1 = new Thread(new ReadTask(func));
        Thread thread2 = new Thread(new WriteTask(func, 0.5));
        thread1.start();
        thread2.start();
    }
}
