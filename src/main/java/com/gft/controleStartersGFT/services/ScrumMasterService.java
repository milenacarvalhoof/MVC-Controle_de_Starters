package com.gft.controleStartersGFT.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.controleStartersGFT.entities.ScrumMaster;
import com.gft.controleStartersGFT.repositories.ScrumMasterRepository;

@Service
public class ScrumMasterService {
	
	@Autowired
	ScrumMasterRepository scrumMasterRepository;
	
	public ScrumMaster salvarScrumMaster(ScrumMaster scrumMaster) {
		return scrumMasterRepository.save(scrumMaster);
	} 
	
	public List<ScrumMaster> listarScrumMasters(){
		return scrumMasterRepository.findAll();
	}
	
	public ScrumMaster obterScrumMaster(Long id) throws Exception{
			
		Optional<ScrumMaster> scrumMaster = scrumMasterRepository.findById(id);
		
		if (scrumMaster.isEmpty()) {
			throw new Exception("ScrumMaster não encontrado(a)!");
		}
		return scrumMaster.get();	
	}
	
	public void excluirScrumMaster(Long id){
		scrumMasterRepository.deleteById(id);
	}
}
