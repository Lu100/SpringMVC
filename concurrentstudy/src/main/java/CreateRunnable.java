import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by Administrator on 2017/4/21.
 */
public class CreateRunnable {

    public static void main(String[] args) {
        ThreadB threadB = new ThreadB();
        Thread thread0 = new Thread(threadB);
        Thread thread1 = new Thread(threadB);
        Thread thread2 = new Thread(threadB);
        Thread thread3 = new Thread(threadB);
        Thread thread4 = new Thread(threadB);
        List<Thread> threads = Lists.newArrayList(thread0, thread1, thread2, thread3, thread4);
        threads.forEach(Thread::start);
    }

}

class ThreadB implements Runnable {
    private int count;

    public void run() {
        while (true) {
            count++;
            System.out.println(count);
        }
    }
}