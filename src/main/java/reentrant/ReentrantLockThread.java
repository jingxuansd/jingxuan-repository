package reentrant;

/**
 * Created by jingxuan on 2018/7/9.
 */
public class ReentrantLockThread extends Thread {

    public ReentrantLockThread(Runnable cmd) {
        super(cmd);
    }
}
