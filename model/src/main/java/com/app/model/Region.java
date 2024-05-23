package com.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Region {
	
	@Id
	@Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "naziv")
	private String naziv;
	
	@Column(name = "nadmorska_visina")
	private double nadmorskaVisina;
	
	@Column(name = "prosecna_godisnja_temperatura") 
	private double prosecnaGodisnjaTemperatura;
	
	@Column(name = "min_temperatura")
	private double minTemperatura;
	
	@Column(name = "max_temperatura")
	private double maxTemperatura;
	
	@Column(name = "kolicina_padavina")
	private double kolicinaPadavina;
	
	public Region() {}

	public Region(int id, String naziv, double nadmorskaVisina, double prosecnaGodisnjaTemperatura,
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
	
	@Override
	public String toString() {
		return "Region [id=" + id + ", naziv=" + naziv + ", nadmorskaVisina=" + nadmorskaVisina
				+ ", prosecnaGodisnjaTemperatura=" + prosecnaGodisnjaTemperatura + ", minTemperatura=" + minTemperatura
				+ ", maxTemperatura=" + maxTemperatura + ", kolicinaPadavina=" + kolicinaPadavina + "]";
	}

}
