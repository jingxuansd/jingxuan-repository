package queue;

/**
 * Created by jingxuan on 2018/7/6.
 */
public class BlockingQueue<T> {
    T[] arr;
    private final int capacity;
    private int index = 0;

    public BlockingQueue(int size) {
        arr = (T[]) new Object[size];
        capacity = size;
    }

    public void put(T t) throws InterruptedException {
        if (t == null) return;
        synchronized (this) {
            if (index == capacity) {
                System.out.println("队列已满，放等待。。。");
                wait();
            }
            arr[index++] = t;
            if (index == 1) {
                System.out.println("队列已不为空，唤醒取。。。");
                notifyAll();
            }
//            System.out.println("添加一个：" + t);
        }
    }

    public T take() throws InterruptedException {
        T choose;
        synchronized (this) {
            if (index == 0) {
                System.out.println("队列为空，取等待。。。");
                wait();
            }
            choose = arr[--index];
            arr[index] = null; //删除
            if (index == capacity-1) {
                System.out.println("队列未满，唤醒放。。。");
                notifyAll();
            }
//            System.out.println("取出一个：" + choose);
        }
        return choose;
    }

    public int getIndex() {
        return index;
    }
}
