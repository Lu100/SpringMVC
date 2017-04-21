import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by Administrator on 2017/4/21.
 */
public class CreateCyclicBarrier {
    public static void main(String[] args) {
        //执行屏障 ，指定需要等待的数量,当cyclicBarrier被执行3次之后，cyclicBarrier的锁开始解放，让所有通过该对象等待的线程开始执行
        //初始化方法也可以不指定屏障完成后执行的线程，只作为3个线程的发令者
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new Game());
        new Player(cyclicBarrier, "A").start();
        new Player(cyclicBarrier, "B").start();
        new Player(cyclicBarrier, "C").start();
    }
}


class Player extends Thread {

    private final CyclicBarrier cyclicBarrier;

    public Player(CyclicBarrier cyclicBarrier, String name) {
        this.cyclicBarrier = cyclicBarrier;
        setName(name);
    }

    @Override
    public void run() {
        System.out.println(getName() + "进入房间");
        try {
            Thread.sleep(500);
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println(getName() + "完成了游戏");
    }

}

class Game extends Thread {
    public void run() {
        System.out.println("Game start");
    }
}