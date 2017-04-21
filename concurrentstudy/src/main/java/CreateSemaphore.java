import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class CreateSemaphore {

    public static void main(String[] args) {
        //Semaphore（发号机）是一个信号量标识，可以用于模拟线程池的效果，比如声明为2时，同一时刻使用相同发号机只允许2个名额
        Semaphore semaphore = new Semaphore(2);
        //模拟客户
        Customer jack = new Customer(semaphore, "jack");
        Customer tom = new Customer(semaphore, "tom");
        Customer marry = new Customer(semaphore, "marry");
        ArrayList<Customer> customers = Lists.newArrayList(jack, tom, marry);
        customers.forEach(Thread::start);
    }

}

class Customer extends Thread {
    private final Semaphore semaphore;

    Customer(Semaphore semaphore, String name) {
        this.semaphore = semaphore;
        setName(name);
    }

    @Override
    public void run() {

        try {
            //等待发号，进入阻塞状态，当发号机发出命令，则开始执行
            semaphore.acquire();
        } catch (InterruptedException e) {

        }

        System.out.println(getName() + ":" + "开始执行");

        for (int i = 0; i < 4; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(getName() + ":" + i);
        }
        System.out.println(getName() + ":" + "执行完毕");

        semaphore.release();
    }
}


