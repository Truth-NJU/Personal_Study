package org.example.javaagent.agentmain;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws AgentLoadException, IOException, AgentInitializationException, AttachNotSupportedException {
        // 先运行Base类的main方法，得到其对应的jvm pid，再修改下面的VirtualMachine.attach("")中的参数
        // 最后再运行该main方法对运行时类的重新加载，实现运行时的字节码增强
        VirtualMachine vm = VirtualMachine.attach("14145");
        vm.loadAgent("/Users/taozehua/Downloads/技术学习/Personal_Study/Internship_experience/aop/java-agent/agent/target/agent-jar-with-dependencies.jar");
    }
}
