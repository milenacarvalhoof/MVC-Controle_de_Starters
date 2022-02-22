package com.gft.controleStartersGFT.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.controleStartersGFT.entities.Starter;
import com.gft.controleStartersGFT.repositories.StarterRepository;

@Service
public class StarterService {

	@Autowired
	StarterRepository starterRepository;
	
	public Starter salvarStarter(Starter starter) {
		return starterRepository.save(starter);
	} 
	
	public List<Starter> listarStarters(){
		return starterRepository.findAll();
	}
	
	public Starter obterStarter(Long id) throws Exception{
			
		Optional<Starter> starter = starterRepository.findById(id);
		
		if (starter.isEmpty()) {
			throw new Exception("Starter não encontrado(a)!");
		}
		return starter.get();	
	}
	
	public void excluirStarter(Long id){
		starterRepository.deleteById(id);
	}
}
