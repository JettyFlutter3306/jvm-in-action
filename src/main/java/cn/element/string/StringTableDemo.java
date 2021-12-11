package cn.element.string;

/**
 * 演示StringTable的垃圾回收
 * -Xmx10m -XX:+PrintStringTableStatistics -XX:+PrintGCDetails -verbose:gc
 */
public class StringTableDemo {

    public static void main(String[] args) {
        int i = 0;

        try {
            for (int j = 0; j < 10000; j++) {
                String.valueOf(j).intern();
                i++;
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(i);
        }
    }
}
