package com.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.app.model.Zemljiste.calcCategory;
import com.app.model.Zemljiste.phCategory;

@Entity
@Table
public class Voce {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "opis")
	private String opis;
	
	
	@Column(name = "nadmorska_visina")
	private double nadmorskaVisina;
	
	@Column(name = "min_temperatura")
	private double minTemperatura;
	
	@Column(name = "max_temperatura")
	private double maxTemperatura;
	
	@Column(name = "kolicina_padavina")
	private double kolicinaPadavina;
	
	@Column(name = "prosecna_godisnja_temperatura") 
	private double prosecnaGodisnjaTemperatura;
	
	@Column(name = "ph_category") 
	@Enumerated(EnumType.STRING)
	private phCategory phCategory;
	@Column(name = "calc_category")
	@Enumerated(EnumType.STRING)
    private calcCategory calcCategory;
	
	public Voce() {}

	public Voce(int id, String name, String opis, double nadmorskaVisina, double minTemperatura, double maxTemperatura,
			double kolicinaPadavina, double prosecnaGodisnjaTemperatura) {
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
	
	@Override
	public String toString() {
		return "Voce [id=" + id + ", name=" + name + ", opis=" + opis + ", nadmorskaVisina=" + nadmorskaVisina
				+ ", minTemperatura=" + minTemperatura + ", maxTemperatura=" + maxTemperatura + ", kolicinaPadavina="
				+ kolicinaPadavina + ", prosecnaGodisnjaTemperatura=" + prosecnaGodisnjaTemperatura + ", phCategory="
				+ phCategory + ", calcCategory=" + calcCategory + "]";
	}
	
}
