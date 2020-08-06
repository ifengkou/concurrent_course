package cn.analysys.count_down_latch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 负责所有特定的外部服务健康的检测，抽象类，Runnable
 * @author shenlongguang<https://github.com/ifengkou>
 * @date: 2020/8/5
 */
public abstract class BaseHealthChecker implements Runnable {
    private CountDownLatch _latch;
    private AtomicInteger _atomic;
    private String _serviceName;
    private boolean _serviceUp;

    /**
     * 需要将latch 传进来，这样线程就可以进行 countDown操作
     * @param serviceName
     * @param latch
     */
    public BaseHealthChecker(String serviceName, CountDownLatch latch,AtomicInteger atomic)
    {
        super();
        this._latch = latch;
        this._serviceName = serviceName;
        this._serviceUp = false;
        this._atomic = atomic;
    }
    @Override
    public void run() {
        try {
            verifyService();
            _serviceUp = true;
        } catch (Throwable t) {
            t.printStackTrace(System.err);
            _serviceUp = false;
        } finally {
            // **
            // 不管是否完成任务，最终要调用countDown，不然主线程就会一直在wait
            if(_latch != null) {
                _latch.countDown();
            }
            if(_atomic!=null){
                _atomic.incrementAndGet();
            }
        }
    }

    public String getServiceName() {
        return _serviceName;
    }

    public boolean isServiceUp() {
        return _serviceUp;
    }

    /**
     * 将检测服务抽象出来，各个子类复制特定的检测服务
     */
    public abstract void verifyService();
}
