package org.example.javaagent.agentmain;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

// instrument是JVM提供的一个可以修改已加载类的类库，专门为Java语言编写的插桩服务提供支持。
// 它需要依赖JVMTI的Attach API机制实现，
// 在JDK 1.6以前，instrument只能在JVM刚启动开始加载类时生效
// 而在JDK 1.6之后，instrument支持了在运行时对类定义的修改。
// 要使用instrument的类修改功能，我们需要实现它提供的ClassFileTransformer接口，定义一个类文件转换器。
// 接口中的transform()方法会在类文件被加载时调用，
// 而在transform方法里，我们可以利用ASM或Javassist对传入的字节码进行改写或替换，生成新的字节码数组后返回。
public class TestTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) {
        System.out.println("Transforming " + className);
        try {
            // 利用Javassist对Base类中的process()方法进行增强
            ClassPool cp = ClassPool.getDefault();
            CtClass cc = cp.get("org.example.javaagent.agentmain.Base");
            CtMethod m = cc.getDeclaredMethod("process");
            m.insertBefore("{ System.out.println(\"start\"); }");
            m.insertAfter("{ System.out.println(\"end\"); }");
            return cc.toBytecode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}