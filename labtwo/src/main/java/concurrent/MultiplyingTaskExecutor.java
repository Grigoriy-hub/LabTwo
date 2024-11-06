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
        ArrayList<MultiplyingTask> taskList = new ArrayList<>();
        for(int i = 0; i < 10; ++i){
            MultiplyingTask multiplyingTask = new MultiplyingTask(linkedListTabulatedFunction);
            taskList.add(multiplyingTask);
            Thread thread = new Thread(multiplyingTask);
            list.add(thread);
        }
        for (Thread thread : list) {
            thread.start();
        }
        int i = 0;
        while (!taskList.isEmpty()){
            if (i >= taskList.size()){
                i = 0;
            }
            if (!list.get(i).isAlive()){
                taskList.remove(i);
            }
            ++i;

        }
        System.out.println(linkedListTabulatedFunction);


    }
}
