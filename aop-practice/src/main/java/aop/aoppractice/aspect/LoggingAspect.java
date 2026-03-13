package aop.aoppractice.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Around("execution(* aop.aoppractice.service.*.*(..))")
    public Object logAndMeasure(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        System.out.println("Method start: " + joinPoint.getSignature());

        Object result = joinPoint.proceed();

        long end = System.currentTimeMillis();
        long duration = end - start;

        System.out.println("Method end: " + joinPoint.getSignature() + " | duration = " + duration + " ms");

        return result;
    }
}