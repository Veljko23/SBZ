package com.app.service.controllers;

import java.util.ArrayList;
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
import com.app.model.Region;
import com.app.model.User;
import com.app.service.services.RegionService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "api/regions")
public class RegionController {
	
	@Autowired
	private RegionService regionService;
	
	@GetMapping
	public ResponseEntity<List<Region>> getRegions() {

		List<Region> regions = regionService.findAll();
		
		//List<UserDto> userdtos = new ArrayList<UserDto>();
		
		//for(User user: users) {
			//userdtos.add(new UserDto(user));
		//}

		return new ResponseEntity<>(regions, HttpStatus.OK);
	}
	
	@PostMapping(value = "/createRegion", consumes = "application/json")
    public String createRegion(@RequestBody RegionDto regionDto){
		
       
		Region region = regionService.create(regionDto);
	       String message  = null;
	       if(region == null) {
	           message = "unsuccesfull";
	       }else {
	           message = "succesfull";
	       }
	        return getJsonResponse("message", message);
    }
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<RegionDto> getRegion(@PathVariable Integer id) {
		try {
			Region region = regionService.findOne(id);

			return new ResponseEntity<>(new RegionDto(region), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@PutMapping(value = "/{id}", consumes = "application/json")
	public ResponseEntity<RegionDto> updateRegion(@RequestBody RegionDto regionDto, @PathVariable Integer id) {

		Region updatedRegion = regionService.updateRegion(regionDto, id);

		return new ResponseEntity<>(new RegionDto(updatedRegion), HttpStatus.OK);

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
