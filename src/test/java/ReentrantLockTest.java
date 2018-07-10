import org.junit.Test;
import reentrant.ReentrantLockThread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by jingxuan on 2018/7/9.
 */
public class ReentrantLockTest {
    private Lock lock = new ReentrantLock();
    @Test
    public void test() {
        ReentrantLockThread thread1 = new ReentrantLockThread(new Runnable() {
            public void run() {
                System.out.println("test1 start");
                lock.lock();
                try {
                    Thread.sleep(10000);
                    System.out.println("lock over");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.unlock();
            }
        });
        thread1.start();
        thread1.interrupt();

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        ReentrantLockThread thread = new ReentrantLockThread(new Runnable() {
            public void run() {
                System.out.println("test2 start");
                try {
                    lock.lockInterruptibly();
                    Thread.sleep(10000);
                    System.out.println("lockInterruptibly over");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        thread.interrupt();

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
