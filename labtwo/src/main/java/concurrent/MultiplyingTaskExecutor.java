package concurrent;

import functions.LinkedListTabulatedFunction;
import functions.UnitFunction;

import java.util.ArrayList;
import java.util.List;

public class MultiplyingTaskExecutor {
    public static void main(String[] args) {
        UnitFunction unitFunction = new UnitFunction();
        LinkedListTabulatedFunction linkedListTabulatedFunction = new LinkedListTabulatedFunction(unitFunction,1, 1000, 1000);
        List<Thread> list = new ArrayList<>();
        for(int i = 0; i < 10; ++i){
            MultiplyingTask multiplyingTask = new MultiplyingTask(linkedListTabulatedFunction);
            Thread thread = new Thread(multiplyingTask);
            list.add(thread);
        }
        for(int i = 0; i < list.size(); ++i){
            list.get(i).start();
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(linkedListTabulatedFunction);


    }
}
