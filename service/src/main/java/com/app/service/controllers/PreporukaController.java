package com.app.service.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ZemljisteDto;
import com.app.model.Zemljiste;
import com.app.service.SampleAppController;
import com.app.service.services.PreporukaService;
import com.app.service.services.ZemljisteConvert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
@RestController
@RequestMapping(value = "/suggest")
public class PreporukaController {

private static Logger log = LoggerFactory.getLogger(SampleAppController.class);
	
	@Autowired
	public ZemljisteConvert zemljisteConvert;
	
	private final PreporukaService preporukaService;

	@Autowired
	public PreporukaController(PreporukaService preporukaService) {
		this.preporukaService = preporukaService;
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getQuestionsZemljiste(@RequestBody ZemljisteDto dto) {

		Zemljiste newZemljiste = zemljisteConvert.toDTO(dto);

		System.out.println("Zemljiste preporukaController request received for: " + newZemljiste);

		Zemljiste z2 = preporukaService.getClassifiedZemljiste(newZemljiste, dto.getPoklapanja());

		return new ResponseEntity<>(newZemljiste.getListaVoca(), HttpStatus.OK);
	}
}
