package de.thb.fbi.tech.slf4j.cdi;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerProducer {
	
	@Produces
	public Logger loggerProduces(InjectionPoint ip){
		return LoggerFactory.getLogger(ip.getMember().getDeclaringClass().getName());
	}
	
}
