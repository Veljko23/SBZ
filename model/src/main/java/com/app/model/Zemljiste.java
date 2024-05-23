package com.app.model;

import java.util.ArrayList;

public class Zemljiste {

	public enum phCategory {
        NA, ALKALNO, NEUTRALNO, SLABO_KISELO,
        KISELO, JAKO_KISELO
    };
    
    public enum calcCategory {
        NA, BESKARBONATNO, SLABO_KARBONATNO, SREDNJE_KARBONATNO, JAKO_KARBONATNO
    };
    
    private static final long serialVersionUID = 1L;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    private Long id;
    private Double phVrednost;
    private Double kalcijumKarbonat;
    private Double azot;
    private Double fosfor;
    private Double kalijum;
    private Double humus;
    private phCategory phCategory;
    private calcCategory calcCategory;
    private Region region;
    private String voce;
    private ArrayList<Voce> listaVoca;
   
	public Zemljiste(Long id, Double pHVrednost, Double kalcijumKarbonat) {
		super();
		this.id = id;
		this.phVrednost = pHVrednost;
		this.kalcijumKarbonat = kalcijumKarbonat;
		this.phCategory = phCategory.NA;
		this.calcCategory = calcCategory.NA;
		this.voce = "unknown";
	}
	
	public Zemljiste() {
		super();
	}
	
	public Zemljiste(Double phVrednost, Double kalcijumKarbonat) {
        this(null, phVrednost, kalcijumKarbonat);
    }
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getPhVrednost() {
		return phVrednost;
	}

	public void setPhVrednost(Double phVrednost) {
		this.phVrednost = phVrednost;
	}

	public Double getKalcijumKarbonat() {
		return kalcijumKarbonat;
	}

	public void setKalcijumKarbonat(Double kalcijumKarbonat) {
		this.kalcijumKarbonat = kalcijumKarbonat;
	}

	public Double getAzot() {
		return azot;
	}

	public void setAzot(Double azot) {
		this.azot = azot;
	}

	public Double getFosfor() {
		return fosfor;
	}

	public void setFosfor(Double fosfor) {
		this.fosfor = fosfor;
	}

	public Double getKalijum() {
		return kalijum;
	}

	public void setKalijum(Double kalijum) {
		this.kalijum = kalijum;
	}

	public Double getHumus() {
		return humus;
	}

	public void setHumus(Double humus) {
		this.humus = humus;
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

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public String getVoce() {
		return voce;
	}

	public void setVoce(String voce) {
		this.voce = voce;
	}

	public ArrayList<Voce> getListaVoca() {
		return listaVoca;
	}

	public void setListaVoca(ArrayList<Voce> listaVoca) {
		this.listaVoca = listaVoca;
	}

	@Override
	public String toString() {
		return "Zemljiste [id=" + id + ", phVrednost=" + phVrednost + ", kalcijumKarbonat=" + kalcijumKarbonat
				+ ", azot=" + azot + ", fosfor=" + fosfor + ", kalijum=" + kalijum + ", humus=" + humus
				+ ", phCategory=" + phCategory + ", calcCategory=" + calcCategory + ", region=" + region + ", voce="
				+ voce + ", listaVoca=" + listaVoca + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((calcCategory == null) ? 0 : calcCategory.hashCode());
		result = prime * result + ((kalcijumKarbonat == null) ? 0 : kalcijumKarbonat.hashCode());
		result = prime * result + ((phVrednost == null) ? 0 : phVrednost.hashCode());
		result = prime * result + ((phCategory == null) ? 0 : phCategory.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Zemljiste other = (Zemljiste) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (calcCategory != other.calcCategory)
			return false;
		if (kalcijumKarbonat == null) {
			if (other.kalcijumKarbonat != null)
				return false;
		} else if (!kalcijumKarbonat.equals(other.kalcijumKarbonat))
			return false;
		
		if (phVrednost == null) {
			if (other.phVrednost != null)
				return false;
		} else if (!phVrednost.equals(other.phVrednost))
			return false;
		if (phCategory != other.phCategory)
			return false;
		return true;
	}
    
}
