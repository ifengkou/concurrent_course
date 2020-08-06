package cn.analysys.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author shenlongguang<https://github.com/ifengkou>
 * @date: 2020/8/6
 */
public class WorkStealingThreadPoolTest {
    public static void main(String[] args){
        //并发数
        int concurrentNumb = 5;
        //假设有50辆车准备停入停车场
        int carNumb = 50;

        ExecutorService executor = Executors.newWorkStealingPool(concurrentNumb);

        for (int i = 1; i <= carNumb; i++) {
            executor.submit(new Thread(new Guard2("No."+i)));
        }

        executor.shutdown();
    }
}
