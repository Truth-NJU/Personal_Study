package org.example.javaagent.premain;

import java.lang.instrument.Instrumentation;

public class Agent {


    // premain运行前静态修改字节码，随Java进程启动而启动，经常见到的java -agentlib就是这种方式
    public static void premain(String agentArgs, Instrumentation instrumentation)  {
//        new AgentBuilder()
        instrumentation.addTransformer(new ClassFileTransformerDemo());

    }

}
