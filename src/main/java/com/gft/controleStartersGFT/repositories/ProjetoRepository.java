package com.gft.controleStartersGFT.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.controleStartersGFT.entities.Projeto;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long>{
	
}
