package com.small.services;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

@Stateless
@Named
public class ErrorProcessService {

	@Inject
	private Logger logger;

	public void throwError(boolean really) {
		logger.error("throwError");

		if (really) {
			throw new SmallBpmnError("some error code");
		}
	}
}
