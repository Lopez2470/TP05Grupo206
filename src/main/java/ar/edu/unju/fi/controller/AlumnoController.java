package ar.edu.unju.fi.controller;

import java.time.LocalDate;
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
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.service.IAlumnoService;
import ar.edu.unju.fi.service.ICarreraService;
import ar.edu.unju.fi.service.IMateriaService;
import jakarta.validation.Valid;

@Controller
public class AlumnoController {
	private static final Log LOGGER = LogFactory.getLog(AlumnoController.class);
	// Inyeccion de dependencia
	@Autowired
	Alumno alumno;
	@Autowired
	Materia materia;
	@Autowired
	IAlumnoService alumnoService;
	@Autowired
	ICarreraService carreraService;
	@Autowired
	IMateriaService materiaService;

	//Alumno alumnoSeleccionado;
	@GetMapping("/listaAlumno")
	public ModelAndView getAlumnos() {
		ModelAndView modelAndView = new ModelAndView("listaDeAlumnos");
		modelAndView.addObject("listadoAlumnos", alumnoService.mostrarAlumnos());
		//modelAndView.addObject("listadoCarreras", carreraService.mostrarCarreras());
		return modelAndView;
	}

	@GetMapping("/formularioAlumno")
	public ModelAndView getFormAlumno() {
		ModelAndView modelAndView = new ModelAndView("formAlumno");
		modelAndView.addObject("nuevoAlumno", alumno);
		modelAndView.addObject("listadoCarreras", carreraService.mostrarCarreras());
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
			alumnoNuevoParaGuardar.setCarrera(carreraService.buscarCarreraPorCodigo(alumnoNuevoParaGuardar.getCarrera().getCodigo()));
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
		modelAndView.addObject("listadoCarreras", carreraService.mostrarCarreras());
		modelAndView.addObject("estado", true);
		return modelAndView;
	}

	@PostMapping("/modificarAlumno")
	public ModelAndView modificarAlumno(@Valid @ModelAttribute("nuevoAlumno") Alumno alumnoAModificar,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			LOGGER.info("Ocurrió un Error " + alumnoAModificar);
			ModelAndView modelAndView = new ModelAndView("formAlumno");
			modelAndView.addObject("listadoCarreras", carreraService.mostrarCarreras());
			return modelAndView;
		}

		ModelAndView modelAndView = new ModelAndView("redirect:/listaAlumno");
		try {
			alumnoAModificar.setCarrera(carreraService.buscarCarreraPorCodigo(alumnoAModificar.getCarrera().getCodigo()));
			alumnoService.modificarAlumno(alumnoAModificar);
			LOGGER.info("Se modificó un objeto Alumno en la Base de Datos");
		} catch (Exception e) {
			boolean errors = true;
			modelAndView.addObject("errors", errors);
			modelAndView.addObject("cargaDeAlumnoErrorMsj", "Error al modificar el objeto Alumno");
		}
		return modelAndView;
	}
	
	
	/*Inscripcion**/
	@GetMapping("/seleccionAlumno")
	public ModelAndView getFormSeleccionAlumno() {
		ModelAndView modelAndView = new ModelAndView("formSeleccionAlumno");
		modelAndView.addObject("alumnos", alumnoService.mostrarAlumnos());
		modelAndView.addObject("alumno", alumno);
		return modelAndView;
	}
	
	@PostMapping("/asignarMaterias")
	public ModelAndView getFormAsignarMaterias(@ModelAttribute("alumno") Alumno alumnoSelec) {
		ModelAndView modelAndView = new ModelAndView("formAsignarMaterias");
		System.out.println("alumnoSselleccionado: " + alumnoSelec.getLu());
		String luAlumno = alumnoSelec.getLu();
		System.out.println("lu encontrado: " + luAlumno);
		Alumno alumnoInscribir = alumnoService.buscarAlumnoPorLu(luAlumno);
		System.out.println("alumno encontrado: " + alumnoInscribir);
		String codigoC= alumnoInscribir.getCarrera().getCodigo();
		System.out.println("codigo carrera " + codigoC);
		Carrera carrera = carreraService.buscarCarreraPorCodigo(codigoC);
		System.out.println("carrera: " + carrera);
		//List<MateriaDTO> materias = materiaService.mostrarMaterias();
		
		//List<MateriaDTO> materias = (List<MateriaDTO>) materiaService.buscarMateriaPorCarrera(carrera);
		modelAndView.addObject("alumnoSeleccionado", alumno);
		//modelAndView.addObject("materias", materias);
		return modelAndView;
	}
	
	
	
	
	/*Filtrar*/
	
	
	  @GetMapping("/formularioMateriaAlumno")
		public ModelAndView getFormulario() {
		ModelAndView modelAndView = new ModelAndView("formMateriaAlumnos");
		modelAndView.addObject("materia", materia);
		modelAndView.addObject("materias", materiaService.mostrarMaterias());
		//System.out.println("carreras: "+ carreraService.mostrarCarreras());
		return modelAndView;
	}
	  
	  
	
	@PostMapping("/filtrarAlumnosPorMateria")
	public ModelAndView filtrarAlumnos(@ModelAttribute("materia") Materia materiaDeAlumnos) {

	
		System.out.println("MATERIA QUE VIENE: " + materiaDeAlumnos);
	  String codigoC = materiaDeAlumnos.getCodigo();
	  System.out.println("VALOR DE codigoC: " +codigoC);

		List<Alumno> alumnos = alumnoService.buscarAlumnosPorMateria(materiaService.buscarMateriaPorCodigo(codigoC));
	  
	  ModelAndView modelAndView = new ModelAndView("formMateriaAlumnos");
	 modelAndView.addObject("alumnos", alumnos);

	  return modelAndView;  
}

	
}