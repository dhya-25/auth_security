package tn.arabsoft.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.arabsoft.auth.entity.Personnel;

public interface UserRepository extends JpaRepository<Personnel, Integer>{
	 Optional<Personnel> findByNom(String username);
	  Optional<Personnel> findByMatriculeP(String matricule);

}
