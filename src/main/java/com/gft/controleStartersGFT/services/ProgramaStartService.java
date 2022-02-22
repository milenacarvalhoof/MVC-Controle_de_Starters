package com.gft.controleStartersGFT.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.controleStartersGFT.entities.ProgramaStart;
import com.gft.controleStartersGFT.repositories.ProgramaStartRepository;

@Service
public class ProgramaStartService {

	@Autowired
	ProgramaStartRepository programaStartRepository;
	
	public ProgramaStart salvarProgramaStart(ProgramaStart programaStart) {
		return programaStartRepository.save(programaStart);
	} 
	
	public List<ProgramaStart> listarProgramasStarts(){
		return programaStartRepository.findAll();
	}
	
	public ProgramaStart obterProgramaStart(Long id) throws Exception{
			
		Optional<ProgramaStart> programaStart = programaStartRepository.findById(id);
		
		if (programaStart.isEmpty()) {
			throw new Exception("ProgramaStart não encontrado!");
		}
		return programaStart.get();	
	}
	
	public void excluirProgramaStart(Long id){
		programaStartRepository.deleteById(id);
	}
}
