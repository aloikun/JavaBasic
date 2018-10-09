package demo1;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class MyCallableTest {
    public static void main(String[] args) {
        // 创建Callable 对象
        Callable<Integer> myCallable = new MyCallable();
        // 使用 FutureTask 来包装Callable 对象
        FutureTask<Integer> futureTask = new FutureTask<>(myCallable);

        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            if (i == 30) {
                Thread thread = new Thread(futureTask);
                thread.start();
            }
        }
        System.out.println("主线程for 循环执行完毕后");
        try {
            // 取得新创建的新线程中的call()方法返回的结果
            int sum = futureTask.get();
            System.out.println("sum = " + sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}

class MyCallable implements Callable<Integer> {
    private int i = 0;

    @Override
    public Integer call() { // 执行主体
        int sum = 0;
        for (; i <= 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            sum += i;
        }
        return sum;
    }
}
