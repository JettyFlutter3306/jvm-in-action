package cn.element.reference;

import java.io.IOException;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * 四种引用的概念
 *
 * 1.强引用: (StrongReference)
 *      只有所有GC Roots对象都不通过[强引用]引用该对象,该对象才能被垃圾回收
 *
 * 2.软引用: (SoftReference)
 *      1) 仅有软引用引用该对象,在垃圾回收之后,内存仍然不足时会再次触发垃圾回收回收软引用对象
 *      2) 可以配合引用队列来释放软引用自身
 *
 * 3.弱引用: (WeakReference)
 *      1) 仅有弱引用引用该对象,在垃圾回收时,无论内存是否充足,都会回收弱引用对象
 *      2) 可以配合引用队列来释放弱引用自身
 *
 * 4.虚引用: (PhantomReference)
 *      必须配合引用队列使用,主要配合ByteBuffer使用,被引用对象回收时,会将虚引用入队,由
 *      Reference Handler线程调用虚引用相关方法释放直接内存
 *
 * 5.终结器引用: (FinalReference)
 *      无需手动编码,但是其内部配合引用队列使用,在垃圾回收时,终结器引用入队(被引用对象暂时
 *      没有被回收),再由Finalizer线程通过终结器引用找到被引用对象并调用它finalize()方法,
 *      第二次GC时才能回收引用对象
 *
 * 演示软引用
 * -Xmx20m -XX:+PrintGCDetails -verbose:gc
 */
public class ReferenceDemo {

    public static final int _4MB = 4 * 1024 * 1024;

    public static void softReference() {
        //List ---> SoftReference ---> byte[]
        List<SoftReference<Byte[]>> list = new ArrayList<>();

        //使用引用队列来回收内存
        ReferenceQueue<Byte[]> queue = new ReferenceQueue<>();

        for (int i = 0; i < 5; i++) {
            //关联了引用队列,当软引用所关联的byte数组被回收时,软引用自己就会加入到queue中去
            SoftReference<Byte[]> reference = new SoftReference<>(new Byte[_4MB], queue);

            System.out.println(reference.get());
            list.add(reference);
            System.out.println(list.size());
        }

        //从队列中获取无用的软引用对象,并删除
        Reference<? extends Byte[]> reference = queue.poll();

        while (reference != null) {
            list.remove(reference);
            reference = queue.poll();
        }

        System.out.println("=========================");

        for (SoftReference<Byte[]> refer : list) {
            System.out.println(refer.get());
        }
    }

    public static void weakReference() {
        //List ---> WeakReference ---> byte[]
        List<WeakReference<Byte[]>> list = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            //关联了引用队列,当软引用所关联的byte数组被回收时,软引用自己就会加入到queue中去
            WeakReference<Byte[]> reference = new WeakReference<>(new Byte[_4MB]);
            list.add(reference);

            for (WeakReference<Byte[]> refer : list) {
                System.out.println(refer.get() + " ");
            }

            System.out.println();
        }

        System.out.println("循环结束: " + list.size());
    }

    public static void main(String[] args) throws IOException {
//        List<byte[]> list = new ArrayList<>();
//
//        for (int i = 0; i < 5; i++) {
//            list.add(new byte[_4MB]);
//        }
//
//        System.in.read();

//        softReference();
        weakReference();
    }
}
