package cn.analysys.semaphore;

import java.util.concurrent.*;

/**
 * @author shenlongguang<https://github.com/ifengkou>
 * @date: 2020/8/5
 */
public class SemaphoreTest {

    public static void main(String[] args){



        //并发数
        int concurrentNumb = 5;
        //假设有50辆车准备停入停车场
        int carNumb = 50;

        /**
         * Semaphore两个重要的方法就是
         * semaphore.acquire() 请求一个信号量，这时候的信号量个数-1（一旦没有可使用的信号量，也即信号量个数变为负数时，再次请求的时候就会阻塞，直到其他线程释放了信号量）
         * semaphore.release() 释放一个信号量，此时信号量个数+1
         */
        Semaphore mSemaphore = new Semaphore(concurrentNumb,true);

        ExecutorService executor = Executors.newFixedThreadPool(carNumb);

        for (int i = 1; i <= carNumb; i++) {
            executor.submit(new Guard("No."+i,mSemaphore));
        }

        executor.shutdown();
    }
}
