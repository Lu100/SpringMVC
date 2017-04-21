import java.util.Scanner;
import java.util.concurrent.Exchanger;

public class CreateExchange {

    public static void main(String[] args) throws InterruptedException {
        Exchanger<String> exchanger = new Exchanger<>();
        Chat1 chat1 = new Chat1(exchanger);
        chat1.start();
        String message = new Scanner(System.in).nextLine();
        String result = exchanger.exchange(message);
        System.out.println("Main:" + result);
    }

}


class Chat1 extends Thread {
    private final Exchanger<String> stringExchanger;

    public Chat1(Exchanger<String> stringExchanger) {
        this.stringExchanger = stringExchanger;
    }

    @Override
    public void run() {
        try {
            String message = stringExchanger.exchange("Hello");
            System.out.println(getName()+":" + message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}



