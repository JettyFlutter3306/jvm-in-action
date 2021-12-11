package cn.element.gc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 判断对象是否可以被回收的算法:
 * 1.引用计数器法:
 *      对象每被引用一次,那么这个对象的引用计数器就加1,引用结束之后就会减1,
 *      引用计数器为0的时候那么就可以被回收,但是这其中会存在很严重的问题,
 *      循环引用的问题,比如A引用B,B引用A.Python虚拟机使用的是引用计数器法
 *      而Java虚拟机使用的是可达性算法
 * 2.可达性引用算法:
 *
 *
 * 使用jmap -dump:format=b,live,file=1.bin 11111命令
 * 可以抓取进程当前的状态,然后MAT分析工具对bin文件进行分析
 * 最后就可以清晰地看到GC Roots的分布
 *
 */
public class GCRootsDemo {

    public static void main(String[] args) throws IOException {
        List<String> list1 = new ArrayList<>();
        list1.add("a");
        list1.add("b");
        list1.add("c");

        System.out.println(1);
        System.in.read();

        list1 = null;
        System.out.println(2);
        System.in.read();
        System.out.println("end...");
    }
}
