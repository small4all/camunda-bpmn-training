package com.small;

import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.cdi.BusinessProcessEvent;
import org.camunda.bpm.engine.cdi.annotation.event.CompleteTask;
import org.camunda.bpm.engine.cdi.annotation.event.CreateTask;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.slf4j.Logger;

@Stateless
public class ProcessEngineService {

	@Inject
	private ProcessEngine processEngine;

	@Inject
	private Logger logger;

	public ProcessInstance startProcess(String processKey) {
		logger.info("Start process {}", processKey);

		ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey(processKey);
		return processInstance;
	}

	public void printTasks(String processInstanceId) {
		List<Task> tasks = processEngine.getTaskService().createTaskQuery().processInstanceId(processInstanceId).list();

		for (Task task : tasks) {
			logger.info("Task {}, name: {}, description: {}", task.getId(), task.getName(), task.getDescription());
		}
	}

	public void completeTask(String id) {
		Task task = processEngine.getTaskService().createTaskQuery().taskId(id).singleResult();
		processEngine.getTaskService().complete(task.getId());

		logger.info("Completed task {}, name: {}, description: {}", task.getId(), task.getName(), task.getDescription());
	}

	public void completeCascading(String processInstanceId) {
		List<Task> tasks = processEngine.getTaskService().createTaskQuery().processInstanceId(processInstanceId).list();
		for (Task task : tasks) {
			completeTask(task.getId());
		}

		if (tasks.size() > 0) {
			completeCascading(processInstanceId);
		}
	}

	public void getTaskForm(String id) {
		Object renderedTaskForm = processEngine.getFormService().getRenderedTaskForm(id);
		logger.info("my task form {}", renderedTaskForm);

		TaskFormData taskFormData = processEngine.getFormService().getTaskFormData(id);
		logger.info("as object {}", taskFormData);
	}

	/**
	 * https://github.com/camunda/camunda-docs-manual/blob/master/content/user-guide/cdi-java-ee-integration/the-cdi-event-bridge.md
	 */
	public void onAllBusinessProcessEvent(@Observes BusinessProcessEvent businessProcessEvent) {
		logger.info("receive BusinessProcessEvent: {}", businessProcessEvent);
	}

	public void onCreateTaskEvent(@Observes @CreateTask("approveRegistration") BusinessProcessEvent businessProcessEvent) {
		logger.info("task {} created", businessProcessEvent.getTask().getName());
	}

	public void onCompleteaskEvent(@Observes @CompleteTask("approveRegistration") BusinessProcessEvent businessProcessEvent) {
		logger.info("task {} completed", businessProcessEvent.getTask().getName());
	}
}
