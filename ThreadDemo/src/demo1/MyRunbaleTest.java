package demo1;

public class MyRunbaleTest {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            if (i == 5) {
                MyRunnable runnable = new MyRunnable();
                Thread thread1 = new Thread(runnable);
                Thread thread2 = new Thread(runnable);
                thread1.start();
                thread2.start();
            }
        }
    }
}

class MyRunnable implements Runnable {
    private int i = 0;

    @Override
    public void run() {
        for (i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }
}

