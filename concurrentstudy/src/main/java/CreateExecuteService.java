import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CreateExecuteService {

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(2, new MyThreadFactory());
        ArrayList<Calculator> calculators = Lists.newArrayList(Calculator.newInstance(1, 100), Calculator.newInstance(1, 1000), Calculator.newInstance(1, 2000), Calculator.newInstance(1, 3000), Calculator.newInstance(1, 4000));
        List<Future<Integer>> futures = executorService.invokeAll(calculators);
        for (Future<Integer> future : futures) {
            Integer integer = future.get();
            System.out.println(future.getClass().getName() + integer);
        }
        executorService.shutdown();
    }

}

class Calculator implements Callable<Integer> {
    private volatile int start, end;

    public static Calculator newInstance(int start, int end) {
        return new Calculator(start, end);
    }

    private Calculator(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = start; i < end; i++) {
            sum += i;
        }
        System.out.println(String.format("%s计算了:%d到%d的和，计算结果为%d", Thread.currentThread().getName(), start, end, sum));
        return sum;
    }
}

class MyThreadFactory implements ThreadFactory {
    private volatile int count = 1;

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setName("计算器" + count++);
        return thread;
    }
}