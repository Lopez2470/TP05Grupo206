package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.service.IMateriaService;

@Controller
public class MateriaController {

	// Inyeccion de dependencia

	@Autowired
	Materia materia;

	@Autowired
	IMateriaService materiaService;
	
	@GetMapping("/listaMateria")
	public ModelAndView getMaterias() {
		ModelAndView modelAndView = new ModelAndView("listaDeMaterias");
		modelAndView.addObject("listadoMaterias", materiaService.mostrarMaterias());
		return modelAndView;
	}
	

	@GetMapping("/formularioMateria")
	public ModelAndView getFormMateria() {
		ModelAndView modelAndView = new ModelAndView("formMateria");
		modelAndView.addObject("nuevaMateria", materia);
		modelAndView.addObject("estado", false);
		return modelAndView;
	}

	@PostMapping("/guardarMateria")
	public ModelAndView guardarMateria(@ModelAttribute("nuevaMateria") Materia materiaNuevaParaGuardar) {
		materiaService.guardarMateria(materiaNuevaParaGuardar);
		ModelAndView modelAndView = new ModelAndView("listaDeMaterias");
		modelAndView.addObject("listadoMaterias", materiaService.mostrarMaterias());
		return modelAndView;
	}
	
	  @GetMapping ("/eliminarMateria/{codigo}") 
	  public ModelAndView borrarMateria(@PathVariable (name="codigo") String codigo) {
		  materiaService.eliminarMateria(codigo); 
		  ModelAndView modelAndView = new ModelAndView("listaDeMaterias"); 
		  modelAndView.addObject("listadoMaterias", materiaService.mostrarMaterias()); 
		  return modelAndView; 
	  }
	  
	  @GetMapping("/modificarMateria/{codigo}") 
	  public ModelAndView getFormModificarMateria(@PathVariable(name="codigo") String codigo) { 
		  System.out.println("codigo que viene: " + codigo);
		  Materia materiaAModificar = materiaService.buscarMateriaPorCodigo(codigo);
		  System.out.println("codigo que vuelve: " + materiaAModificar.getCodigo());
		  ModelAndView modelAndView = new ModelAndView("formMateria");
		  modelAndView.addObject("nuevaMateria", materiaAModificar);
		  modelAndView.addObject("estado", true); 
		  return modelAndView; 
	  }
	  
	  @PostMapping("/modificarMateria") 
	  public ModelAndView modificarMateria(@ModelAttribute("nuevaMateria") Materia materiaaAModificar) { 
		  materiaService.modificarMateria(materiaaAModificar); 
		  ModelAndView modelAndView = new ModelAndView("listaDeMaterias");
		  modelAndView.addObject("listadoMaterias", materiaService.mostrarMaterias());
	  return modelAndView;
	  
	  }

}
