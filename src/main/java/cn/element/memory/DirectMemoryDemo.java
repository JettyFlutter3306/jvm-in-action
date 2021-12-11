package cn.element.memory;

import sun.misc.Unsafe;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;

public class DirectMemoryDemo {

    private static final int M100 = 1024 * 1024 * 100;
    private static final int G1 = 1024 * 1024 * 1024;

    public static void testByteBuffer() throws IOException {
        ByteBuffer buffer = ByteBuffer.allocateDirect(M100);

        System.out.println("分配完毕...");
        System.in.read();
        System.out.println("开始释放...");
        buffer = null;

        System.gc();
        System.in.read();
    }

    public static void testUnsafe() throws IOException {
        Unsafe unsafe = getUnsafe();
        long base = unsafe.allocateMemory(G1);
        unsafe.setMemory(base, G1, (byte) 0);

        System.in.read();
        unsafe.freeMemory(base);
        System.in.read();
    }

    private static Unsafe getUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);

            return (Unsafe) field.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) throws IOException {
//        testByteBuffer();
        testUnsafe();
    }

}
