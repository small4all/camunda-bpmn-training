package com.small;

import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.cdi.BusinessProcessEvent;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.slf4j.Logger;

@Stateless
public class ProcessEngineService {

	@Inject
	private ProcessEngine proocessEngine;
	
	@Inject
	private RuntimeService runtimeService;

	@Inject
	private TaskService taskService;

	@Inject
	private FormService formService;

	@Inject
	private Logger logger;

	public void getName() {
		logger.info(proocessEngine.getName());
	}
	
	public ProcessInstance startProcess(String processKey) {
		logger.info("Start process {}", processKey);

		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processKey);
		return processInstance;
	}

	public void printTasks(String processInstanceId) {
		List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstanceId).list();

		for (Task task : tasks) {
			logger.info("Task {}, name: {}, description: {}", task.getId(), task.getName(), task.getDescription());
		}
	}

	public void completeTask(String id) {
		Task task = taskService.createTaskQuery().taskId(id).singleResult();
		taskService.complete(task.getId());

		logger.info("Completed task {}, name: {}, description: {}", task.getId(), task.getName(), task.getDescription());
	}

	public void completeCascading(String processInstanceId) {
		List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstanceId).list();
		for (Task task : tasks) {
			completeTask(task.getId());
		}

		if (tasks.size() > 0) {
			completeCascading(processInstanceId);
		}
	}

	public void getTaskForm(String id) {
		Object renderedTaskForm = formService.getRenderedTaskForm(id);
		logger.info("my task form {}", renderedTaskForm);

		TaskFormData taskFormData = formService.getTaskFormData(id);
		logger.info("as object {}", taskFormData);
	}

	/**
	 * https://github.com/camunda/camunda-docs-manual/blob/master/content/user-guide/cdi-java-ee-integration/the-cdi-event-bridge.md
	 */
	public void onBusinessProcessEvent(@Observes BusinessProcessEvent businessProcessEvent) {
		logger.info("receive BusinessProcessEvent: {}", businessProcessEvent);
	}
}
