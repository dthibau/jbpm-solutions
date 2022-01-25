package org.formation.tp2.test;

import org.jbpm.test.JbpmJUnitBaseTestCase;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.logger.KieRuntimeLogger;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.manager.RuntimeManager;
import org.kie.api.runtime.manager.audit.AuditService;
import org.kie.api.runtime.process.ProcessInstance;

public class ProcessTest extends JbpmJUnitBaseTestCase {

	public ProcessTest() {
		super(false, false);
	}

	@Test
	public void testClasspathProcess() {

		// Créer la session et charger le processus TP2.bpmn à partir du
		// classpath

		RuntimeManager manager = createRuntimeManager("TP2.bpmn");
		RuntimeEngine engine = getRuntimeEngine(null);
		AuditService auditService = engine.getAuditService();
		KieSession ksession = engine.getKieSession();

		// Associer un logger à la session
		// KnowledgeRuntimeLogger logger =
		// KnowledgeRuntimeLoggerFactory.newFileLogger(
		// (KnowledgeRuntimeEventManager)ksession, "tp2" );
		KieRuntimeLogger logger = KieServices.Factory.get().getLoggers().newThreadedFileLogger(ksession, "tp2", 1000);

		_doTests(ksession);
		logger.close();
		manager.close();
	}

	@Test
	public void testGuvnorProcess() throws Exception {

		// Créer la session et charger le processus TP2.bpmn à partir du
		// repository Guvnor
		// createRuntimeManager()
		// Associer un logger à la session

		// _doTests(ksession);
	}

	private void _doTests(KieSession ksession) {
		// Démarrer le processus

		ProcessInstance processInstance = ksession.startProcess("org.formation.tp2");

		// Tester le noeud actif

		assertNodeActive(processInstance.getId(), ksession, "Signal");

		// Tester si le noeud Hello a été déclenché
		assertNodeTriggered(processInstance.getId(), "Hello");

		// Faire progresser le processus
		ksession.signalEvent("Continue", "None", processInstance.getId());

		// Tester la fin du processus
//		assertProcessInstanceCompleted(processInstance.getId());
		assertProcessInstanceCompleted(processInstance.getId(), ksession);


		// Tester si tous les noeuds ont été déclenchés

		assertNodeTriggered(processInstance.getId(), "StartProcess", "Hello", "Signal", "EndProcess");

	}
}
