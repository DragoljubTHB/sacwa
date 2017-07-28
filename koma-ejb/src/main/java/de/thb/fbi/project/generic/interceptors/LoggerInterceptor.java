package de.thb.fbi.project.generic.interceptors;


import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.slf4j.Logger;

@Interceptor
@Loggable
public class LoggerInterceptor {

	@Inject private Logger logger;
	
	@AroundInvoke
	private Object intercept(InvocationContext ic) throws Exception{
		logger.info(">>>: {} : {}", ic.getTarget().getClass().getName(), ic.getMethod().getName());
		try{
			return ic.proceed();
		}finally {
			logger.info("<<<: {} : {}", ic.getTarget().getClass().getName(), ic.getMethod().getName());
		}
	}
}
