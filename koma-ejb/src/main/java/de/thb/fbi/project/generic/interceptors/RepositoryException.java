package de.thb.fbi.project.generic.interceptors;
//TODO: package de.thb.fbi.project.koma.repository.api

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.inject.Inject;

import org.slf4j.Logger;

public class RepositoryException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String causeStackPrint;

	@Inject
	Logger logger;

	public RepositoryException(String message, Throwable cause) {
		super(message);
		saveStackDiscardCause(cause);

	}

	public RepositoryException(String message) {
		super(message);
	}

	private void saveStackDiscardCause(Throwable cause) {
		final StringWriter internalStack = new StringWriter();
		final PrintWriter stack = new PrintWriter(internalStack);
		if (cause != null) {
			cause.printStackTrace(stack);
			causeStackPrint = internalStack.toString();

		}
	}

	@Override
	public void printStackTrace(PrintWriter s) {
		if (s != null) {
			s.println("Saved caused by: ");
			s.print(causeStackPrint);
			
			logger.info("\n ");

			logger.info("----------------causeStackPrint {}", causeStackPrint);
			logger.info("\n ");
		}
	}

}
