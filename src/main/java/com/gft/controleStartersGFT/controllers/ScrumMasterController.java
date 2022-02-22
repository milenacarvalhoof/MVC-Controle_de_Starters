package com.gft.controleStartersGFT.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gft.controleStartersGFT.entities.ScrumMaster;
import com.gft.controleStartersGFT.services.ScrumMasterService;

@Controller
@RequestMapping("scrumMaster")
public class ScrumMasterController {

	@Autowired
	private ScrumMasterService scrumMasterService;
		
	@RequestMapping("/novo")
	public ModelAndView novoScrumMaster() {
		
		ModelAndView mv = new ModelAndView("scrumMaster/form.html");
		mv.addObject("scrumMaster", new ScrumMaster());
		
		return mv;		
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "novo")
	public ModelAndView salvarScrumMaster(@Valid ScrumMaster scrumMaster, BindingResult bindingResult) {
		
		ModelAndView mv = new ModelAndView("scrumMaster/form.html");
		
		boolean novo = true;
		
		if (scrumMaster.getId() != null) {
			novo = false;
		}
		
		if (bindingResult.hasErrors()) {
			mv.addObject("scrumMaster", scrumMaster);
			return mv;
		}
		
		scrumMasterService.salvarScrumMaster(scrumMaster);
		
		if (novo) {
			mv.addObject("scrumMaster", new ScrumMaster());
		} else {
			mv.addObject("scrumMaster", scrumMaster);
		}
		
		mv.addObject("mensagem", "ScrumMaster salvo com sucesso!");
		
		return mv;
	}
	
	@RequestMapping
	public ModelAndView listarScrumMasters() {
		
		ModelAndView mv = new ModelAndView("scrumMaster/listar.html");
		mv.addObject("lista", scrumMasterService.listarScrumMasters());
		
		return mv;
	}
	
	@RequestMapping(path = "/obter")
	public ModelAndView obterScrumMaster(@RequestParam Long id) {
		
		ModelAndView mv = new ModelAndView("scrumMaster/listar.html");
		
		try {
			mv.addObject("lista", scrumMasterService.obterScrumMaster(id));
		} catch (Exception e1) {
			mv.addObject("mensagem", e1.getMessage());
		}
		
		return mv;
	}
	
	@RequestMapping(path = "/editar")
	public ModelAndView editarScrumMaster(@RequestParam Long id) {
		
		ModelAndView mv = new ModelAndView("scrumMaster/form.html");
		ScrumMaster scrumMaster;
		
		try {
			scrumMaster = scrumMasterService.obterScrumMaster(id);
		} catch (Exception e) {
			scrumMaster = new ScrumMaster();
			mv.addObject("mensagem", e.getMessage());
		}
		
		mv.addObject("scrumMaster", scrumMaster);
		return mv;
	}
	
	@RequestMapping(path = "/excluir")
	public ModelAndView excluirScrumMaster(@RequestParam Long id, RedirectAttributes redirectAttributes) {
		
		ModelAndView mv = new ModelAndView("redirect:/scrumMaster");
		
		try {
			scrumMasterService.excluirScrumMaster(id);
			redirectAttributes.addFlashAttribute("mensagem", "Scrum Master Excluído com sucesso!");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("mensagem", "Erro ao excluir Scrum Master!" + e.getMessage());
		}
		
		return mv;
	}
}
