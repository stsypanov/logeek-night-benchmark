package com.luxoft.logeek.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Slf4j
@Aspect
@Component
public class ServiceAspect {

	@Pointcut("execution(* com.luxoft.logeek.service.impl.*.*(..))")
	public void serviceMethod() {
	}


	@Around(value = "serviceMethod()", argNames = "pjp")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		if (log.isDebugEnabled()) {
			StopWatch stopWatch = new StopWatch();
			stopWatch.start();
			try {
				return pjp.proceed();
			} finally {
				log.debug("{} took {} to execute", pjp.getSignature().getName(), stopWatch.getTotalTimeMillis());
			}
		} else {
			return pjp.proceed();
		}
	}
}
