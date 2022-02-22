package com.gft.controleStartersGFT.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.controleStartersGFT.entities.Tecnologia;
import com.gft.controleStartersGFT.repositories.TecnologiaRepository;

@Service
public class TecnologiaService {
	
	@Autowired
	TecnologiaRepository tecnologiaRepository;
	
	public Tecnologia salvarTecnologia(Tecnologia tecnologia) {
		return tecnologiaRepository.save(tecnologia);
	} 
	
	public List<Tecnologia> listarTecnologias(){
		return tecnologiaRepository.findAll();
	}
	
	public Tecnologia obterTecnologia(Long id) throws Exception{
			
		Optional<Tecnologia> tecnologia = tecnologiaRepository.findById(id);
		
		if (tecnologia.isEmpty()) {
			throw new Exception("Tecnologia não encontrada!");
		}
		return tecnologia.get();	
	}
	
	public void excluirTecnologia(Long id){
		tecnologiaRepository.deleteById(id);
	}

}
