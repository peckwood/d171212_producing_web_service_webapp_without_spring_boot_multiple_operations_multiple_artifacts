package com.raidencentral.app.ws;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.raidencentral.app.ws.artifact.contract_status.ContractStatusUpdateRequest;
import com.raidencentral.app.ws.artifact.response.Response;
import com.raidencentral.app.ws.artifact.response.StatusType;

@Endpoint
public class ContractStatusUpdateEndpoint {
	private static final String NAMESPACE_URI = "http://www.ouhaogroup.com/contract-status";
	
	//localPart matches the input parameter of the method
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "contractStatusUpdateRequest")
	@ResponsePayload
	public Response updateSiteAndContractToConstructionComplete(@RequestPayload ContractStatusUpdateRequest request) {
		System.out.println("request.getContractNumber(): {}" + request.getContractNumber());
		Response response = new Response();
		response.setStatus(StatusType.SUCCESS);
		response.setMsg(request.getContractNumber());
		return response;
	}
}
