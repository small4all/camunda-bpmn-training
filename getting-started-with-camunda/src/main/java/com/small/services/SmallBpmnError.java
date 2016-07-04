package com.small.services;

import javax.ejb.ApplicationException;

import org.camunda.bpm.engine.delegate.BpmnError;

@ApplicationException(rollback = false)
public class SmallBpmnError extends BpmnError {

	private static final long serialVersionUID = -6718405184596227230L;

	public SmallBpmnError(String errorCode) {
		super(errorCode);
	}
}
