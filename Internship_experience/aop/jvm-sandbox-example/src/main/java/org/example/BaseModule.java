package org.example;

import com.alibaba.jvm.sandbox.api.Information;
import com.alibaba.jvm.sandbox.api.Module;
import com.alibaba.jvm.sandbox.api.annotation.Command;
import com.alibaba.jvm.sandbox.api.listener.ext.Advice;
import com.alibaba.jvm.sandbox.api.listener.ext.AdviceListener;
import com.alibaba.jvm.sandbox.api.listener.ext.EventWatchBuilder;
import com.alibaba.jvm.sandbox.api.resource.ModuleEventWatcher;
import org.kohsuke.MetaInfServices;

import javax.annotation.Resource;

@MetaInfServices(Module.class)
@Information(id = "base-aop") // 模块对应的名字
public class BaseModule implements Module {

    @Resource
    private ModuleEventWatcher moduleEventWatcher;

    @Command("aop")
    public void baseAop() {
        new EventWatchBuilder(moduleEventWatcher)
                .onClass("org.example.Base")
                .onBehavior("process")
                .onWatching()
                .withLine()
                .onWatch(new AdviceListener() {
                    @Override
                    protected void beforeLine(Advice advice,int lineNum){
                        if(lineNum==5)
                            System.out.println("jvm sandbox before line " + lineNum);
                        if(lineNum==6)
                            System.out.println("jvm sandbox after line " + lineNum);
                    }
                });
    }
}
