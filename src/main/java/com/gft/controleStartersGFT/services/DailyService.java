package com.gft.controleStartersGFT.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.controleStartersGFT.entities.Daily;
import com.gft.controleStartersGFT.repositories.DailyRepository;

@Service
public class DailyService {
	
	@Autowired
	DailyRepository dailyRepository;
	
	public Daily salvarDaily(Daily daily) {
		return dailyRepository.save(daily);
	}
	
	public List<Daily> listarTodasDailys(){
		return dailyRepository.findAll();
	}
	
	public Daily obterDaily(Long id) throws Exception{
			
		Optional<Daily> daily = dailyRepository.findById(id);
		
		if (daily.isEmpty()) {
			throw new Exception("Daily não encontrada!");
		}
		return daily.get();	
	}
	
	public void excluirDaily(Long id){
		dailyRepository.deleteById(id);
	}
}
