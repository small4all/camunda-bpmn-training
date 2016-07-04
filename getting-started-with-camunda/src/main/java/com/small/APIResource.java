package com.small;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.camunda.bpm.engine.runtime.ProcessInstance;

@Path("api")
@Produces({ MediaType.APPLICATION_JSON })
public class APIResource {

	@Inject
	private ProcessEngineService processEngineService;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getIt() {
		return "Api resource is ready";
	}

	@POST
	@Path("process/{processKey}")
	public Response postStartProcess(@PathParam("processKey") String processKey) {
		ProcessInstance processInstance = processEngineService.startProcess(processKey);
		return Response.ok(processInstance.getId()).build();
	}

	@GET
	@Path("process/instance/{processInstanceId}/printTasks")
	public Response getPrintTasks(@PathParam("processInstanceId") String processInstanceId) {
		processEngineService.printTasks(processInstanceId);
		return Response.noContent().build();
	}

	@POST
	@Path("process/tasks/{id}/complete")
	public Response postCompleteTask(@PathParam("id") String id) {
		processEngineService.completeTask(id);
		return Response.status(Status.CREATED).build();
	}
	
	@POST
	@Path("process/instance/{processInstanceId}/completeCascading")
	public Response postCompleteCascading(@PathParam("processInstanceId") String processInstanceId) {
		processEngineService.completeCascading(processInstanceId);
		return Response.status(Status.CREATED).build();
	}
}
