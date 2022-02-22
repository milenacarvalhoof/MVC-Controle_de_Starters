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

import com.gft.controleStartersGFT.entities.Starter;
import com.gft.controleStartersGFT.services.StarterService;

@Controller
@RequestMapping("starter")
public class StarterController {

	@Autowired
	private StarterService starterService;
		
	@RequestMapping("/novo")
	public ModelAndView novoStarter() {
		
		ModelAndView mv = new ModelAndView("starter/form.html");
		mv.addObject("starter", new Starter());
		
		return mv;		
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "novo")
	public ModelAndView salvarStarter(@Valid Starter starter, BindingResult bindingResult) {
		
		ModelAndView mv = new ModelAndView("starter/form.html");
		
		boolean novo = true;
		
		if (starter.getId() != null) {
			novo = false;
		}
		
		if (bindingResult.hasErrors()) {
			mv.addObject("starter", starter);
			return mv;
		}
		
		starterService.salvarStarter(starter);
		
		if (novo) {
			mv.addObject("starter", new Starter());
		} else {
			mv.addObject("starter", starter);
		}
		
		mv.addObject("mensagem", "Starter salvo com sucesso!");
		
		return mv;
	}
	
	@RequestMapping
	public ModelAndView listarStarters() {
		
		ModelAndView mv = new ModelAndView("starter/listar.html");
		mv.addObject("lista", starterService.listarStarters());
		
		return mv;
	}
	
	@RequestMapping(path = "/obter")
	public ModelAndView obterStarter(@RequestParam Long id) {
		
		ModelAndView mv = new ModelAndView("starter/listar.html");
		
		try {
			mv.addObject("lista", starterService.obterStarter(id));
		} catch (Exception e1) {
			mv.addObject("mensagem", e1.getMessage());
		}
		
		return mv;
	}
	
	@RequestMapping(path = "/editar")
	public ModelAndView editarStarter(@RequestParam Long id) {
		
		ModelAndView mv = new ModelAndView("starter/form.html");
		Starter starter;
		
		try {
			starter = starterService.obterStarter(id);
		} catch (Exception e) {
			starter = new Starter();
			mv.addObject("mensagem", e.getMessage());
		}
		
		mv.addObject("starter", starter);
		return mv;
	}
	
	@RequestMapping(path = "/excluir")
	public ModelAndView excluirStarter(@RequestParam Long id, RedirectAttributes redirectAttributes) {
		
		ModelAndView mv = new ModelAndView("redirect:/starter");
		
		try {
			starterService.excluirStarter(id);
			redirectAttributes.addFlashAttribute("mensagem", "Starter Excluído com sucesso!");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("mensagem", "Erro ao excluir starter!" + e.getMessage());
		}
		
		return mv;
	}
}
