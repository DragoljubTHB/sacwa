package de.thb.fbi.project.generic.interceptors;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.interceptor.InterceptorBinding;

@Inherited
@Retention(RUNTIME)
@Target({ TYPE, METHOD })
@InterceptorBinding
public @interface ConvertJPAExceptions {

	// TODO: std fields -> message etc
	public String message() default "{de.thb.fbi.project.generic.repository.JPARepository.create.ConvertJPAException}"; // JPA Repository ConvertJPAExceptions
	//de.thb.fbi.project.generic.repository.JPARepository.create.ConvertJPAExceptions

	// public Class<?>[] groups default [];

	// public Class<? extends Payload>[] payload[] default [];

}
