package org.formation.tp4.test;

import java.util.HashMap;
import java.util.Map;

import org.formation.model.Demande;
import org.jbpm.test.JbpmJUnitBaseTestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.logger.KieRuntimeLogger;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.manager.RuntimeManager;
import org.kie.api.runtime.manager.audit.AuditService;
import org.kie.api.runtime.process.ProcessInstance;

public class TP4_jbpmTest extends JbpmJUnitBaseTestCase {

	RuntimeManager manager;
	RuntimeEngine engine;
	KieSession ksession;
	KieRuntimeLogger logger;
	
	@Before
	public void setup() {
		manager = createRuntimeManager("org/formation/Subprocess.bpmn","org/formation/Main.bpmn");
		engine = getRuntimeEngine(null);
		ksession = engine.getKieSession();

		logger  = KieServices.Factory.get().getLoggers().newThreadedFileLogger(ksession, "tp4", 1000);
	}
	@After
	public void tearDown() {
		logger.close();
		manager.close();
	}
	@Test
	public void testGrandeDemande() {
		try {
			
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("demande", new Demande(2000));
			// start a new process instance
			ProcessInstance processInstance = ksession.startProcess("org.formation.Main",map);		
			Thread.sleep(5000);
			Thread.sleep(5000);
			// Check last subprocess node
			assertNodeTriggered(processInstance.getId(), "Sub-Process","EvaluateResult");
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
	
	@Test
	public void testPetiteDemande() throws InterruptedException {
			// load up the knowledge base
		
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("demande", new Demande(500));
			// start a new process instance
			ProcessInstance processInstance = ksession.startProcess("org.formation.Main",map);		
			Thread.sleep(10000);
			// Check last subprocess node
			assertNodeTriggered(processInstance.getId(), "PetitCredit");
			
		
	}
}
