package org.example.javaagent.premain;

import java.lang.instrument.Instrumentation;

// JVM 在类加载时候会先执行代理类的 premain 方法，再执行 Java 程序本身的 main 方法，这就是 premain 名字的来源。
// 在 premain 方法中可以对加载前的 class 文件进行修改。
public class Agent {
    // premain随Java进程启动而启动，经常见到的java -agentlib就是这种方式
    // 也是在类加载之后对类做修改
    public static void premain(String agentArgs, Instrumentation instrumentation)  {
//        new AgentBuilder()
        instrumentation.addTransformer(new ClassFileTransformerDemo());

    }

}
