public class CreateThread {

    public static void main(String[] args) {
        ThreadA threadA = new ThreadA();
        threadA.start();

        while (true)
            System.out.println("Main");
    }
}


class ThreadA extends Thread {
    @Override
    public void run() {
        while (true) {
            System.out.println(Thread.currentThread().getName() + "A");
        }
    }
}
