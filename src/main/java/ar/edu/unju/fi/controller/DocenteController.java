package ar.edu.unju.fi.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ar.edu.unju.fi.model.Docente;
//import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.service.IDocenteService;
import jakarta.validation.Valid;


@Controller

public class DocenteController {
	private static final Log LOGGER = LogFactory.getLog(AlumnoController.class);

	//Inyeccion de dependencia
	@Autowired
	Docente docente;
	@Autowired
	IDocenteService docenteService;

	@GetMapping("/listaDocente")
	public ModelAndView getDocente() {
		ModelAndView modelAndView = new ModelAndView("listaDeDocentes");
		modelAndView.addObject("listadoDocentes", docenteService.mostrarDocentes());
		return modelAndView;
	}

	@GetMapping("/formularioDocente")
	public ModelAndView getFormDocente() {
		ModelAndView modelAndView = new ModelAndView("formDocente");
		modelAndView.addObject("nuevoDocente", docente);
		modelAndView.addObject("estado", false);
		return modelAndView;
	}
	
	
	  @PostMapping("/guardarDocente") 
	  public ModelAndView guardarDocente(@Valid @ModelAttribute("nuevoDocente") Docente docenteNuevoParaGuardar,
				BindingResult bindingResult) { 
		  if (bindingResult.hasErrors()) {
				LOGGER.info("Ocurrió un Error: formulariuo Incompleto " + docenteNuevoParaGuardar);
				ModelAndView modelAndView = new ModelAndView("formDocente");
				return modelAndView;
		  }
		  ModelAndView modelAndView = new ModelAndView("redirect:/listaDocente");
		  
		  try {
			  docenteService.guardarDocente(docenteNuevoParaGuardar);
			  LOGGER.info("Se agregó un objeto Docente en la Base de Datos");
			} catch (Exception e) {
				boolean unsave = true;
				modelAndView.addObject("unsave", unsave);
				modelAndView.addObject("cargaDeDocenteErrorMsj", "Error al cargar el nuevo objeto Docente");
			}
		  //ModelAndView modelAndView = new ModelAndView("listaDeDocentes");
		  // modelAndView.addObject("listadoDocentes",docenteService.mostrarDocentes()); 
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
	  public ModelAndView modificarDocente(@Valid @ModelAttribute("nuevoDocente") Docente docenteaAModificar,
				BindingResult bindingResult) { 
		  if (bindingResult.hasErrors()) {
				LOGGER.info("Ocurrió un Error: formulariuo Incompleto " + docenteaAModificar);
				ModelAndView modelAndView = new ModelAndView("formDocente");
				//modelAndView.addObject("nuevoDocente", docente);
				return modelAndView;
			}
		  ModelAndView modelAndView = new ModelAndView("redirect:/listaDocente");
		  try {
			  docenteService.modificarDocente(docenteaAModificar); 
			  LOGGER.info("Se modificó un objeto Docente en la Base de Datos");
			} catch (Exception e) {
				boolean unsave = true;
				modelAndView.addObject("unsave", unsave);
				modelAndView.addObject("cargaDeDocenteErrorMsj", "Error al modificar el objeto Docente");
			}
		  //ModelAndView modelAndView = new ModelAndView("listaDeDocentes");
		  // modelAndView.addObject("listadoDocentes", docenteService.mostrarDocentes()); 
		  return modelAndView;
	  }
}
