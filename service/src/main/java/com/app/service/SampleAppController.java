package com.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Item;
import com.app.model.Zemljiste;



@RestController
public class SampleAppController {
	private static Logger log = LoggerFactory.getLogger(SampleAppController.class);

	private final SampleAppService sampleService;


	public SampleAppController(SampleAppService sampleService) {
		this.sampleService = sampleService;
	}

	@RequestMapping(value = "/item", method = RequestMethod.GET, produces = "application/json")
	public Item getQuestions(@RequestParam(required = true) String id, @RequestParam(required = true) String name,
			@RequestParam(required = true) double cost, @RequestParam(required = true) double salePrice) {

		Item newItem = new Item(Long.parseLong(id), name, cost, salePrice);

		log.debug("Item request received for: " + newItem);

		Item i2 = sampleService.getClassifiedItem(newItem);

		return i2;
	}
	
	@RequestMapping(value = "/zemljiste", method = RequestMethod.GET, produces = "application/json")
	public Zemljiste getQuestionsZemljiste(@RequestParam(required = true) double phVrednost, @RequestParam(required = true) double kalcijumKarbonat) {

		Zemljiste newZemljiste = new Zemljiste(phVrednost, kalcijumKarbonat);

		log.debug("Zemljiste request received for: " + newZemljiste);

		Zemljiste z2 = sampleService.getClassifiedZemljiste(newZemljiste);

		return z2;
	}
	
}
