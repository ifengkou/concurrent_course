package cn.analysys.count_down_latch;

import lombok.extern.slf4j.Slf4j;

/**
 * @author shenlongguang<https://github.com/ifengkou>
 * @date: 2020/8/5
 */
@Slf4j
public class StartChecker {
    public static void main(String[] args)
    {
        boolean result = false;
        try {
            result = ApplicationStartupUtil.checkExternalServices();
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("所有外部服务检测完成 !! 检测结果是 :: "+ result);
        //System.exit(0);
    }
}
