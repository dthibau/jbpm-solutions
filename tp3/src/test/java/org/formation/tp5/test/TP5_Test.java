package org.formation.tp5.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.formation.model.Demande;
import org.jbpm.test.JbpmJUnitBaseTestCase;
import org.junit.Test;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.manager.audit.AuditService;
import org.kie.api.runtime.manager.audit.ProcessInstanceLog;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.internal.runtime.manager.context.EmptyContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is a sample file to test a process.
 */
public class TP5_Test extends JbpmJUnitBaseTestCase {
	
	AuditService auditService;
	private static final Logger logger = LoggerFactory.getLogger(TP5_Test.class);

	public TP5_Test() {
		super(true,true);
	}
	
	@Test
	public void testProcess() {
			
		
		manager = createRuntimeManager("org/formation/Subprocess.bpmn","org/formation/Main.bpmn");

		RuntimeEngine engine = getRuntimeEngine();
		auditService = getLogService();
		KieSession ksession = engine.getKieSession();

		// Starting the process with big amount
		Map<String, Object> hshVariables = new HashMap<String, Object>();
		hshVariables.put("demande", new Demande(2000));
		ProcessInstance procInst = ksession.startProcess("org.formation.Main",
				hshVariables);

		_printProcesses(ksession);
		// Fermeture session + logger
//		manager.disposeRuntimeEngine(engine);
		
		manager.close();
		manager = null;
		manager = createRuntimeManager("org/formation/Subprocess.bpmn","org/formation/Main.bpmn");
		
		
		// Rechargement de la session avec un nouvel environnement
		engine = getRuntimeEngine();
		auditService = getLogService();
		ksession = engine.getKieSession();

		_printProcesses(ksession);

		// Poursuivre la demande de Credit via le signal approprié
		ksession.signalEvent("DonneAvis", 5);

		_printProcesses(ksession);
		
		assertProcessInstanceCompleted(procInst.getId());

		// Fermeture session + logger
		manager.disposeRuntimeEngine(engine);
		
		
		
		// Rechargement de la session avec un nouvel environnement
		engine = manager.getRuntimeEngine(EmptyContext.get());
		ksession = engine.getKieSession();

		_printProcesses(ksession);
		// Fermeture session + logger
		manager.disposeRuntimeEngine(engine);
	}

	private void _printProcesses(KieSession ksession) {
		logger.info("Print process from database ");
		for (ProcessInstanceLog p : auditService.findProcessInstances()) {
			List<String> nodes = getActiveNodesInProcessInstance(ksession, p.getProcessInstanceId());

			for ( String node : nodes) {
				 logger.info("Process instance avec l'id " + 
							p.getProcessInstanceId() + " est dans le noeud " + node);
			}
		}
	}


}
