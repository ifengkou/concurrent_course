package cn.analysys.volatile_test;

import cn.analysys.Printer;

/**
 * @author shenlongguang<https://github.com/ifengkou>
 * @date: 2020/8/12
 */
public class VolatileDemo {
    private static volatile boolean isOver = false;

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!isOver) {
                    // print 是同步方法，所以不能用
                    //Printer.print("========");
                }
            }
        });
        Printer.print("start ========");
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        isOver = true;
        Printer.print("Over========");
    }
}
