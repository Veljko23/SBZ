package com.app.dto;

import com.app.model.Region;

public class RegionDto {
	private int id;
	private String naziv;
	private double nadmorskaVisina;
	private double prosecnaGodisnjaTemperatura;
	private double minTemperatura;
	private double maxTemperatura;
	private double kolicinaPadavina;

	
	public RegionDto() {}


	public RegionDto(int id, String naziv, double nadmorskaVisina, double prosecnaGodisnjaTemperatura,
			double minTemperatura, double maxTemperatura, double kolicinaPadavina) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.nadmorskaVisina = nadmorskaVisina;
		this.prosecnaGodisnjaTemperatura = prosecnaGodisnjaTemperatura;
		this.minTemperatura = minTemperatura;
		this.maxTemperatura = maxTemperatura;
		this.kolicinaPadavina = kolicinaPadavina;
	}
	
	public RegionDto(Region region) {
		id = region.getId();
		naziv = region.getNaziv();
		nadmorskaVisina = region.getNadmorskaVisina();
		prosecnaGodisnjaTemperatura = region.getProsecnaGodisnjaTemperatura();
		minTemperatura = region.getMinTemperatura();
		maxTemperatura = region.getMaxTemperatura();
		kolicinaPadavina = region.getKolicinaPadavina();
	}

	public Region toRegion() {
		Region region = new Region();
		region.setId(id);
		region.setNaziv(naziv);
		region.setNadmorskaVisina(nadmorskaVisina);
		region.setProsecnaGodisnjaTemperatura(prosecnaGodisnjaTemperatura);
		region.setMinTemperatura(minTemperatura);
		region.setMaxTemperatura(maxTemperatura);
		region.setKolicinaPadavina(kolicinaPadavina);
		
		return region;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNaziv() {
		return naziv;
	}


	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}


	public double getNadmorskaVisina() {
		return nadmorskaVisina;
	}


	public void setNadmorskaVisina(double nadmorskaVisina) {
		this.nadmorskaVisina = nadmorskaVisina;
	}


	public double getProsecnaGodisnjaTemperatura() {
		return prosecnaGodisnjaTemperatura;
	}


	public void setProsecnaGodisnjaTemperatura(double prosecnaGodisnjaTemperatura) {
		this.prosecnaGodisnjaTemperatura = prosecnaGodisnjaTemperatura;
	}


	public double getMinTemperatura() {
		return minTemperatura;
	}


	public void setMinTemperatura(double minTemperatura) {
		this.minTemperatura = minTemperatura;
	}


	public double getMaxTemperatura() {
		return maxTemperatura;
	}


	public void setMaxTemperatura(double maxTemperatura) {
		this.maxTemperatura = maxTemperatura;
	}


	public double getKolicinaPadavina() {
		return kolicinaPadavina;
	}


	public void setKolicinaPadavina(double kolicinaPadavina) {
		this.kolicinaPadavina = kolicinaPadavina;
	}
	
	
}
