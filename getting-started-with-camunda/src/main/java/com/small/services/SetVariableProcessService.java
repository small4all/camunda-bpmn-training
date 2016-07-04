package com.small.services;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.slf4j.Logger;

@Stateless
@Named
public class SetVariableProcessService {

	@Inject
	private Logger logger;

	public void setVariable(DelegateExecution delegateExecution, String name, String value) {
		logger.info("Set variable {} to {}", name, value);
		delegateExecution.setVariable(name, value);
	}
}
