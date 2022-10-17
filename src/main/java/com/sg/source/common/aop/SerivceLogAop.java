package com.sg.source.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class SerivceLogAop {

	@Pointcut("execution(* com.sg..impl.*Impl.*(..))")
	public void log() {}

	@Around("log()")
	public Object printLog(ProceedingJoinPoint joinPoint) throws Throwable {

		Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());

		String methodName = "[" + joinPoint.getSignature().toShortString() + "]";
		logger.debug(methodName + " Called.");

		Object[] args = joinPoint.getArgs();
		if (args != null && args.length > 0) {
			for (int i = 0; i < args.length; i++) {
				logger.debug(methodName + " argument[" + i + "] : " + ((args[i] != null) ? args[i].toString() : "null"));
			}
		}

		return joinPoint.proceed();
	}
}
