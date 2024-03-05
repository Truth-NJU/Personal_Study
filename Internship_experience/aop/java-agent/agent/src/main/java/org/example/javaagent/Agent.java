package org.example.javaagent;

import net.bytebuddy.agent.builder.AgentBuilder;

import java.lang.instrument.Instrumentation;

public class Agent {



    public static void premain(String agentArgs, Instrumentation instrumentation)  {
//        new AgentBuilder()
        instrumentation.addTransformer(new ClassFileTransformerDemo());

    }

}
