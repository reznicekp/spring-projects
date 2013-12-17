package org.preznicek.vehiclesregistration.aspect;

import java.util.Date;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.preznicek.vehiclesregistration.model.formbean.BaseFormBean;

@Aspect
public class LoggingAspect {
	
	private final Logger logger = Logger.getLogger("vehicles-registration");

	private long start;
	private long end;
	
	@Before(value="allControllerMethods()")
	public void controllerAdvice(JoinPoint joinPoint) {
		logger.debug(joinPoint.getSignature());
		
		Object[] args = joinPoint.getArgs();
		for (Object arg : args) {
			if (arg instanceof BaseFormBean) {
				logger.debug(arg);
			}
		}
	}
	
	@Before("allServiceMethods()")
	public void serviceAdvice(JoinPoint joinPoint) {
		logger.debug(joinPoint.getSignature());
	}
	
	@Before("allDaoMethods()")
	public void beforeDaoAdvice(JoinPoint joinPoint) {
		logger.debug(joinPoint.getSignature());
		start = new Date().getTime();
	}
	
	@After("allDaoMethods()")
	public void afterDaoAdvice() {
		end = new Date().getTime();
		long duration = end - start;
		logger.info("DAO duration: " + duration + " ms");
	}
	
	@AfterThrowing(pointcut="allControllerMethods() || allServiceMethods() || allDaoMethods()", throwing="e")
	public void errorAdvice(JoinPoint joinPoint, Exception e) {
		logger.error("An error occured in " + joinPoint.getSignature());
		e.printStackTrace();
	}
	
	@Pointcut(value="within(org.preznicek.vehiclesregistration.controller.*Controller)")
	public void allControllerMethods() {}
	
	@Pointcut(value="within(org.preznicek.vehiclesregistration.database.service.*)")
	public void allServiceMethods() {}
	
	@Pointcut(value="within(org.preznicek.vehiclesregistration.database.dao.*)")
	public void allDaoMethods() {}
	
}
