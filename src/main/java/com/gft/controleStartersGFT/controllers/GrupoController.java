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

import com.gft.controleStartersGFT.entities.Grupo;
import com.gft.controleStartersGFT.services.GrupoService;
import com.gft.controleStartersGFT.services.ScrumMasterService;
import com.gft.controleStartersGFT.services.StarterService;
import com.gft.controleStartersGFT.services.TecnologiaService;

@Controller
@RequestMapping("grupo")
public class GrupoController {
	
	@Autowired
	private GrupoService grupoService;
	
	@Autowired
	private StarterService starterService;
	
	@Autowired
	private TecnologiaService tecnologiaService;
	
	@Autowired
	private ScrumMasterService scrumMasterService;
		
	@RequestMapping("/novo")
	public ModelAndView novoGrupo() {
		
		ModelAndView mv = new ModelAndView("grupo/form.html");
		mv.addObject("grupo", new Grupo());
		mv.addObject("listaStarters", starterService.listarStarters());
		mv.addObject("listaTecnologias", tecnologiaService.listarTecnologias());
		mv.addObject("listaScrumMasters", scrumMasterService.listarScrumMasters());
		
		return mv;		
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "novo")
	public ModelAndView salvarGrupo(@Valid Grupo grupo, BindingResult bindingResult) {
		
		ModelAndView mv = new ModelAndView("grupo/form.html");
		
		boolean novo = true;
		
		if (grupo.getId() != null) {
			novo = false;
		}
		
		if (bindingResult.hasErrors()) {
			mv.addObject("grupo", grupo);
			return mv;
		}
		
		grupoService.salvarGrupo(grupo);
		
		if (novo) {
			mv.addObject("grupo", new Grupo());
		} else {
			mv.addObject("grupo", grupo);
		}
		
		mv.addObject("mensagem", "Grupo salvo com sucesso!");
		mv.addObject("listaStarters", starterService.listarStarters());
		mv.addObject("listaTecnologias", tecnologiaService.listarTecnologias());
		mv.addObject("listaScrumMasters", scrumMasterService.listarScrumMasters());
		
		return mv;
	}
	
	@RequestMapping
	public ModelAndView listarGrupo() {
		
		ModelAndView mv = new ModelAndView("grupo/listar.html");
		mv.addObject("lista", grupoService.listarGrupos());
		
		return mv;
	}
	
	@RequestMapping(path = "/obter")
	public ModelAndView obterGrupo(@RequestParam Long id) {
		
		ModelAndView mv = new ModelAndView("grupo/listar.html");
		
		try {
			mv.addObject("lista", grupoService.obterGrupo(id));
		} catch (Exception e1) {
			mv.addObject("mensagem", e1.getMessage());
		}
		
		return mv;
	}
	
	@RequestMapping(path = "/editar")
	public ModelAndView editarGrupo(@RequestParam(required = false) Long id) {
		
		ModelAndView mv = new ModelAndView("grupo/form.html");
		Grupo grupo;
		
		try {
			grupo = grupoService.obterGrupo(id);
		} catch (Exception e) {
			grupo = new Grupo();
			mv.addObject("mensagem", e.getMessage());
		}
		
		mv.addObject("grupo", grupo);
		mv.addObject("listaStarters", starterService.listarStarters());
		mv.addObject("listaTecnologias", tecnologiaService.listarTecnologias());
		mv.addObject("listaScrumMasters", scrumMasterService.listarScrumMasters());
		
		return mv;
	}
	
	@RequestMapping(path = "/excluir")
	public ModelAndView excluirGrupo(@RequestParam Long id, RedirectAttributes redirectAttributes) {
		
		ModelAndView mv = new ModelAndView("redirect:/grupo");
		
		try {
			grupoService.excluirGrupo(id);
			redirectAttributes.addFlashAttribute("mensagem", "Grupo Excluído com sucesso!");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("mensagem", "Erro ao excluir grupo!" + e.getMessage());
		}
		
		return mv;
	}

}
