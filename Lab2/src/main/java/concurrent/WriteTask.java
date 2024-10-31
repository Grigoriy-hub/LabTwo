package concurrent;

import functions.TabulatedFunction;

public class WriteTask implements Runnable{
    private final TabulatedFunction writeFunc;
    private final double value;
    public WriteTask(TabulatedFunction func,double value){
        this.writeFunc=func;
        this.value=value;
    }
    @Override
    public void run(){
        int count=writeFunc.getCount();
        for (int i = 0; i < count; i++) {
                    writeFunc.setY(i, value);
                    System.out.println("Writing for index " + String.format("%d", i) + " complete");
            }
        }

}
