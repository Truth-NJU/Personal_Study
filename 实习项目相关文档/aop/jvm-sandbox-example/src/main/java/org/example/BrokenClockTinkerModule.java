package org.example;

import com.alibaba.jvm.sandbox.api.Information;
import com.alibaba.jvm.sandbox.api.Module;
import com.alibaba.jvm.sandbox.api.ProcessController;
import com.alibaba.jvm.sandbox.api.annotation.Command;
import com.alibaba.jvm.sandbox.api.listener.ext.Advice;
import com.alibaba.jvm.sandbox.api.listener.ext.AdviceListener;
import com.alibaba.jvm.sandbox.api.listener.ext.EventWatchBuilder;
import com.alibaba.jvm.sandbox.api.resource.ModuleEventWatcher;
import org.kohsuke.MetaInfServices;

import javax.annotation.Resource;

@MetaInfServices(Module.class)
@Information(id = "broken-clock-tinker") // 模块对应的名字
public class BrokenClockTinkerModule implements Module {

    @Resource
    private ModuleEventWatcher moduleEventWatcher;

    @Command("repairCheckState")
    public void repairCheckState() {

        // new EventWatchBuilder(moduleEventWatcher)构造一个事件观察者的构造器，通过Builder我们可以方便的构造出我们的观察者。
        new EventWatchBuilder(moduleEventWatcher)
                /**
                 * 以类的名字进行筛选
                 * 对于一些类需要加上includeBootstrap().
                 * 如onClass("java.net.SocketInputStream").includeBootstrap()
                 * 因为java.net.SocketInputStream在JDK中，由BootstrapClassLoader负责加载，默认情况下是不会检索这个ClassLoader所加载的类。
                 * 所以必须带上includeBootstrap()明确下类的检索范围。
                 */
                .onClass("org.example.Clock")
                /**
                 * 对上一环节onClass()所匹配的类进行方法级的筛选。
                 * 在JDK中，是严格区分构造方法和普通方法的，
                 * 但实际使用上，我们可以把他们两个都抽象为类的行为（Behavior）
                 * 其中构造方法的方法名为<init>，普通方法的方法名保持不变。
                 */
                .onBehavior("checkState")
                /**
                 * 可以带上.withParameterTypes(xxxx)
                 * 可以对方法根据参数的匹配作进一步的筛选
                 * 如.withParameterTypes(byte[].class, int.class, int.class)中，方法的参数必须为byte、int、int类型
                 */
                .onWatch(new AdviceListener() {

                    /**
                     * 拦截{@code com.taobao.demo.Clock#checkState()}方法，当这个方法抛出异常时将会被
                     * AdviceListener#afterThrowing()所拦截
                     */
                    @Override
                    protected void afterThrowing(Advice advice) throws Throwable {

                        // 在此，你可以通过ProcessController来改变原有方法的执行流程
                        // 这里的代码意义是：改变原方法抛出异常的行为，变更为立即返回；void返回值用null表示
                        ProcessController.returnImmediately(null);
                    }
                });

    }

}