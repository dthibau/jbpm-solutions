package org.formation.tp2.test;

import org.jbpm.test.JbpmJUnitBaseTestCase;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.logger.KieRuntimeLogger;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.manager.RuntimeManager;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.internal.event.KnowledgeRuntimeEventManager;
import org.kie.internal.logger.KnowledgeRuntimeLogger;
import org.kie.internal.logger.KnowledgeRuntimeLoggerFactory;

public class ProcessTest extends JbpmJUnitBaseTestCase {

	public ProcessTest() {
		super(false,false);
	}
		@Test
	   public void testClasspathProcess() {

	       // Créer la session et charger le processus TP2.bpmn à partir du classpath
	       
			
		// Associer un logger à la session
			
	       _doTests(ksession);
	       
	       // Fermer le logger
	   }
	   
		private void _doTests(KieSession ksession) {
	       // Démarrer le processus 

	    
	       // Tester le noeud actif

	       
	       // Tester si le noeud Hello a été déclenché
	       
	       // Faire progresser le processus
	       
	       // Tester la fin du processus
	    
	       // Tester si tous les noeuds ont été déclenchés   
		   
	   }
}
