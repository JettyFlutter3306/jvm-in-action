package cn.element.heap;

/**
 * 演示堆内存
 * 27讲
 */
public class HeapMemory {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("1....");

        Thread.sleep(30000);

        byte[] array = new byte[1024 * 1024 * 10];  //10MB

        Thread.sleep(30000);

        array = null;

        System.gc();
        System.out.println("3...");
        Thread.sleep(1000000L);

    }

}
