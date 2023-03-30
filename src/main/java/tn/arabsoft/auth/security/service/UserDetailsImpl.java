package tn.arabsoft.auth.security.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import tn.arabsoft.auth.entity.Personnel;

public class UserDetailsImpl implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idEmploye;
	private String email ;
	private String matriculeP ;
	private String nom ;
	private String prenom ;
	private String departement ;
	private String nomResponsable ;
	private int numTel ;
	private String poste ;
	private String password ;
	private Collection<? extends GrantedAuthority> authorities;


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return nom;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	public static UserDetailsImpl build(Personnel user) {
	    List<GrantedAuthority> authorities = user.getRoles().stream()
	        .map(role -> new SimpleGrantedAuthority(role.getNomRole().name()))
	        .collect(Collectors.toList());

    return new UserDetailsImpl(
        user.getIdEmploye(), 
        user.getNomResponsable(),user.getMatriculeP(), 
        user.getEmail(),
        user.getPassword(), 
        user.getNom(),user.getPrenom(),user.getNumTel(),user.getPoste(),user.getDepartement(),authorities);
  }
	

	public UserDetailsImpl() {
		super();
	}

	

	

	
	

	public UserDetailsImpl(Integer idEmploye, String email, String matriculeP, String nom, String password,
			String departement, String nomResponsable, int numTel, String poste, String prenom,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.idEmploye = idEmploye;
		this.email = email;
		this.matriculeP = matriculeP;
		this.nom = nom;
		this.prenom = prenom;
		this.departement = departement;
		this.nomResponsable = nomResponsable;
		this.numTel = numTel;
		this.poste = poste;
		this.password = password;
		this.authorities = authorities;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	

	public Integer getIdEmploye() {
		return idEmploye;
	}

	public void setIdEmploye(Integer idEmploye) {
		this.idEmploye = idEmploye;
	}

	public String getMatriculeP() {
		return matriculeP;
	}

	public void setMatriculeP(String matriculeP) {
		this.matriculeP = matriculeP;
	}

	public String getDepartement() {
		return departement;
	}

	public void setDepartement(String departement) {
		this.departement = departement;
	}

	public String getNomResponsable() {
		return nomResponsable;
	}

	public void setNomResponsable(String nomResponsable) {
		this.nomResponsable = nomResponsable;
	}

	public int getNumTel() {
		return numTel;
	}

	public void setNumTel(int numTel) {
		this.numTel = numTel;
	}

	public String getPoste() {
		return poste;
	}

	public void setPoste(String poste) {
		this.poste = poste;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}


	

}
