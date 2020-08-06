package cn.analysys.threadpool;

import java.util.concurrent.*;

/**
 * @author shenlongguang<https://github.com/ifengkou>
 * @date: 2020/8/6
 */
public class ThreadPoolTest {
    public static void main(String[] args){
        //
        new Thread(new Runnable() {
            @Override
            public void run() {
            }
        }).start();


        /**
         * 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
         * 定长线程池的大小最好根据系统资源进行设置。如Runtime.getRuntime().availableProcessors()
         */
        ExecutorService executor1 = Executors.newFixedThreadPool(5);
        /**
         * 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程
         * 线程池为无限大，当执行第二个任务时第一个任务已经完成，会复用执行第一个任务的线程，而不用每次新建线程。
         *
         */
        ExecutorService executor2 = Executors.newCachedThreadPool();


        /**
         * 创建一个定长线程池，支持定时及周期性任务执行
         * 表示延迟3秒执行。
         * executor3.schedule(runnable,3,TimeUnit.SECONDS);
         * 表示延迟1秒后每3秒执行一次。
         * executor3.scheduleAtFixedRate(runnable
         */
        ScheduledExecutorService executor3 = Executors.newScheduledThreadPool(3);

        /**
         * 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行
         * 此线程池保证所有任务的执行顺序按照任务的提交顺序执行。
         */
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

        /**
         * 创建一个线程池，该线程池维护足够的线程以支持给定的并行级别，并且可以使用多个队列来减少争用。
         * 并行级别对应于主动参与或可用于任务处理的最大线程数。实际线程数可能会动态地增长和收缩。
         * 不能保证提交任务的执行顺序。
         */
        ExecutorService workStealingPool = Executors.newWorkStealingPool();

        /**
         * newFixedThreadPool、newCachedThreadPool、newSingleThreadExecutor
         */
        ExecutorService threadPool =
                new ThreadPoolExecutor(3, 3, 1000L, TimeUnit.MICROSECONDS,
                        new LinkedBlockingQueue<>());
    }
}
