package com.example.demo;

import java.lang.reflect.Modifier;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
@Scope("prototype")
public class TestAspect {
    private int i = 0;
    @Before("execution(* callMethod*())")
    public void before(){
        System.out.println("------- 我在你前面做 -------");
    }

    @After("execution(* callMethod*())")
    public void after(){
        System.out.println("------- 我在你後面做 -------");
    }

    // @Around("execution(* com.example.demo.TestClass.callMethod*(..))")
    @Around("execution(* callMethod*(..))")
    public Object doAroundAccessCheck(ProceedingJoinPoint pjp) throws Throwable {
        String className = pjp.getTarget().getClass().getSimpleName();
        String methodName = pjp.getSignature().getName();
        System.out.println("i:" + ++this.i);
        String stringRepeat = "---".repeat(i);
        System.out.println(String.format("┌" + stringRepeat + "------- %s:%s -------" + stringRepeat + "┐", className, methodName));
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        System.out.println("目標方法名為:" + pjp.getSignature().getName());
        System.out.println("目標方法所屬類的簡單類名:" +        pjp.getSignature().getDeclaringType().getSimpleName());
        System.out.println("目標方法所屬類的類名:" + pjp.getSignature().getDeclaringTypeName());
        System.out.println("目標方法聲明類型:" + Modifier.toString(pjp.getSignature().getModifiers()));
        //獲取傳入目標方法的參數
        Object[] args = pjp.getArgs();
        for (int i = 0; i < args.length; i++) {
            System.out.println("第" + (i+1) + "個參數為:" + args[i]);
            if(args[i] instanceof String[]){
                int j = 1;
                for (Object obj : (String[])args[i]) {
                    System.out.println("String[]第" + (j++) + "個參數為:" + (String)obj);
                }
            }
        }
        System.out.println("被代理的對象:" + pjp.getTarget());
        System.out.println("代理對象自己:" + pjp.getThis());
        // 开始
        Object retVal = pjp.proceed();
        stopWatch.stop();
        // 结束
        System.out.println("invoke method: " + pjp.getSignature().getName() + ", elapsed time: " + stopWatch.getTotalTimeMillis());
        System.out.println(String.format("└" + stringRepeat + "------- %s:%s -------" + stringRepeat + "┘", className, methodName));
        return retVal;
    }    
}
