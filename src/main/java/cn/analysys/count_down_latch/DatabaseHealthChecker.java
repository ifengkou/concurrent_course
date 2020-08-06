package cn.analysys.count_down_latch;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 数据库健康检测
 * @author shenlongguang<https://github.com/ifengkou>
 * @date: 2020/8/5
 */
@Slf4j
public class DatabaseHealthChecker extends BaseHealthChecker {
    public DatabaseHealthChecker(CountDownLatch latch,AtomicInteger atomic) {
        super("数据库健康检查", latch,atomic);
    }

    @Override
    public void verifyService() {
        log.info("Checking {}",this.getServiceName());
        try {
            // 假设检测耗时 7秒
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("{} is UP",this.getServiceName());
    }
}
