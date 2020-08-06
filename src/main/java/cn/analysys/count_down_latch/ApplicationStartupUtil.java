package cn.analysys.count_down_latch;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author shenlongguang<https://github.com/ifengkou>
 * @date: 2020/8/5
 */
@Slf4j
public class ApplicationStartupUtil {
    private static List<BaseHealthChecker> _services;

    private static CountDownLatch _latch;

    private ApplicationStartupUtil()
    {
    }

    private final static ApplicationStartupUtil INSTANCE = new ApplicationStartupUtil();

    public static ApplicationStartupUtil getInstance()
    {
        return INSTANCE;
    }

    public static boolean checkExternalServices() throws Exception
    {
        //初始化CountDownLatch
        // **
        // 构造器中的计数值（count）实际上就是闭锁需要等待的线程数量。
        // 这个值只能被设置一次，而且CountDownLatch没有提供任何机制去重新设置这个计数值。
        _latch = new CountDownLatch(3);

        AtomicInteger atomic = new AtomicInteger();

        //将检测器加到list中来
        _services = new ArrayList<>();
        _services.add(new NetworkHealthChecker(_latch,atomic));
        _services.add(new CacheHealthChecker(_latch,atomic));
        _services.add(new DatabaseHealthChecker(_latch,atomic));

        //定义一个线程池
        ExecutorService executor = Executors.newFixedThreadPool(_services.size());
        for(final BaseHealthChecker v : _services)
        {
            executor.execute(v);
        }

        //等待所有 检测器执行完成
        // **
        // 主线程必须在启动其他线程后立即调用CountDownLatch.await()方法。
        // 这样主线程的操作就会在这个方法上阻塞，直到其他线程完成各自的任务。
        //_latch.await();
        _latch.await(6, TimeUnit.SECONDS);

        log.info(String.format("checker is running off ,latch of count is %s , atomic = %s ",_latch.getCount(),atomic.get()));

        executor.shutdown();
        // 如果所有检测器 顺利检测完成，则返回true，否则 false
        for(final BaseHealthChecker v : _services)
        {
            if( ! v.isServiceUp())
            {
                return false;
            }
        }
        return true;
    }
}
