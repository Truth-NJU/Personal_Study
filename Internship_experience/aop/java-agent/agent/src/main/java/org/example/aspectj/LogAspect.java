package org.example.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LogAspect {

    @Pointcut("execution(* *(..)) && @annotation(org.example.aspectj.Log)")
    public void logCut() {

    }


    @Around("logCut()")
    public Object process(ProceedingJoinPoint point) throws Throwable {
        //获取方法参数
        Object[] args = point.getArgs();
        //获取方法名
        String methodName = point.getSignature().getName();
        //获取所在类名
        String className = point.getTarget().getClass().getName();
        System.out.println("AOP:"+methodName);
        return point.proceed();
    }

}
