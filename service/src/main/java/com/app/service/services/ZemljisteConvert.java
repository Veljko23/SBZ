package com.app.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.dto.ZemljisteDto;
import com.app.model.Region;
import com.app.model.Zemljiste;
import com.app.model.Zemljiste.calcCategory;
import com.app.model.Zemljiste.phCategory;

@Component
public class ZemljisteConvert {
	
	@Autowired
	RegionService regionService;
	
	public Zemljiste toDTO(ZemljisteDto dto) {
		Zemljiste z = new Zemljiste();
		z.setKalcijumKarbonat(dto.getKalcijumKarbonat());
		z.setPhVrednost(dto.getPhVrednost());
		z.setAzot(dto.getAzot());
		z.setFosfor(dto.getFosfor());
		z.setHumus(dto.getHumus());
		z.setKalijum(dto.getKalijum());
		
		z.setCalcCategory(calcCategory.NA);
		z.setPhCategory(phCategory.NA);
		
		Region r = regionService.findOneByNaziv(dto.getRegion());
		z.setRegion(r);
		return z;
	}
}
