package aop.aoppractice.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("execution(* aop.aoppractice.service.*.*(..))")
    public Object logAndMeasure(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime();

        logger.info("Method start: {}", joinPoint.getSignature());

        Object result = joinPoint.proceed();

        long duration = (System.nanoTime() - start) / 1_000_000;
        logger.info("Execution time: {} ms", duration);

        return result;
    }
}