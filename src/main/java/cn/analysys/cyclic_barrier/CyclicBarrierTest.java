package cn.analysys.cyclic_barrier;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.*;

/**
 *  赛跑时，等待所有人都准备好时，才起跑：
 * @author shenlongguang<https://github.com/ifengkou>
 * @date: 2020/8/5
 */
@Slf4j
public class CyclicBarrierTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        //如果将参数改为4，但是下面只加入了3个选手，这永远等待下去
        int numb = 10;
        CyclicBarrier barrier = new CyclicBarrier(5);

        ExecutorService executor = Executors.newFixedThreadPool(numb);

        for (int i = 1; i <= numb; i++) {
            executor.submit(new Runner(barrier, " No."+i));
        }

        executor.shutdown();
    }
}
