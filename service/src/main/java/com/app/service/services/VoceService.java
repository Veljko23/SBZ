package com.app.service.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.RegionDto;
import com.app.dto.VoceDto;
import com.app.model.Region;
import com.app.model.Voce;
import com.app.service.repository.VoceRepository;

@Service
public class VoceService {
	
	@Autowired
	private VoceRepository voceRepository;
	
	public Voce findOneByName(String name) {
		return voceRepository.findOneByName(name).orElse(null);
	}
	
	public Voce findOne(Integer id) {
		return voceRepository.findById(id).get();
	}
	
	public List<Voce> findAll(){
		return voceRepository.findAll();
	}
	
	public List<Voce> findAllVoce(){
		return voceRepository.findAllVoce();
	}
	
	public Voce create(VoceDto voceDto) {
		Voce voce = voceDto.toVoce();
		
		voce.setName(voce.getName());
		voce.setOpis(voce.getOpis());
		voce.setNadmorskaVisina(voce.getNadmorskaVisina());
		voce.setMinTemperatura(voce.getMinTemperatura());
		voce.setMaxTemperatura(voce.getMaxTemperatura());
		voce.setKolicinaPadavina(voce.getKolicinaPadavina());
		voce.setProsecnaGodisnjaTemperatura(voce.getProsecnaGodisnjaTemperatura());
		
		voce.setPhCategory(voce.getPhCategory());
		voce.setCalcCategory(voce.getCalcCategory());

		return voceRepository.save(voce);
	}
	
	public Voce updateVoce(VoceDto voceDto, int id) {
		Voce voce = findOne(id);

		if (voce == null) {
			return null;
		}

		voce.setId(voceDto.getId());
		voce.setName(voceDto.getName());
		voce.setOpis(voceDto.getOpis());
		voce.setNadmorskaVisina(voceDto.getNadmorskaVisina());
		voce.setMinTemperatura(voceDto.getMinTemperatura());
		voce.setMaxTemperatura(voceDto.getMaxTemperatura());
		voce.setKolicinaPadavina(voceDto.getKolicinaPadavina());
		voce.setProsecnaGodisnjaTemperatura(voceDto.getProsecnaGodisnjaTemperatura());
		voce.setPhCategory(voceDto.getPhCategory());
		voce.setCalcCategory(voceDto.getCalcCategory());
		return voceRepository.save(voce);
	}

	public List<Voce> search(String name){
		return voceRepository.findByName(name);
	}
}
