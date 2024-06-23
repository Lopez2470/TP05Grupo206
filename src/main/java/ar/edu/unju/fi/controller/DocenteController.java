package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.DTO.DocenteDTO;
//import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.service.IDocenteService;


@Controller

public class DocenteController {

	//Inyeccion de dependencia
	//@Autowired
	//Docente docente;
	@Autowired
	IDocenteService docenteService;
	
	/*DTO*/
	@Autowired
	DocenteDTO docenteDTO;
	
	@GetMapping("/formularioDocente")
	public ModelAndView getFormDocente() {
		ModelAndView modelAndView = new ModelAndView("formDocente");
		modelAndView.addObject("nuevoDocente", docenteDTO);
		modelAndView.addObject("estado", false);
		return modelAndView;
	}
	
	@PostMapping("/guardarDocente") public ModelAndView
	  guardarDocente(@ModelAttribute("nuevoDocente") DocenteDTO docenteDTONuevoParaGuardar) { 
	  docenteService.guardarDocenteDTO(docenteDTONuevoParaGuardar);
	  ModelAndView modelAndView = new ModelAndView("listaDeDocentes");
	  modelAndView.addObject("listadoDocentes",docenteService.mostrarDocentesDTO()); 
	  return modelAndView; 
	  }
	
	@GetMapping ("/eliminarDocente/{legajo}") 
	  public ModelAndView borrarDocente(@PathVariable (name="legajo") String legajo) { 
		  docenteService.eliminarDocenteDTO(legajo);
		  ModelAndView modelAndView = new ModelAndView("listaDeDocentes");
		  modelAndView.addObject("listadoDocentes", docenteService.mostrarDocentesDTO()); 
		  return modelAndView;
	  }
	
	@GetMapping("/modificarDocente/{legajo}") 
	  public ModelAndView getFormModificarDocente(@PathVariable(name="legajo") String legajo) { 
		  DocenteDTO docenteDTOAModificar = docenteService.buscarDocenteDTOPorLegajo(legajo); 
		  ModelAndView modelAndView = new ModelAndView("formDocente");
		  modelAndView.addObject("nuevoDocente", docenteDTOAModificar);
		  modelAndView.addObject("estado", true); 
		  return modelAndView; 
	  };
	  
	  @PostMapping("/modificarDocente") 
	  public ModelAndView modificarDocente(@ModelAttribute("nuevoDocente") DocenteDTO docenteDTOaAModificar) { 
		  docenteService.modificarDocenteDTO(docenteDTOaAModificar); 
		  ModelAndView modelAndView = new ModelAndView("listaDeDocentes");
		  modelAndView.addObject("listadoDocentes", docenteService.mostrarDocentesDTO()); 
	  return modelAndView;
	  
	  }
	
	/*
	@GetMapping("/formularioDocente")
	public ModelAndView getFormDocente() {
		ModelAndView modelAndView = new ModelAndView("formDocente");
		modelAndView.addObject("nuevoDocente", docente);
		modelAndView.addObject("estado", false);
		return modelAndView;
	}
	
	
	  @PostMapping("/guardarDocente") public ModelAndView
	  guardarDocente(@ModelAttribute("nuevoDocente") Docente docenteNuevoParaGuardar) { 
	  docenteService.guardarDocente(docenteNuevoParaGuardar);
	  ModelAndView modelAndView = new ModelAndView("listaDeDocentes");
	  modelAndView.addObject("listadoDocentes",docenteService.mostrarDocentes()); 
	  return modelAndView; }
	  

	  @GetMapping ("/eliminarDocente/{legajo}") 
	  public ModelAndView borrarDocente(@PathVariable (name="legajo") String legajo) { 
		  docenteService.eliminarDocente(legajo);
		  ModelAndView modelAndView = new ModelAndView("listaDeDocentes");
		  modelAndView.addObject("listadoDocentes", docenteService.mostrarDocentes()); 
		  return modelAndView;
	  }

	  @GetMapping("/modificarDocente/{legajo}") 
	  public ModelAndView getFormModificarDocente(@PathVariable(name="legajo") String legajo) { 
		  Docente docenteAModificar = docenteService.buscarDocentePorLegajo(legajo); 
		  ModelAndView modelAndView = new ModelAndView("formDocente");
		  modelAndView.addObject("nuevoDocente", docenteAModificar);
		  modelAndView.addObject("estado", true); 
		  return modelAndView; 
	  };
	  
	  @PostMapping("/modificarDocente") 
	  public ModelAndView modificarDocente(@ModelAttribute("nuevoDocente") Docente docenteaAModificar) { 
		  docenteService.modificarDocente(docenteaAModificar); 
		  ModelAndView modelAndView = new ModelAndView("listaDeDocentes");
		  modelAndView.addObject("listadoDocentes", docenteService.mostrarDocentes()); 
	  return modelAndView;
	  
	  }
	  */
}