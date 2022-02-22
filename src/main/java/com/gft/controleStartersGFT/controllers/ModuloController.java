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

import com.gft.controleStartersGFT.entities.Modulo;
import com.gft.controleStartersGFT.services.DailyService;
import com.gft.controleStartersGFT.services.ModuloService;
import com.gft.controleStartersGFT.services.ProjetoService;

@Controller
@RequestMapping("modulo")
public class ModuloController {

	@Autowired
	private ModuloService moduloService;
	
	@Autowired
	private DailyService dailyService;
	
	@Autowired
	private ProjetoService projetoService;
		
	@RequestMapping("/novo")
	public ModelAndView novoModulo() {
		
		ModelAndView mv = new ModelAndView("modulo/form.html");
		
		mv.addObject("modulo", new Modulo());
		mv.addObject("listaDailys", dailyService.listarTodasDailys());
		mv.addObject("listaProjetos", projetoService.listarTodosProjetos());
		
		return mv;		
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "novo")
	public ModelAndView salvarModulo(@Valid Modulo modulo, BindingResult bindingResult) {
		
		ModelAndView mv = new ModelAndView("modulo/form.html");
		
		boolean novo = true;
		
		if (modulo.getId() != null) {
			novo = false;
		}
		
		if (bindingResult.hasErrors()) {
			mv.addObject("modulo", modulo);
			return mv;
		}
		
		moduloService.salvarModulo(modulo);
		
		if (novo) {
			mv.addObject("modulo", new Modulo());
		} else {
			mv.addObject("modulo", modulo);
		}
		
		mv.addObject("mensagem", "Modulo salvo com sucesso!");
		mv.addObject("listaDailys", dailyService.listarTodasDailys());
		mv.addObject("listaProjetos", projetoService.listarTodosProjetos());
		
		return mv;
	}
	
	@RequestMapping
	public ModelAndView listarModulos() {
		
		ModelAndView mv = new ModelAndView("modulo/listar.html");
		mv.addObject("lista", moduloService.listarModulos());
		
		return mv;
	}
	
	@RequestMapping(path = "/editar")
	public ModelAndView editarModulo(@RequestParam Long id) {
		
		ModelAndView mv = new ModelAndView("modulo/form.html");
		Modulo modulo;
		
		try {
			modulo = moduloService.obterModulo(id);
		} catch (Exception e) {
			modulo = new Modulo();
			mv.addObject("mensagem", e.getMessage());
		}
		
		mv.addObject("modulo", modulo);
		mv.addObject("listaDailys", dailyService.listarTodasDailys());
		mv.addObject("listaProjetos", projetoService.listarTodosProjetos());
		
		return mv;
	}
	
	@RequestMapping(path = "/obter")
	public ModelAndView obterModulo(@RequestParam Long id) {
		
		ModelAndView mv = new ModelAndView("modulo/listar.html");
		
		try {
			mv.addObject("lista", moduloService.obterModulo(id));
		} catch (Exception e1) {
			mv.addObject("mensagem", e1.getMessage());
		}
		
		return mv;
	}
	
	@RequestMapping(path = "/excluir")
	public ModelAndView excluirModulo(@RequestParam Long id, RedirectAttributes redirectAttributes) {
		
		ModelAndView mv = new ModelAndView("redirect:/modulo");
		
		try {
			moduloService.excluirModulo(id);
			redirectAttributes.addFlashAttribute("mensagem", "Modulo Excluído com sucesso!");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("mensagem", "Erro ao excluir modulo!" + e.getMessage());
		}
		
		return mv;
	}
}
