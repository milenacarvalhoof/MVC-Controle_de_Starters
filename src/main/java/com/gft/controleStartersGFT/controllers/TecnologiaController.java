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

import com.gft.controleStartersGFT.entities.Tecnologia;
import com.gft.controleStartersGFT.services.TecnologiaService;

@Controller
@RequestMapping("tecnologia")
public class TecnologiaController {

	@Autowired
	private TecnologiaService tecnologiaService;
		
	@RequestMapping("/novo")
	public ModelAndView novaTecnologia() {
		
		ModelAndView mv = new ModelAndView("tecnologia/form.html");
		mv.addObject("tecnologia", new Tecnologia());
		
		return mv;		
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "novo")
	public ModelAndView salvarTecnologia(@Valid Tecnologia tecnologia, BindingResult bindingResult) {
		
		ModelAndView mv = new ModelAndView("tecnologia/form.html");
		
		boolean novo = true;
		
		if (tecnologia.getId() != null) {
			novo = false;
		}
		
		if (bindingResult.hasErrors()) {
			mv.addObject("tecnologia", tecnologia);
			return mv;
		}
		
		tecnologiaService.salvarTecnologia(tecnologia);
		
		if (novo) {
			mv.addObject("tecnologia", new Tecnologia());
		} else {
			mv.addObject("tecnologia", tecnologia);
		}
		
		mv.addObject("mensagem", "Tecnologia salva com sucesso!");
		
		return mv;
	}
	
	@RequestMapping
	public ModelAndView listarTecnologias() {
		
		ModelAndView mv = new ModelAndView("tecnologia/listar.html");
		mv.addObject("lista", tecnologiaService.listarTecnologias());
		
		return mv;
	}
	
	@RequestMapping(path = "/obter")
	public ModelAndView obterTecnologia(@RequestParam Long id) {
		
		ModelAndView mv = new ModelAndView("tecnologia/listar.html");
		
		try {
			mv.addObject("lista", tecnologiaService.obterTecnologia(id));
		} catch (Exception e1) {
			mv.addObject("mensagem", e1.getMessage());
		}
		
		return mv;
	}
	
	@RequestMapping(path = "/editar")
	public ModelAndView editarTecnologia(@RequestParam Long id) {
		
		ModelAndView mv = new ModelAndView("tecnologia/form.html");
		Tecnologia tecnologia;
		
		try {
			tecnologia = tecnologiaService.obterTecnologia(id);
		} catch (Exception e) {
			tecnologia = new Tecnologia();
			mv.addObject("mensagem", e.getMessage());
		}
		
		mv.addObject("tecnologia", tecnologia);
		return mv;
	}
	
	@RequestMapping(path = "/excluir")
	public ModelAndView excluirTecnologia(@RequestParam Long id, RedirectAttributes redirectAttributes) {
		
		ModelAndView mv = new ModelAndView("redirect:/tecnologia");
		
		try {
			tecnologiaService.excluirTecnologia(id);
			redirectAttributes.addFlashAttribute("mensagem", "Tecnologia Excluído com sucesso!");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("mensagem", "Erro ao excluir tecnologia!" + e.getMessage());
		}
		
		return mv;
	}
}
