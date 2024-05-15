package com.app.service.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.RegionDto;
import com.app.model.Region;
import com.app.service.repository.RegionRepository;

@Service
public class RegionService {
	@Autowired
	RegionRepository regionRepository;
	
	public Region findOneByNaziv(String naziv) {
		return regionRepository.findOneByNaziv(naziv).orElse(null);
	}
	
	public List<Region> findAll(){
		return regionRepository.findAll();
	}
	
	public Region findOne(Integer id) {
		return regionRepository.findById(id).get();
	}
	
	public Region create(RegionDto regionDto) {
		Region region = regionDto.toRegion();
		
		region.setNaziv(region.getNaziv());
		region.setNadmorskaVisina(region.getNadmorskaVisina());
		region.setProsecnaGodisnjaTemperatura(region.getProsecnaGodisnjaTemperatura());
		region.setMinTemperatura(region.getMinTemperatura());
		region.setMaxTemperatura(region.getMaxTemperatura());
		region.setKolicinaPadavina(region.getKolicinaPadavina());
		
		return regionRepository.save(region);
	}
	
	public Region updateRegion(RegionDto regionDto, int id) {
		Region region = findOne(id);

		if (region == null) {
			return null;
		}

		region.setId(regionDto.getId());
		region.setNaziv(regionDto.getNaziv());
		region.setNadmorskaVisina(regionDto.getNadmorskaVisina());
		region.setProsecnaGodisnjaTemperatura(regionDto.getProsecnaGodisnjaTemperatura());
		region.setMinTemperatura(regionDto.getMinTemperatura());
		region.setMaxTemperatura(regionDto.getMaxTemperatura());
		region.setKolicinaPadavina(regionDto.getKolicinaPadavina());

		return regionRepository.save(region);
	}
}
