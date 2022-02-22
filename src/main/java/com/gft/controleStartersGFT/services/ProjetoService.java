package com.gft.controleStartersGFT.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.controleStartersGFT.entities.Projeto;
import com.gft.controleStartersGFT.repositories.ProjetoRepository;

@Service
public class ProjetoService {

	@Autowired
	ProjetoRepository projetoRepository;
	
	public Projeto salvarProjeto(Projeto projeto) {
		return projetoRepository.save(projeto);
	}
	
	public List<Projeto> listarTodosProjetos(){
		return projetoRepository.findAll();
	}
	
	public Projeto obterProjeto(Long id) throws Exception{
			
		Optional<Projeto> projeto = projetoRepository.findById(id);
		
		if (projeto.isEmpty()) {
			throw new Exception("Projeto não encontrado!");
		}
		return projeto.get();	
	}
	
	public List<Projeto> maioresNotas() {
		
		List<Projeto> lista = listarTodosProjetos();
		
		Comparator<Projeto> maiorNota = (projeto1, projeto2) -> { 
		      if (projeto1.getNota() > projeto2.getNota()) return 1;
		      if (projeto1.getNota() < projeto2.getNota()) return -1;
		      return 0;
		};
		
		int qtdModulos = 4;

		List<Projeto> listaMelhoresNotas = new ArrayList<>();
		
		for (int i = 0; i < qtdModulos; i++) {
			Long x = (long) i+1;
			Stream<Projeto> etapa = lista.stream().filter(p -> p.getEtapa().getId() == x);
			listaMelhoresNotas.add(etapa.max(maiorNota).get());
		}
		
		return listaMelhoresNotas;	
	}
	
	public List<Projeto> menoresNotas() {
		
		List<Projeto> lista = listarTodosProjetos();
		
		Comparator<Projeto> menorNota = (projeto1, projeto2) -> { 
		      if (projeto1.getNota() > projeto2.getNota()) return 1;
		      if (projeto1.getNota() < projeto2.getNota()) return -1;
		      return 0;
		};

		int qtdModulos = 4;

		List<Projeto> listaMenoresNotas = new ArrayList<>();
		
		for (int i = 0; i < qtdModulos; i++) {
			Long x = (long) i+1;
			Stream<Projeto> etapa = lista.stream().filter(p -> p.getEtapa().getId() == x);
			listaMenoresNotas.add(etapa.min(menorNota).get());
		}
		
		return listaMenoresNotas;
	}
	
	public void excluirProjeto(Long id){
		projetoRepository.deleteById(id);
	}
}
