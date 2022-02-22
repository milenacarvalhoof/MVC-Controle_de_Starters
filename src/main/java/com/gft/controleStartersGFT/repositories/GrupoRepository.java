package com.gft.controleStartersGFT.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.controleStartersGFT.entities.Grupo;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long> {

}
