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

import com.gft.controleStartersGFT.entities.Projeto;
import com.gft.controleStartersGFT.services.ModuloService;
import com.gft.controleStartersGFT.services.ProjetoService;
import com.gft.controleStartersGFT.services.StarterService;

@Controller
@RequestMapping("projeto")
public class ProjetoController {

	@Autowired
	private ProjetoService projetoService;
	
	@Autowired
	private StarterService starterService;
	
	@Autowired
	private ModuloService moduloService;
		
	@RequestMapping("/novo")
	public ModelAndView novoProjeto() {
		
		ModelAndView mv = new ModelAndView("projeto/form.html");
		mv.addObject("projeto", new Projeto());
		mv.addObject("listaStarters", starterService.listarStarters());
		mv.addObject("listaModulos", moduloService.listarModulos());
		
		return mv;		
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "novo")
	public ModelAndView salvarProjeto(@Valid Projeto projeto, BindingResult bindingResult) {
		
		ModelAndView mv = new ModelAndView("projeto/form.html");
		
		boolean novo = true;
		
		if (projeto.getId() != null) {
			novo = false;
		}
		
		if (bindingResult.hasErrors()) {
			mv.addObject("projeto", projeto);
			return mv;
		}
		
		projetoService.salvarProjeto(projeto);
		
		if (novo) {
			mv.addObject("projeto", new Projeto());
		} else {
			mv.addObject("projeto", projeto);
		}
		
		mv.addObject("mensagem", "Projeto salvo com sucesso!");
		mv.addObject("listaStarters", starterService.listarStarters());
		mv.addObject("listaModulos", moduloService.listarModulos());
		
		return mv;
	}
	
	@RequestMapping
	public ModelAndView listarProjetos() {
		
		ModelAndView mv = new ModelAndView("projeto/listar.html");
		mv.addObject("lista", projetoService.listarTodosProjetos());
				
		return mv;
	}
	
	@RequestMapping(path = "/obter")
	public ModelAndView obterProjeto(@RequestParam Long id) {
		
		ModelAndView mv = new ModelAndView("projeto/listar.html");
		
		try {
			mv.addObject("lista", projetoService.obterProjeto(id));
		} catch (Exception e1) {
			mv.addObject("mensagem", e1.getMessage());
		}
		
		return mv;
	}
	
	@RequestMapping("/maioresNotas")
	public ModelAndView maioresNotas() {
		
		ModelAndView mv = new ModelAndView("projeto/maiorNota.html");		
		mv.addObject("lista", projetoService.maioresNotas());
		
		return mv;
	}
	
	@RequestMapping("/menoresNotas")
	public ModelAndView menoresNotas() {
		
		ModelAndView mv = new ModelAndView("projeto/menorNota.html");
		mv.addObject("lista", projetoService.menoresNotas());
		
		return mv;
	}
	
	@RequestMapping(path = "/editar")
	public ModelAndView editarProjeto(@RequestParam(required = false) Long id) {
		
		ModelAndView mv = new ModelAndView("projeto/form.html");
		Projeto projeto;
		
		if (id == null) {
			projeto = new Projeto();			
		} else{
			try {
				projeto = projetoService.obterProjeto(id);
			} catch (Exception e) {
				projeto = new Projeto();
				mv.addObject("mensagem", e.getMessage());
			}
		}
		
		mv.addObject("projeto", projeto);
		mv.addObject("listaStarters", starterService.listarStarters());
		mv.addObject("listaModulos", moduloService.listarModulos());
		
		return mv;
	}
	
	@RequestMapping(path = "/excluir")
	public ModelAndView excluirProjeto(@RequestParam Long id, RedirectAttributes redirectAttributes) {
		
		ModelAndView mv = new ModelAndView("redirect:/projeto");
		
		try {
			projetoService.excluirProjeto(id);
			redirectAttributes.addFlashAttribute("mensagem", "Projeto Excluído com sucesso!");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("mensagem", "Erro ao excluir projeto!" + e.getMessage());
		}
		
		return mv;
	}
}
