package com.small.services;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

@Stateless
@Named
public class LoggerProcessService {

	@Inject
	private Logger logger;

	public void log(String message) {
		logger.info(message);
	}
}
