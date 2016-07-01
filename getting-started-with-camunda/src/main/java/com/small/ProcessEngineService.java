package com.small;

import javax.inject.Inject;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.runtime.ProcessInstance;

public class ProcessEngineService {
	
	@Inject
	private ProcessEngine processEngine;
	
	public ProcessInstance startProcess(String processKey) {
		ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey(processKey);
		return processInstance;
	}
}
