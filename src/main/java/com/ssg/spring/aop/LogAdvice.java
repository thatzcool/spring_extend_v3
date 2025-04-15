package com.ssg.spring.aop;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log4j2
public class LogAdvice {

  @Around("execution(* com.ssg.spring.service.*Service.*(..))")
  public Object logTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{

    long start = System.currentTimeMillis();

    Object result = proceedingJoinPoint.proceed();

    long end = System.currentTimeMillis();

    long gap = end - start;

    log.info("-------------------------");
    log.info(proceedingJoinPoint.getTarget());
    log.info(proceedingJoinPoint.getSignature());
    log.info("TIME: " + gap);

    if(gap > 100){
      log.warn("-----------------WARN-------------");
    }

    return result;
  }

}
