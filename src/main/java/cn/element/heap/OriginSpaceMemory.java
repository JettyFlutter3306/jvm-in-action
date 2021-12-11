package cn.element.heap;

import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.Opcodes;

/**
 * 演示元空间内存的溢出
 * -XX:MaxMetaspaceSize=8m
 */
public class OriginSpaceMemory extends ClassLoader {

    public static void main(String[] args) {
        int j = 0;

        OriginSpaceMemory memory = new OriginSpaceMemory();

        //Error occurred during initialization of VM
        //MaxMetaspaceSize is too small.
        try {
            for (int i = 0; i < 10000; i++, j++) {
                //ClassWriter作用是生成类的二进制字节码
                ClassWriter writer = new ClassWriter(0);

                //版本号 public 类名 包名 父类名 接口名
                writer.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "Class" + i, null, "java/lang/Object", null);

                //返回字节码的byte型数组
                byte[] code = writer.toByteArray();

                //执行了类的加载
                memory.defineClass("Class" + i, code, 0, code.length);
            }
        } finally {
            System.out.println(j);
        }
    }

}
