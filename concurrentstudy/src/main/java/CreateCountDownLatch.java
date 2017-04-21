import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Administrator on 2017/4/21.
 */
public class CreateCountDownLatch {

    public static void main(String[] args) {
        //同步栓,初始化3个
        CountDownLatch countDownLatch = new CountDownLatch(3);

        Racer jack = new Racer(countDownLatch, "jack");
        Racer tom = new Racer(countDownLatch, "tom");
        Racer marry = new Racer(countDownLatch, "marry");
        List<Racer> Racers = Lists.newArrayList(jack, tom, marry);

        Racers.forEach(CreateCountDownLatch::execute);
    }

    public static void execute(Racer racer) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        racer.start();
        //使得同步栓信号量减一，当到达0时唤醒所有使用该同步栓等待的线程
        racer.getCountDownLatch().countDown();
    }
}

class Racer extends Thread {

    private final CountDownLatch countDownLatch;

    public Racer(CountDownLatch countDownLatch, String name) {
        this.countDownLatch = countDownLatch;
        setName(name);
    }

    @Override
    public void run() {
        System.out.println(getName() + ":" + "已经准备好,正在等待信号");
        try {
            //等待同步栓放行
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 1; i < 5; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(getName() + "已经到达" + i * 100 + "米的位置");
        }
        System.out.println(getName() + "已经完成比赛！");
    }

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }
}
