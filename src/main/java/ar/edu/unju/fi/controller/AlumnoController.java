package ar.edu.unju.fi.controller;

import java.time.LocalDate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.service.IAlumnoService;
import jakarta.validation.Valid;

@Controller
public class AlumnoController {
	private static final Log LOGGER = LogFactory.getLog(AlumnoController.class);
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
	public ModelAndView guardarAlumno(@Valid @ModelAttribute("nuevoAlumno") Alumno alumnoNuevoParaGuardar,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			LOGGER.info("Ocurrió un Error " + alumnoNuevoParaGuardar);
			ModelAndView modelAndView = new ModelAndView("formAlumno");
			return modelAndView;
		}
		ModelAndView modelAndView = new ModelAndView("redirect:/listaAlumno");
		try {
			alumnoService.guardarAlumno(alumnoNuevoParaGuardar);
			LOGGER.info("Se agregó un objeto Alumno a la Base de Datos");
		} catch (Exception e) {
			boolean unsave = true;
			modelAndView.addObject("unsave", unsave);
			modelAndView.addObject("cargaDeAlumnoErrorMsj", "Error al cargar el nuevo objeto Alumno");
		}
		return modelAndView;
	}

	@GetMapping("/eliminarAlumno/{lu}")
	public ModelAndView borrarAlumno(@PathVariable(name = "lu") String lu) {
		alumnoService.eliminarAlumno(lu);
		ModelAndView modelAndView = new ModelAndView("redirect:/listaAlumno");
		//ModelAndView modelAndView = new ModelAndView("listaDeAlumnos");
		//modelAndView.addObject("listadoAlumnos", alumnoService.mostrarAlumnos());
		return modelAndView;
	}

	@GetMapping("/modificarAlumno/{lu}")
	public ModelAndView getFormModificarAlumno(@PathVariable(name = "lu") String lu) {
		Alumno alumnoAModificar = alumnoService.buscarAlumnoPorLu(lu);
		ModelAndView modelAndView = new ModelAndView("formAlumno");
		modelAndView.addObject("nuevoAlumno", alumnoAModificar);
		modelAndView.addObject("estado", true);
		return modelAndView;
	}

	@PostMapping("/modificarAlumno")
	public ModelAndView modificarAlumno(@Valid @ModelAttribute("nuevoAlumno") Alumno alumnoAModificar,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			LOGGER.info("Ocurrió un Error " + alumnoAModificar);
			ModelAndView modelAndView = new ModelAndView("formAlumno");
			return modelAndView;
		}

		ModelAndView modelAndView = new ModelAndView("redirect:/listaAlumno");
		try {
			alumnoService.modificarAlumno(alumnoAModificar);
			LOGGER.info("Se modificó un objeto Alumno en la Base de Datos");
		} catch (Exception e) {
			boolean unsave = true;
			modelAndView.addObject("unsave", unsave);
			modelAndView.addObject("cargaDeAlumnoErrorMsj", "Error al modificar el objeto Alumno");
		}
		return modelAndView;
	}
}