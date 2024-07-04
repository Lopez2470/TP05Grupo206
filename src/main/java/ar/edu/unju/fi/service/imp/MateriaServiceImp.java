package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.DTO.MateriaDTO;
import ar.edu.unju.fi.map.MateriaMapDTO;
import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.repository.MateriaRepository;
import ar.edu.unju.fi.service.IMateriaService;
@Service
public class MateriaServiceImp implements IMateriaService{
	@Autowired
	MateriaRepository materiaRepository;
	
	@Autowired
	MateriaMapDTO materiaMapDTO;

	@Override
	public void guardarMateria(Materia materia) {
		materia.setEstado(true);
		materiaRepository.save(materia);	
	}

	@Override
	public List<MateriaDTO> mostrarMaterias() {
		return materiaMapDTO.convertirListaMateriasAListaMateriasDTO(materiaRepository.findMateriaByEstado(true));
	}

	@Override
	public void eliminarMateria(String codigo) {
		Materia materiaABorrar=buscarMateriaPorCodigo(codigo);
		materiaABorrar.setEstado(false);
		materiaRepository.save(materiaABorrar);		
	}

	@Override
	public void modificarMateria(Materia materiaModificada) {
		materiaModificada.setEstado(true);
		materiaRepository.save(materiaModificada);	
	}

	@Override
	public Materia buscarMateriaPorCodigo(String codigo) {
		return materiaRepository.findById(codigo).orElse(null);
	}

	@Override
	public Materia buscarMateriaPorCarrera(Carrera carrera) {
		// TODO Auto-generated method stub
		return (Materia) materiaRepository.findMateriaByCarrera(carrera);
	}


	@Override
	public List<Alumno> obtenerAlumnosPorMateria(String codigo) {
		// TODO Auto-generated method stub
		return materiaRepository.findAlumnosByCodigo(codigo);
	}
	
}
