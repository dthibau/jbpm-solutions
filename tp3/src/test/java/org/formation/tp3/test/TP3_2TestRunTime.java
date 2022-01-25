package org.formation.tp3.test;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.io.ResourceType;
import org.kie.api.logger.KieRuntimeLogger;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.manager.RuntimeEnvironment;
import org.kie.api.runtime.manager.RuntimeEnvironmentBuilder;
import org.kie.api.runtime.manager.RuntimeManager;
import org.kie.api.runtime.manager.RuntimeManagerFactory;
import org.kie.internal.io.ResourceFactory;

/**
 * This is a sample file to launch a process.
 */
public class TP3_2TestRunTime {

	@Test
	public void startProcessAndTrace() {
		try {
			// load up the knowledge base
			RuntimeManager manager = _createManager();
			RuntimeEngine engine = manager.getRuntimeEngine(null);
			KieSession ksession = engine.getKieSession();
			KieRuntimeLogger logger = KieServices.Factory.get().getLoggers().newThreadedFileLogger(ksession, "tp3_2", 1000);
			// start a new process instance
			ksession.startProcess("org.formation.tp3.Main");
			logger.close();
			Thread.sleep(10000);
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	private static RuntimeManager _createManager() throws Exception {
		
		RuntimeEnvironment env = RuntimeEnvironmentBuilder.Factory.get()
    			.newEmptyBuilder().addAsset(ResourceFactory.newClassPathResource("org/formation/tp3/Subprocess.bpmn"), ResourceType.BPMN2).
    			addAsset(ResourceFactory.newClassPathResource("org/formation/tp3/Main.bpmn"), ResourceType.BPMN2).get();
		return RuntimeManagerFactory.Factory.get().newSingletonRuntimeManager(env);
		
	}
	
}