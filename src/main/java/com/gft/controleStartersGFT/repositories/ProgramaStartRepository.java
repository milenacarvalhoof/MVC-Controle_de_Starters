package com.gft.controleStartersGFT.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.controleStartersGFT.entities.ProgramaStart;

@Repository
public interface ProgramaStartRepository extends JpaRepository<ProgramaStart, Long>{

}
