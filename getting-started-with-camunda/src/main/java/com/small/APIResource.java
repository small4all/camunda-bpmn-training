package com.small;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
		ProcessInstance process = processEngineService.startProcess(processKey);
		return Response.ok(process.getId()).build();
	}
}
