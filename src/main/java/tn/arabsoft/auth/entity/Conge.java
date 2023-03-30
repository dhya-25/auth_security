package tn.arabsoft.auth.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="conge")
public class Conge implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long  idConge ; 
	private String MatriculeC ;
	private Date dateDebut  ;
	private  Date dateFin ;
	private int duree;
	private String statut ;
	
	@ManyToOne
	@JoinColumn(name="idEmploye")
	private Personnel personnel;
	
	@ManyToOne
	@JoinColumn(name="idType")
	private TypeConge typeConge;
	
	public Conge() {
		// TODO Auto-generated constructor stub
	}
	
	

	public Conge(String matriculeC, Date dateDebut, Date dateFin, int duree, String statut) {
		super();
		MatriculeC = matriculeC;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.duree = duree;
		this.statut = statut;
	}



	public Long getIdConge() {
		return idConge;
	}

	public void setIdConge(Long idConge) {
		this.idConge = idConge;
	}

	public String getMatriculeC() {
		return MatriculeC;
	}

	public void setMatriculeC(String matriculeC) {
		MatriculeC = matriculeC;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}



	public Personnel getPersonnel() {
		return personnel;
	}



	public void setPersonnel(Personnel personnel) {
		this.personnel = personnel;
	}



	public TypeConge getTypeConge() {
		return typeConge;
	}



	public void setTypeConge(TypeConge typeConge) {
		this.typeConge = typeConge;
	}

	

	
	
	
}