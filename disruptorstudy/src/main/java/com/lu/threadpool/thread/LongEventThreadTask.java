package com.lu.threadpool.thread;

public class LongEventThreadTask implements Runnable {

    private String command;

    public LongEventThreadTask(String command) {
        this.command = command;
    }

    public void run() {
        System.out.println(Thread.currentThread().getName() + String.format("：开始执行代号为【%s】的命令！", command));
        Sleep();
        System.out.println(Thread.currentThread().getName() + String.format("：【%s】的命令!执行完成！", command));
    }

    public static void Sleep() {
        Sleep(5000);
    }

    public static void Sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
