import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * 任务拆分模型
 * 以1-100000000的计算为例，将该计算任务拆分成为多个子任务，交予新线程执行，最后合并
 * 该实例仅作为任务拆分模型，并不作计算效率统计
 */
public class CreateForkJoinModel {

    public static final long CAPITAL = Integer.MAX_VALUE >> 2;

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        try {

            ForkJoinTask<Long> submit = forkJoinPool.submit(new CalculatorTask(1, CAPITAL));
            long start = System.currentTimeMillis();
            Long result = submit.get();
            long end = System.currentTimeMillis();
            System.out.println("任务拆分计算结果为：" + result);
            System.out.println("任务拆分计算耗时为：" + (end - start) + ",一共拆分了" + CalculatorTask.count + "次");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        long start = System.currentTimeMillis();

        long sum = 0;
        for (long i = 0; i <= CAPITAL; i++) {
            sum += i;
        }
        System.out.println("单线程计算结果为:" + sum);
        long end = System.currentTimeMillis();
        System.out.println("单线程计算耗时为:" + (end - start));
    }

}

class CalculatorTask extends RecursiveTask<Long> {

    public static int count = 0;

    //任务拆分的阈值
    private static final int CALCULATOR_CAPITAL = 1 << 10;

    private final long start, end;

    public CalculatorTask(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long result = 0;
        if (end - start <= CALCULATOR_CAPITAL) {
            result = doCalculator(start, end);
        } else {
            try {
                result = forkTask(start, end);
            } catch (ExecutionException | InterruptedException e) {
                System.err.println("线程执行发生异常!");
            }
        }
        return result;
    }

    private long doCalculator(long start, long end) {
        long sum = 0;
        for (long i = start; i <= end; i++) {
            sum += i;
        }
        return sum;
    }

    private long forkTask(long start, long end) throws ExecutionException, InterruptedException {
        count++;
        long middle = start + end;
        middle /= 2;
        Long left = new CalculatorTask(start, middle).fork().get();
        Long right = new CalculatorTask(middle + 1, end).fork().get();
//        String name = Thread.currentThread().getName();
//        System.out.println(String.format("线程【%s】将任务(%d-%d)拆分(%d+%d)+(%d+%d)=%d", name, start, end, start, middle, middle + 1, end, left + right));
        return left + right;
    }
}


