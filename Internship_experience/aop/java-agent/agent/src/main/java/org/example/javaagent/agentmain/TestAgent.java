package org.example.javaagent.agentmain;

import java.lang.instrument.Instrumentation;

// 定义一个Agent，借助Agent的能力将Instrument注入到JVM中。
// Agent中用到的类Instrumentation。在JDK 1.6之后，Instrumentation可以做启动后的Instrument、本地代码（Native Code）的Instrument，以及动态改变Classpath等等。
// 我们可以向Instrumentation中添加上文中定义的Transformer，并指定要被重加载的类，代码如下所示。
// 这样，当Agent被Attach到一个JVM中时，就会执行类字节码替换并重载入JVM的操作。
public class TestAgent {
    // agentmain可以在代码运行后动态修改已经加载的类，进行字节码增强
    // 也是在类加载之后对类做修改
    public static void agentmain(String args, Instrumentation inst) {
        //指定我们自己定义的Transformer，在其中利用Javassist做字节码替换
        inst.addTransformer(new TestTransformer(), true);
        try {
            //重定义类并载入新的字节码
            inst.retransformClasses(Class.forName("org.example.javaagent.agentmain.Base"));
            System.out.println("Agent Load Done.");
        } catch (Exception e) {
            System.out.println("agent load failed!");
        }
    }
}
