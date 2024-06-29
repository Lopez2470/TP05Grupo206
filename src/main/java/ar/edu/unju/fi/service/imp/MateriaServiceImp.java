package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.repository.MateriaRepository;
import ar.edu.unju.fi.service.IMateriaService;
@Service
public class MateriaServiceImp implements IMateriaService{
	@Autowired
	MateriaRepository materiaRepository;

	@Override
	public void guardarMateria(Materia materia) {
		materia.setEstado(true);
		materiaRepository.save(materia);	
	}

	@Override
	public List<Materia> mostrarMaterias() {
		return materiaRepository.findMateriaByEstado(true);
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


	
	
}