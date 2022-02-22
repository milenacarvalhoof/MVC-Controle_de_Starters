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

import com.gft.controleStartersGFT.entities.Daily;
import com.gft.controleStartersGFT.services.DailyService;
import com.gft.controleStartersGFT.services.GrupoService;
import com.gft.controleStartersGFT.services.ModuloService;
import com.gft.controleStartersGFT.services.StarterService;

@Controller
@RequestMapping("daily")
public class DailyController {
	
	@Autowired
	private DailyService dailyService;
	
	@Autowired
	private StarterService starterService;
	
	@Autowired
	private ModuloService moduloService;
	
	@Autowired
	private GrupoService grupoService;
		
	@RequestMapping("/novo")
	public ModelAndView novaDaily() {
		
		ModelAndView mv = new ModelAndView("daily/form.html");
		
		mv.addObject("daily", new Daily());
		mv.addObject("listaStarters", starterService.listarStarters());
		mv.addObject("listaModulos", moduloService.listarModulos());
		mv.addObject("listaGrupos", grupoService.listarGrupos());
		
		return mv;		
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "novo")
	public ModelAndView salvarDaily(@Valid Daily daily, BindingResult bindingResult) {
		
		ModelAndView mv = new ModelAndView("daily/form.html");
		
		boolean novo = true;
		
		if (daily.getId() != null) {
			novo = false;
		}
		
		if (bindingResult.hasErrors()) {
			mv.addObject("daily", daily);
			return mv;
		}
		
		dailyService.salvarDaily(daily);
		
		if (novo) {
			mv.addObject("daily", new Daily());
		} else {
			mv.addObject("daily", daily);
		}
		
		mv.addObject("mensagem", "Daily salva com sucesso!");
		mv.addObject("listaStarters", starterService.listarStarters());
		mv.addObject("listaModulos", moduloService.listarModulos());
		mv.addObject("listaGrupos", grupoService.listarGrupos());
		
		return mv;
	}
	
	@RequestMapping
	public ModelAndView listarDailys() {
		
		ModelAndView mv = new ModelAndView("daily/listar.html");
		mv.addObject("lista", dailyService.listarTodasDailys());
				
		return mv;
	}
	
	@RequestMapping(path = "/obter")
	public ModelAndView obterDaily(@RequestParam Long id) {
		
		ModelAndView mv = new ModelAndView("daily/listar.html");
		
		try {
			mv.addObject("lista", dailyService.obterDaily(id));
		} catch (Exception e1) {
			mv.addObject("mensagem", e1.getMessage());
		}
		
		return mv;
	}
	
	@RequestMapping(path = "/editar")
	public ModelAndView editarDaily(@RequestParam(required = false) Long id) {
		
		ModelAndView mv = new ModelAndView("daily/form.html");
		Daily daily;
		
		try {
			daily = dailyService.obterDaily(id);
		} catch (Exception e) {
			daily = new Daily();
			mv.addObject("mensagem", e.getMessage());
		}
		
		mv.addObject("daily", daily);
		mv.addObject("listaStarters", starterService.listarStarters());
		mv.addObject("listaModulos", moduloService.listarModulos());
		mv.addObject("listaGrupos", grupoService.listarGrupos());
		
		return mv;
	}
	
	@RequestMapping(path = "/excluir")
	public ModelAndView excluirDaily(@RequestParam Long id, RedirectAttributes redirectAttributes) {
		
		ModelAndView mv = new ModelAndView("redirect:/daily");
		
		try {
			dailyService.excluirDaily(id);
			redirectAttributes.addFlashAttribute("mensagem", "Daily Excluída com sucesso!");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("mensagem", "Erro ao excluir daily!" + e.getMessage());
		}
		
		return mv;
	}

}
