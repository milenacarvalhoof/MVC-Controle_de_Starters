package com.gft.controleStartersGFT.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.controleStartersGFT.entities.Modulo;
import com.gft.controleStartersGFT.repositories.ModuloRepository;

@Service
public class ModuloService {
	
	@Autowired
	ModuloRepository moduloRepository;
	
	public Modulo salvarModulo(Modulo modulo) {
		return moduloRepository.save(modulo);
	} 
	
	public List<Modulo> listarModulos(){
		return moduloRepository.findAll();
	}
	
	public Modulo obterModulo(Long id) throws Exception{
			
		Optional<Modulo> modulo = moduloRepository.findById(id);
		
		if (modulo.isEmpty()) {
			throw new Exception("Modulo não encontrado!");
		}
		return modulo.get();	
	}
	
	public void excluirModulo(Long id){
		moduloRepository.deleteById(id);
	}
}
