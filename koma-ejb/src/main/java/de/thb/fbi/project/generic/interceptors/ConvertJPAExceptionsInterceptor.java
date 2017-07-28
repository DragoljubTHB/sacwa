package de.thb.fbi.project.generic.interceptors;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.PersistenceException;

import org.slf4j.Logger;

@Interceptor
@ConvertJPAExceptions
public class ConvertJPAExceptionsInterceptor {

	@Inject
	private Logger logger;

	@AroundInvoke
	private Object interceptAroundInvoke(InvocationContext ic) throws Exception {
		logger.info(">>> ");
		try {
			return ic.proceed();
		} catch (PersistenceException e) {
			throw new RepositoryException(e.getMessage(), e); //"Converting to RepositoryException :",

		} finally {
			logger.info("<<< ");

		}
	}

}
