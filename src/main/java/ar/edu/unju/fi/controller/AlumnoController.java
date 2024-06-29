package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.service.IAlumnoService;

@Controller
public class AlumnoController {
	// Inyeccion de dependencia
		@Autowired
		Alumno alumno;
		@Autowired
		IAlumnoService alumnoService;
		
		@GetMapping("/listaAlumno")
		public ModelAndView getAlumnos() {
			ModelAndView modelAndView = new ModelAndView("listaDeAlumnos");
			modelAndView.addObject("listadoAlumnos", alumnoService.mostrarAlumnos());
			return modelAndView;
		}
		
		@GetMapping("/formularioAlumno")
		public ModelAndView getFormAlumno() {
			ModelAndView modelAndView = new ModelAndView("formAlumno");
			modelAndView.addObject("nuevoAlumno", alumno);
			modelAndView.addObject("estado", false);
			return modelAndView;
		}
		
		@PostMapping("/guardarAlumno")
		public ModelAndView guardarAlumno(@ModelAttribute("nuevoAlumno") Alumno alumnoNuevoParaGuardar) {
			alumnoService.guardarAlumno(alumnoNuevoParaGuardar);
			ModelAndView modelAndView = new ModelAndView("listaDeAlumnos");
			modelAndView.addObject("listadoAlumnos", alumnoService.mostrarAlumnos());
			return modelAndView;
		}
		
		  @GetMapping ("/eliminarAlumno/{lu}") 
		  public ModelAndView borrarAlumno(@PathVariable (name="lu") String lu) {
			  alumnoService.eliminarAlumno(lu); 
			  ModelAndView modelAndView = new ModelAndView("listaDeAlumnos"); 
			  modelAndView.addObject("listadoAlumnos", alumnoService.mostrarAlumnos()); 
			  return modelAndView; 
		  }
		  
		  @GetMapping("/modificarAlumno/{lu}") 
		  public ModelAndView getFormModificarAlumno(@PathVariable(name="lu") String lu) { 
			  Alumno alumnoAModificar = alumnoService.buscarAlumnoPorLu(lu);
			  ModelAndView modelAndView = new ModelAndView("formAlumno");
			  
			  modelAndView.addObject("nuevoAlumno", alumnoAModificar);
			  modelAndView.addObject("estado", true); 
			  return modelAndView; 
		  }
		  
		  @PostMapping("/modificarAlumno") 
		  public ModelAndView modificarAlumno(@ModelAttribute("nuevoAlumno") Alumno alumnoAModificar) { 
			  alumnoService.modificarAlumno(alumnoAModificar); 
			  ModelAndView modelAndView = new ModelAndView("listaDeAlumnos");
			  modelAndView.addObject("listadoAlumnos", alumnoService.mostrarAlumnos());
		  return modelAndView;
		  
		  }

}