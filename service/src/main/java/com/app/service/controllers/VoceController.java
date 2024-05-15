package com.app.service.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.RegionDto;
import com.app.dto.UserDto;
import com.app.dto.VoceDto;
import com.app.model.Region;
import com.app.model.User;
import com.app.model.Voce;
import com.app.model.Zemljiste.calcCategory;
import com.app.model.Zemljiste.phCategory;
import com.app.service.services.VoceService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "api/fruits")

public class VoceController {
	
	@Autowired
	private VoceService voceService;

	@GetMapping
	public ResponseEntity<List<VoceDto>> getVoce() {
	    List<Voce> voce = voceService.findAllVoce();
	    
	    List<VoceDto> voceDtos = new ArrayList<VoceDto>();
	    
	    for(Voce v: voce) {
	    	System.out.println(v.getPhCategory());
	        voceDtos.add(new VoceDto(v));
	    }

	    return new ResponseEntity<>(voceDtos, HttpStatus.OK);
	}
	
	@GetMapping(value = "/phValues", produces = "application/json")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<?> getPhValues() {
	    return ResponseEntity.ok(phCategory.values());
	}
	
	@GetMapping(value = "/calcValues", produces = "application/json")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<?> getCalcValues() {
	    return ResponseEntity.ok(calcCategory.values());
	}
	
	@PostMapping(value = "/createFruit", consumes = "application/json")
    public String createFruit(@RequestBody VoceDto voceDto){
		
		System.out.println(phCategory.values());

		Voce voce = voceService.create(voceDto);
	       String message  = null;
	       if(voce == null) {
	           message = "unsuccesfull";
	       }else {
	           message = "succesfull";
	       }
	        return getJsonResponse("message", message);
    }
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<VoceDto> getVoce(@PathVariable Integer id) {
		try {
			Voce voce = voceService.findOne(id);

			return new ResponseEntity<>(new VoceDto(voce), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@PutMapping(value = "/{id}", consumes = "application/json")
	public ResponseEntity<VoceDto> updateVoce(@RequestBody VoceDto voceDto, @PathVariable Integer id) {

		Voce updatedVoce = voceService.updateVoce(voceDto, id);

		return new ResponseEntity<>(new VoceDto(updatedVoce), HttpStatus.OK);

	}
	
	@GetMapping(value = "/search/{name}")
    public ResponseEntity<List<VoceDto>> searchVoce(@PathVariable String name) {
        List<Voce> voceList = voceService.search(name);
        List<VoceDto> voceDtos = new ArrayList<>();
        for (Voce voce : voceList) {
            voceDtos.add(new VoceDto(voce));
        }
        return new ResponseEntity<>(voceDtos, HttpStatus.OK);
    }
	
	private String getJsonResponse(String key, String value) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(Map.of(key, value));
        } catch (Exception e) {
            e.printStackTrace();
            return "{}";
        }
    }
}
