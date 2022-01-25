package org.formation.tp3.test;

import org.junit.Test;
import org.kie.api.KieBase;
import org.kie.api.io.ResourceType;
import org.kie.api.logger.KieRuntimeLogger;
import org.kie.api.runtime.KieSession;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.logger.KnowledgeRuntimeLoggerFactory;

/**
 * This is a sample file to launch a process.
 */
public class TP3_2test {

	@Test
	public void startProcessAndTrace() {
		try {
			// load up the knowledge base
			KieBase kbase = readKnowledgeBase();
			KieSession ksession = kbase.newKieSession();
			KieRuntimeLogger logger = KnowledgeRuntimeLoggerFactory.newFileLogger(ksession, "tp3_2");
			// start a new process instance
			ksession.startProcess("org.formation.tp3.Main");
			logger.close();
			Thread.sleep(10000);
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	private static KieBase readKnowledgeBase() throws Exception {
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		kbuilder.add(ResourceFactory.newClassPathResource("org/formation/tp3/Subprocess.bpmn"), ResourceType.BPMN2);
		kbuilder.add(ResourceFactory.newClassPathResource("org/formation/tp3/Main.bpmn"), ResourceType.BPMN2);
		return kbuilder.newKieBase();
	}
	
}