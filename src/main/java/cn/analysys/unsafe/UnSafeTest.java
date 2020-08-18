package cn.analysys.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author shenlongguang<https://github.com/ifengkou>
 * @date: 2020/8/11
 */
public class UnSafeTest {
    public static void main(String[] args){
        /*Unsafe unsafe = Unsafe.getUnsafe();
        System.out.println(unsafe);*/

        Field theUnsafeField = null;
        try {
            theUnsafeField = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafeField.setAccessible(true);
            Unsafe unsafe = (Unsafe) theUnsafeField.get(null);
            System.out.println(unsafe);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
