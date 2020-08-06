package cn.analysys.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * 停车场守卫
 * @author shenlongguang<https://github.com/ifengkou>
 * @date: 2020/8/6
 */
@Slf4j
public class Guard2 implements Runnable {

    private String carNo;

    public Guard2(String carNo) {
        this.carNo = carNo;
    }

    @Override
    public void run() {
        log.info("{} 进来了====>",carNo);
        try {
            Thread.sleep(1000*(new Random()).nextInt(5));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("{} 出去了",carNo);
    }
}
