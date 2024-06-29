package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.model.Carrera;

import ar.edu.unju.fi.service.ICarreraService;

@Controller
public class CarreraController {
	// Inyeccion de dependencia
	@Autowired
	Carrera carrera;
	@Autowired
	ICarreraService carreraService;
	
	@GetMapping("/listaCarrera")
	public ModelAndView getCarreras() {
		ModelAndView modelAndView = new ModelAndView("listaDeCarreras");
		modelAndView.addObject("listadoCarreras", carreraService.mostrarCarreras());
		return modelAndView;
	}
	

	@GetMapping("/formularioCarrera")
	public ModelAndView getFormCarrera() {
		ModelAndView modelAndView = new ModelAndView("formCarrera");
		modelAndView.addObject("nuevaCarrera", carrera);
		modelAndView.addObject("estado", false);
		return modelAndView;
	}
	
	@PostMapping("/guardarCarrera")
	public ModelAndView guardarCarrera(@ModelAttribute("nuevaCarrera") Carrera carreraNuevaParaGuardar) {
		carreraService.guardarCarrera(carreraNuevaParaGuardar);
		ModelAndView modelAndView = new ModelAndView("listaDeCarreras");
		modelAndView.addObject("listadoCarreras", carreraService.mostrarCarreras());
		return modelAndView;
	}
	
	  @GetMapping ("/eliminarCarrera/{codigo}") 
	  public ModelAndView borrarCarrera(@PathVariable (name="codigo") String codigo) {
		  carreraService.eliminarCarrera(codigo); 
		  ModelAndView modelAndView = new ModelAndView("listaDeCarreras"); 
		  modelAndView.addObject("listadoCarreras", carreraService.mostrarCarreras()); 
		  return modelAndView; 
	  }
	  
	  @GetMapping("/modificarCarrera/{codigo}") 
	  public ModelAndView getFormModificarCarrera(@PathVariable(name="codigo") String codigo) { 
		  Carrera carreraAModificar = carreraService.buscarCarreraPorCodigo(codigo);
		  ModelAndView modelAndView = new ModelAndView("formCarrera");
		  modelAndView.addObject("nuevaCarrera", carreraAModificar);
		  modelAndView.addObject("estado", true); 
		  return modelAndView; 
	  }
	  
	  @PostMapping("/modificarCarrera") 
	  public ModelAndView modificarCarrera(@ModelAttribute("nuevaCarrera") Carrera carreraAModificar) { 
		  carreraService.modificarCarrera(carreraAModificar); 
		  ModelAndView modelAndView = new ModelAndView("listaDeCarreras");
		  modelAndView.addObject("listadoCarreras", carreraService.mostrarCarreras());
	  return modelAndView;  
	  }

}