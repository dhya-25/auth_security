package tn.arabsoft.auth.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
public class TypeConge implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idType;
	private String nomTypeconge;
	
	@OneToMany(mappedBy ="typeConge" ,fetch= FetchType.LAZY)
	private Collection<Conge> Conges;
	
	public TypeConge() {
		// TODO Auto-generated constructor stub
	}

	public TypeConge(String nomTypeconge) {
		super();
		this.nomTypeconge = nomTypeconge;
	}

	public Long getIdType() {
		return idType;
	}

	public void setIdType(Long idType) {
		this.idType = idType;
	}

	public String getNomTypeconge() {
		return nomTypeconge;
	}

	public void setNomTypeconge(String nomTypeconge) {
		this.nomTypeconge = nomTypeconge;
	}

	public Collection<Conge> getConges() {
		return Conges;
	}

	public void setConges(Collection<Conge> conges) {
		Conges = conges;
	}
	

}