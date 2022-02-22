package com.gft.controleStartersGFT.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.controleStartersGFT.entities.Modulo;

@Repository
public interface ModuloRepository extends JpaRepository<Modulo, Long>{

}
