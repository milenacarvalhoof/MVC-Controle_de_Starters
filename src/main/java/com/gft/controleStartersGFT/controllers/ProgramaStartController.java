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

import com.gft.controleStartersGFT.entities.ProgramaStart;
import com.gft.controleStartersGFT.services.ModuloService;
import com.gft.controleStartersGFT.services.ProgramaStartService;
import com.gft.controleStartersGFT.services.TecnologiaService;

@Controller
@RequestMapping("programaStart")
public class ProgramaStartController {

	@Autowired
	private ProgramaStartService programaStartService;
	
	@Autowired
	private TecnologiaService tecnologiaService;
	
	@Autowired
	private ModuloService moduloService;
		
	@RequestMapping("/novo")
	public ModelAndView novoProgramaStart() {
		
		ModelAndView mv = new ModelAndView("programaStart/form.html");
		
		mv.addObject("programaStart", new ProgramaStart());
		mv.addObject("listaTecnologias", tecnologiaService.listarTecnologias());
		mv.addObject("listaModulos", moduloService.listarModulos());
		
		return mv;		
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "novo")
	public ModelAndView salvarProgramaStart(@Valid ProgramaStart programaStart, BindingResult bindingResult) {
		
		ModelAndView mv = new ModelAndView("programaStart/form.html");
		
		boolean novo = true;
		
		if (programaStart.getId() != null) {
			novo = false;
		}
		
		if (bindingResult.hasErrors()) {
			mv.addObject("programaStart", programaStart);
			return mv;
		}
		
		programaStartService.salvarProgramaStart(programaStart);
		
		if (novo) {
			mv.addObject("programaStart", new ProgramaStart());
		} else {
			mv.addObject("programaStart", programaStart);
		}
		
		mv.addObject("mensagem", "ProgramaStart salvo com sucesso!");
		mv.addObject("listaTecnologias", tecnologiaService.listarTecnologias());
		mv.addObject("listaModulos", moduloService.listarModulos());
		
		return mv;
	}
	
	@RequestMapping
	public ModelAndView listarProgramasStarts() {
		
		ModelAndView mv = new ModelAndView("programaStart/listar.html");
		mv.addObject("lista", programaStartService.listarProgramasStarts());
		
		return mv;
	}
	
	@RequestMapping(path = "/obter")
	public ModelAndView obterProgramaStart(@RequestParam Long id) {
		
		ModelAndView mv = new ModelAndView("programaStart/listar.html");
		
		try {
			mv.addObject("lista", programaStartService.obterProgramaStart(id));
		} catch (Exception e1) {
			mv.addObject("mensagem", e1.getMessage());
		}
		
		return mv;
	}
	
	@RequestMapping(path = "/editar")
	public ModelAndView editarProgramaStart(@RequestParam(required = false) Long id) {
		
		ModelAndView mv = new ModelAndView("programaStart/form.html");
		ProgramaStart programaStart;
		
		if (id == null) {
			programaStart = new ProgramaStart();			
		} else{
			try {
				programaStart = programaStartService.obterProgramaStart(id);
			} catch (Exception e) {
				programaStart = new ProgramaStart();
				mv.addObject("mensagem", e.getMessage());
			}
		}	
		
		mv.addObject("programaStart", programaStart);
		mv.addObject("listaTecnologias", tecnologiaService.listarTecnologias());
		mv.addObject("listaModulos", moduloService.listarModulos());
		
		return mv;
	}
	
	@RequestMapping(path = "/excluir")
	public ModelAndView excluirProgramaStart(@RequestParam Long id, RedirectAttributes redirectAttributes) {
		
		ModelAndView mv = new ModelAndView("redirect:/programaStart");
		
		try {
			programaStartService.excluirProgramaStart(id);
			redirectAttributes.addFlashAttribute("mensagem", "ProgramaStart Excluído com sucesso!");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("mensagem", "Erro ao excluir programaStart!" + e.getMessage());
		}
		
		return mv;
	}
}
