package ar.edu.unju.fi.controller;

import java.util.List;

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

import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.repository.CarreraRepository;
import ar.edu.unju.fi.service.IAlumnoService;
import ar.edu.unju.fi.service.ICarreraService;
import jakarta.validation.Valid;

@Controller
public class CarreraController {
	private static final Log LOGGER = LogFactory.getLog(AlumnoController.class);
	// Inyeccion de dependencia
	@Autowired
	Carrera carrera;
	@Autowired
	ICarreraService carreraService;
	@Autowired
	IAlumnoService alumnoService;
	//@Autowired
	//Alumno alumno;
	
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
	public ModelAndView guardarCarrera(@Valid @ModelAttribute("nuevaCarrera") Carrera carreraNuevaParaGuardar,
			BindingResult bindingResult) { 
		if (bindingResult.hasErrors()) {
				LOGGER.info("Ocurrió un Error: formulariuo Incompleto " + carreraNuevaParaGuardar);
				ModelAndView modelAndView = new ModelAndView("formCarrera");
				return modelAndView;
		  }
		  ModelAndView modelAndView = new ModelAndView("redirect:/listaCarrera");
		  
		  try {
			  carreraService.guardarCarrera(carreraNuevaParaGuardar);
			  LOGGER.info("Se agregó un objeto Carrera en la Base de Datos");
			} catch (Exception e) {
				boolean unsave = true;
				modelAndView.addObject("unsave", unsave);
				modelAndView.addObject("cargaDeCarreraErrorMsj", "Error al cargar el nuevo objeto Carrera");
			}
			//ModelAndView modelAndView = new ModelAndView("listaDeCarreras");
			//modelAndView.addObject("listadoCarreras", carreraService.mostrarCarreras());
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
	  public ModelAndView modificarCarrera(@Valid @ModelAttribute("nuevaCarrera") Carrera carreraAModificar,
		BindingResult bindingResult) { 
			if (bindingResult.hasErrors()) {
				LOGGER.info("Ocurrió un Error: formulariuo Incompleto " + carreraAModificar);
				ModelAndView modelAndView = new ModelAndView("formCarrera");
				return modelAndView;
			}
			ModelAndView modelAndView = new ModelAndView("redirect:/listaCarrera");
		  try {
			  carreraService.modificarCarrera(carreraAModificar); 
			  LOGGER.info("Se modificó un objeto Carrera en la Base de Datos");
			} catch (Exception e) {
				boolean unsave = true;
				modelAndView.addObject("unsave", unsave);
				modelAndView.addObject("cargaDeCarreraErrorMsj", "Error al modificar el objeto Carrera");
			}

		 // ModelAndView modelAndView = new ModelAndView("listaDeCarreras");
		 // modelAndView.addObject("listadoCarreras", carreraService.mostrarCarreras());
		return modelAndView;
	  }
	  
	  /*
	  @GetMapping("/formularioCarreraAlumno")
		public ModelAndView getFormAlumnosPorCarrera() {
			ModelAndView modelAndView = new ModelAndView("formCarreraAlumno");
			modelAndView.addObject("listadoCarreras", carreraService.mostrarCarreras());
			return modelAndView;
		}
	  
	  @PostMapping("/formularioCarreraAlumno}") 
	  public ModelAndView getFormAlumnosCarrera(@PathVariable(name="codigo") String codigo) { 	
		  System.out.println("Carrera codigo: " + codigo);
		  Carrera carrera = carreraService.buscarCarreraPorCodigo(codigo);
		  List<Alumno> alumnos = carreraService.obtenerAlumnosPorCarrera(carrera);
		  System.out.println("Alumos: " + alumnos);
	
		  Carrera carreraAMostrar = carreraService.buscarCarreraPorCodigo(codigo);
		  
		  ModelAndView modelAndView = new ModelAndView("formCarreraAlumno");
		  //List<Alumno> alumnos = carreraService.obtenerAlumnosPorCarrera(codigo);
		  modelAndView.addObject("carrera", carreraAMostrar);
		  modelAndView.addObject("alumnos", alumnos); 
		  System.out.println("Carrera: " + carreraAMostrar.getCodigo());
		  return modelAndView; 
	  }
*/
	  
	  @GetMapping("/formularioCarreraAlumno")
	  public ModelAndView getFormulario() {
			ModelAndView modelAndView = new ModelAndView("formCarreraAlumno");
			modelAndView.addObject("carrera", carrera);
			modelAndView.addObject("carreras", carreraService.mostrarCarreras());
			System.out.println("carreras: "+ carreraService.mostrarCarreras());
			return modelAndView;
		}
	  @PostMapping("/mostrarAlumnosPorCarrera")
	  public ModelAndView obtenerAlumnos(@ModelAttribute("carrera") Carrera carreraDeAlumnos) {
		  String codigoC = carreraDeAlumnos.getCodigo();
		  List<Alumno> alumnos = carreraService.obtenerAlumnosPorCarrera(codigoC);
		  ModelAndView modelAndView = new ModelAndView("formCarreraAlumno");
		  modelAndView.addObject("alumnos", alumnos);
		  return modelAndView;
		  
	  }
	  
	  
	  

}