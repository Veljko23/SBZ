package com.app.dto;

import com.app.model.Voce;
import com.app.model.Zemljiste.calcCategory;
import com.app.model.Zemljiste.phCategory;

public class VoceDto {
	
	private int id;
	private String name;
	private String opis;
	private double nadmorskaVisina;
	private double minTemperatura;
	private double maxTemperatura;
	private double kolicinaPadavina;
	private double prosecnaGodisnjaTemperatura;
	private phCategory phCategory;
	private calcCategory calcCategory;

	public VoceDto() {}

	public VoceDto(int id, String name, String opis, double nadmorskaVisina, double minTemperatura,
			double maxTemperatura, double kolicinaPadavina, double prosecnaGodisnjaTemperatura) {
		super();
		this.id = id;
		this.name = name;
		this.opis = opis;
		this.nadmorskaVisina = nadmorskaVisina;
		this.minTemperatura = minTemperatura;
		this.maxTemperatura = maxTemperatura;
		this.kolicinaPadavina = kolicinaPadavina;
		this.prosecnaGodisnjaTemperatura = prosecnaGodisnjaTemperatura;
		
	}
	
	public VoceDto(Voce voce) {
		id = voce.getId();
		name = voce.getName();
		opis = voce.getOpis();
		nadmorskaVisina = voce.getNadmorskaVisina();
		minTemperatura = voce.getMinTemperatura();
		maxTemperatura = voce.getMaxTemperatura();
		kolicinaPadavina = voce.getKolicinaPadavina();
		prosecnaGodisnjaTemperatura = voce.getProsecnaGodisnjaTemperatura();
		phCategory = voce.getPhCategory();
		calcCategory = voce.getCalcCategory();
	}
	
	public Voce toVoce() {
		Voce voce = new Voce();
		voce.setId(id);
		voce.setName(name);
		voce.setOpis(opis);
		voce.setNadmorskaVisina(nadmorskaVisina);
		voce.setMinTemperatura(minTemperatura);
		voce.setMaxTemperatura(maxTemperatura);
		voce.setKolicinaPadavina(kolicinaPadavina);
		voce.setProsecnaGodisnjaTemperatura(prosecnaGodisnjaTemperatura);
		voce.setPhCategory(phCategory);
		voce.setCalcCategory(calcCategory);
		
		return voce;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public double getNadmorskaVisina() {
		return nadmorskaVisina;
	}

	public void setNadmorskaVisina(double nadmorskaVisina) {
		this.nadmorskaVisina = nadmorskaVisina;
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

	public double getProsecnaGodisnjaTemperatura() {
		return prosecnaGodisnjaTemperatura;
	}

	public void setProsecnaGodisnjaTemperatura(double prosecnaGodisnjaTemperatura) {
		this.prosecnaGodisnjaTemperatura = prosecnaGodisnjaTemperatura;
	}

	public phCategory getPhCategory() {
		return phCategory;
	}

	public void setPhCategory(phCategory phCategory) {
		this.phCategory = phCategory;
	}

	public calcCategory getCalcCategory() {
		return calcCategory;
	}

	public void setCalcCategory(calcCategory calcCategory) {
		this.calcCategory = calcCategory;
	}
	

}
