import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;
import queue.BlockingQueue;

/**
 * Created by jingxuan on 2018/7/6.
 */
public class LearnMain {
    private static BlockingQueue<Integer> queue = new BlockingQueue<Integer>(10);
    public static void main(String[] args) throws InterruptedException {
        Thread a = new Thread() {
            @Override
            public void run() {
                for( int j = 0 ; j < 100 ; j++ )
                    try {
                        queue.put(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }
        };
        a.start();
        Thread b = new Thread() {
            @Override
            public void run() {
                for( int j = 0 ; j < 90 ; j++ )
                    try {
                        queue.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }
        };
        b.start();
        a.join();
        b.join();
        System.out.println(queue.getIndex());
    }
}
