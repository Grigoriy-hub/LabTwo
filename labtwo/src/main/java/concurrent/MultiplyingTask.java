package concurrent;

import functions.TabulatedFunction;

public class MultiplyingTask implements Runnable {
    private TabulatedFunction linkTabulatedFunction;
    public MultiplyingTask(TabulatedFunction linkTabulatedFunction) {
        this.linkTabulatedFunction = linkTabulatedFunction;
    }

    @Override
    public void run() {
        for(int i = 0; i < linkTabulatedFunction.getCount(); ++i) {
            synchronized (linkTabulatedFunction) {
                linkTabulatedFunction.setY(i, 2 * linkTabulatedFunction.getY(i));
            }
        }
        Thread currentThread = Thread.currentThread();
        System.out.println("The current thread: " + currentThread.getName() + " ,finished executing the task");
    }
}
