package cn.element.gc;

/**
 * 垃圾回收算法分析
 *
 * 1.标记-清除: 速度较快,但是会产生很多内部碎片
 *
 * 2.标记-整理: 速度慢,但是不会产生内部碎片
 *
 * 3.复制: 不会有内存碎片,但是需要占用双倍的内存空间
 *
 * 相关VM参数
 *
 * 含义                       参数
 * 堆初始大小                -Xms
 * 堆最大大小                -Xmx / -XX:MaxHeapSize=size
 * 新生代大小                -Xmn / -XX:NewSize=size + -XX:MaxNewSize=size
 * 幸存区比例(动态)          -XX:InitialSurvivorRatio=ratio -XX:+UseAdaptiveSizePolicy
 * 幸存区比例                -XX:SurvivorRatio=ratio
 * 晋升阈值                  -XX:MaxTenuringThreshold=threshold
 * 晋升详情                  -XX:+PrintTenuringDistribution
 * GC详情                   -XX:+PrintGCDetails -verbose:gc
 * FullGC前MinorGC          -XX:+ScavengeBeforeFullGC
 */
public class CollectionStrategyDemo {

    public static void main(String[] args) {

    }
}
