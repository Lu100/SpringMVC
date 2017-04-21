public class ThreadCommunication {
    public static void main(String[] args) {
        Data data = new Data();
        Consumer consumer = new Consumer(data);
        Provider provider = new Provider(data);
        consumer.setName("消费者");
        provider.setName("生产者");
        consumer.start();
        provider.start();
    }
}

class Data {
    private int count;

    public synchronized void add() {
        count++;
        System.out.println(Thread.currentThread().getName() + ":生产了" + count);
        if (count % 5 == 0) notifyAll();
    }

    public synchronized void sum() {
        if (count < 5) try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count -= 5;
        System.out.println(Thread.currentThread().getName() + ":数据已经被消费->现有" + count);
    }
}

class Consumer extends Thread {

    private final Data data;

    public Consumer(Data data) {
        this.data = data;
    }

    @Override
    public void run() {
        while (true)
            data.sum();
    }
}

class Provider extends Thread {

    private final Data data;

    public Provider(Data data) {
        this.data = data;
    }

    @Override
    public void run() {
        while (true)
            data.add();
    }

}
