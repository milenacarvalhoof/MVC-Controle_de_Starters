package com.gft.controleStartersGFT.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.controleStartersGFT.entities.Starter;

@Repository
public interface StarterRepository extends JpaRepository<Starter, Long>{

}
