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
            int availNum = mSemaphore.availablePermits();
            if(availNum ==0) {
                System.out.println(System.currentTimeMillis() +  carNo + "排队，当前还有:" + availNum + "个许可证");
            }
            mSemaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() +carNo+"进来了====>");

        try {
            Thread.sleep(1000*(new Random()).nextInt(5));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() +carNo+"出去了");
        mSemaphore.release();
    }
}
