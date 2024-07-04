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
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.service.ICarreraService;
import ar.edu.unju.fi.service.IDocenteService;
import ar.edu.unju.fi.service.IMateriaService;
import jakarta.validation.Valid;

@Controller
public class MateriaController {
	private static final Log LOGGER = LogFactory.getLog(AlumnoController.class);

	// Inyeccion de dependencia

	@Autowired
	Materia materia;

	@Autowired
	IMateriaService materiaService;
	
	@Autowired
	IDocenteService docenteService;
	
	@Autowired
	ICarreraService carreraService;

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
		modelAndView.addObject("listadoDocentes", docenteService.mostrarDocentes());
		modelAndView.addObject("listadoCarreras", carreraService.mostrarCarreras());
		modelAndView.addObject("estado", false);
		return modelAndView;
	}

	@PostMapping("/guardarMateria")
	public ModelAndView guardarMateria(@Valid @ModelAttribute("nuevaMateria") Materia materiaNuevaParaGuardar,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			LOGGER.info("Ocurrió un Error: formulariuo Incompleto " + materiaNuevaParaGuardar);
			ModelAndView modelAndView = new ModelAndView("formMateria");
			return modelAndView;
		}
		
		ModelAndView modelAndView = new ModelAndView("redirect:/listaMateria");
		
		try {
			materiaNuevaParaGuardar.setDocente(docenteService.buscarDocentePorLegajo(materiaNuevaParaGuardar.getDocente().getLegajo()));
			materiaNuevaParaGuardar.setCarrera(carreraService.buscarCarreraPorCodigo(materiaNuevaParaGuardar.getCarrera().getCodigo()));
			materiaService.guardarMateria(materiaNuevaParaGuardar);
			LOGGER.info("Se agregó un objeto Materia en la Base de Datos");
		} catch (Exception e) {
			boolean unsave = true;
			modelAndView.addObject("unsave", unsave);
			modelAndView.addObject("cargaDeMateriaErrorMsj", "Error al cargar el nuevo objeto Materia");
			
		}
		// ModelAndView modelAndView = new ModelAndView("listaDeMaterias");
		// modelAndView.addObject("listadoMaterias", materiaService.mostrarMaterias());
		return modelAndView;
	}

	@GetMapping("/eliminarMateria/{codigo}")
	public ModelAndView borrarMateria(@PathVariable(name = "codigo") String codigo) {
		materiaService.eliminarMateria(codigo);
		ModelAndView modelAndView = new ModelAndView("listaDeMaterias");
		modelAndView.addObject("listadoMaterias", materiaService.mostrarMaterias());
		return modelAndView;
	}

	@GetMapping("/modificarMateria/{codigo}")
	public ModelAndView getFormModificarMateria(@PathVariable(name = "codigo") String codigo) {
		Materia materiaAModificar = materiaService.buscarMateriaPorCodigo(codigo);
		
		ModelAndView modelAndView = new ModelAndView("formMateria");
		modelAndView.addObject("nuevaMateria", materiaAModificar);
		modelAndView.addObject("estado", true);
		
		modelAndView.addObject("listadoDocentes", docenteService.mostrarDocentes());
		modelAndView.addObject("listadoCarreras", carreraService.mostrarCarreras());
		
		
		return modelAndView;
	}

	@PostMapping("/modificarMateria")
	public ModelAndView modificarMateria(@Valid @ModelAttribute("nuevaMateria") Materia materiaAAModificar,
			BindingResult bindingResult) { 
		if (bindingResult.hasErrors()) {
			LOGGER.info("Ocurrió un Error: formulariuo Incompleto " + materiaAAModificar);
			ModelAndView modelAndView = new ModelAndView("formMateria");
			
			modelAndView.addObject("nuevaMateria", materiaAAModificar);
			
			
			return modelAndView;
		}
		ModelAndView modelAndView = new ModelAndView("redirect:/listaMateria");
		
		try {
			materiaAAModificar.setDocente(docenteService.buscarDocentePorLegajo(materiaAAModificar.getDocente().getLegajo()));
			materiaAAModificar.setCarrera(carreraService.buscarCarreraPorCodigo(materiaAAModificar.getCarrera().getCodigo()));
			  materiaService.modificarMateria(materiaAAModificar);
			  LOGGER.info("Se modificó un objeto Materia en la Base de Datos");
			} catch (Exception e) {
				boolean unsave = true;
				modelAndView.addObject("unsave", unsave);
				modelAndView.addObject("cargaDeMateriaErrorMsj", "Error al modificar el objeto Materia");
			}
		
		//ModelAndView modelAndView = new ModelAndView("listaDeMaterias");
		//modelAndView.addObject("listadoMaterias", materiaService.mostrarMaterias());
		return modelAndView;

	}
	

}