package cn.analysys.semaphore;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * 停车场守卫
 * @author shenlongguang<https://github.com/ifengkou>
 * @date: 2020/8/6
 */
@Slf4j
public class Guard implements Runnable {

    private String carNo;
    private Semaphore mSemaphore;

    public Guard(String carNo,Semaphore mSemaphore) {
        this.mSemaphore = mSemaphore;
        this.carNo = carNo;
    }

    @Override
    public void run() {
        try {
            mSemaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("{} 进来了====>",carNo);
        try {
            Thread.sleep(1000*(new Random()).nextInt(5));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("{} 出去了",carNo);
        mSemaphore.release();
    }
}
