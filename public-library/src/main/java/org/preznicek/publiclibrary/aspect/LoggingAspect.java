package org.preznicek.publiclibrary.aspect;

import java.util.Date;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.preznicek.publiclibrary.model.formbean.BaseFormBean;

@Aspect
public class LoggingAspect {
	
	private final Logger logger = Logger.getLogger("public-library");

	private long start;
	private long end;
	
	// execution oznacuje pattern pro nalezeni dane metody
	// within oznacuje pattern pro nalezeni vsech metod dane tridy
	// args oznacuje pattern pro nalezeni vsech metod s danymi argumenty
	
	// vsechny public gettery s zadnym nebo vice argumenty
//	@Before(value="execution(public * get*(..))")
	// vsechny public gettery s jednim nebo vice argumenty
//	@Before(value="execution(public * get*(*))")
	// vsechny public gettery bez argumentu
//	@Before(value="execution(public * get*())")
	// vsechny public metody home(), ktere vraci typ ModelAndView
//	@Before(value="execution(public ModelAndView home())")
	// vsechny public metody home() bez parametru v HomeController, ktere vraci typ ModelAndView
//	@Before(value="execution(public ModelAndView org.preznicek.publiclibrary.controller.HomeController.home())")
	// vsechny metody v jakemkoliv controlleru
//	@Before(value="execution(* * org.preznicek.publiclibrary.controller.*Controller.*(..))")
//	@Before(value="within(org.preznicek.publiclibrary.controller.*Controller)")
//	public void loggingAdvice() {
//		System.out.println("toto je logging advice");
//	}
	
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
	
	@Before("@annotation(org.preznicek.publiclibrary.aspect.annotation.HomeMethod)")
	public void homeMethodAdvice() {
		logger.info("first method of the application - just for demonstration of @annotation");
	}
	
	@AfterThrowing(pointcut="allControllerMethods() || allServiceMethods() || allDaoMethods()", throwing="e")
	public void errorAdvice(JoinPoint joinPoint, Exception e) {
		logger.error("An error occured in " + joinPoint.getSignature());
		e.printStackTrace();
	}
	
	@Pointcut(value="within(org.preznicek.publiclibrary.controller.*Controller)")
	public void allControllerMethods() {}
	
	@Pointcut(value="within(org.preznicek.publiclibrary.database.service.*)")
	public void allServiceMethods() {}
	
	@Pointcut(value="within(org.preznicek.publiclibrary.database.dao.*)")
	public void allDaoMethods() {}
	
}
