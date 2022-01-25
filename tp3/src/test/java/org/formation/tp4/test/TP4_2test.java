package org.formation.tp4.test;

import java.util.HashMap;
import java.util.Map;

import org.formation.model.Demande;
import org.junit.Test;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.logger.KieRuntimeLogger;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.utils.KieHelper;

/**
 * This is a sample file to launch a process.
 */
public class TP4_2test {

	@Test
	public void testGrandeDemande() {
		try {
			// load up the knowledge base
			KieBase kbase = readKnowledgeBase();
			KieSession ksession = kbase.newKieSession();
			KieRuntimeLogger logger = KieServices.Factory.get().getLoggers().newThreadedFileLogger(ksession, "tp4", 1000);
			
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("demande", new Demande(2000));
			// start a new process instance
			ksession.startProcess("org.formation.Main",map);		
			Thread.sleep(5000);
			Thread.sleep(5000);
			logger.close();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
	
	@Test
	public void testPetiteDemande() {
		try {
			// load up the knowledge base
			KieBase kbase = readKnowledgeBase();
			KieSession ksession = kbase.newKieSession();
			KieRuntimeLogger logger = KieServices.Factory.get().getLoggers().newThreadedFileLogger(ksession, "tp4", 1000);
			
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("demande", new Demande(500));
			// start a new process instance
			ksession.startProcess("org.formation.Main",map);		
			Thread.sleep(10000);

			logger.close();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	private static KieBase readKnowledgeBase() throws Exception {
		KieHelper kieHelper = new KieHelper();
		  KieBase kieBase = kieHelper
				  .addResource(ResourceFactory.newClassPathResource("org/formation/Subprocess.bpmn")).
				  addResource(ResourceFactory.newClassPathResource("org/formation/Main.bpmn")).build();
		  return kieBase;
	}
	
}