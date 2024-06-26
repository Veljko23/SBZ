package com.app.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.Item;
import com.app.model.Zemljiste;



@Service
public class SampleAppService {

	private static Logger log = LoggerFactory.getLogger(SampleAppService.class);

	private final KieContainer kieContainer;

	@Autowired
	public SampleAppService(KieContainer kieContainer) {
		log.info("Initialising a new example session.");
		this.kieContainer = kieContainer;
	}

	public Item getClassifiedItem(Item i) {
		KieSession kieSession = kieContainer.newKieSession();
		kieSession.insert(i);
		kieSession.fireAllRules();
		kieSession.dispose();
		return i;
	}
	
	public Zemljiste getClassifiedZemljiste(Zemljiste z) {
		KieSession kieSession = kieContainer.newKieSession();
		kieSession.insert(z);
		kieSession.getAgenda().getAgendaGroup("classify").setFocus();
		kieSession.fireAllRules();
		kieSession.dispose();
		return z;
	}
	
	public String getVoce(Zemljiste z) {
		KieSession kieSession = kieContainer.newKieSession();
		kieSession.insert(z);
		kieSession.fireAllRules();
		kieSession.dispose();
		return "";
	}
}
