package org.formation.service;

import java.util.HashMap;
import java.util.Map;

import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.api.runtime.process.WorkItemManager;



public class ComputeIncidentHandler implements WorkItemHandler {

	
	/* Synchronous call
	 * @see org.kie.api.runtime.process.WorkItemHandler#executeWorkItem(org.kie.api.runtime.process.WorkItem, org.kie.api.runtime.process.WorkItemManager)
	 */
	public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {
		String idClient = (String) workItem.getParameter("idClient");

		System.out.println("INCIDENT CHECK FOR client "+idClient);
		Map<String,Object> results = new HashMap<String,Object>();
		results.put("computeIncidents",new Integer(2));

		
		manager.completeWorkItem(workItem.getId(), results);
		
	}

	public void abortWorkItem(WorkItem workItem, WorkItemManager manager) {
		// TODO Auto-generated method stub
		
	}

	

}
