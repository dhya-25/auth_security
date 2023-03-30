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
@Table(name="Service")

	
	public class Service implements Serializable {
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
	      private Long idService ;
		  private Long idChef;
	      private String nomService ;
	     
	      
//	     @OneToMany(mappedBy="role",fetch = FetchType.LAZY)
//	  	private Collection<Personnel> personnels;

	     public Service() {
			// TODO Auto-generated constructor stub
		}

		public Long getIdService() {
			return idService;
		}

		public void setIdService(Long idService) {
			this.idService = idService;
		}

		public Long getIdChef() {
			return idChef;
		}

		public void setIdChef(Long idChef) {
			this.idChef = idChef;
		}

		public String getNomService() {
			return nomService;
		}

		public void setNomService(String nomService) {
			this.nomService = nomService;
		}


	     
	     
}
