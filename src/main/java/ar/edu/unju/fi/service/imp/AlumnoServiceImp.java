package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.repository.AlumnoRepository;
import ar.edu.unju.fi.service.IAlumnoService;
@Service
public class AlumnoServiceImp implements IAlumnoService {
	@Autowired
	AlumnoRepository alumnoRepository;

	@Override
	public void guardarAlumno(Alumno alumno) {
		alumno.setEstado(true);
		alumnoRepository.save(alumno);
	}

	@Override
	public List<Alumno> mostrarAlumnos() {
		return alumnoRepository.findAlumnoByEstado(true);
	}

	@Override
	public void eliminarAlumno(String lu) {
		Alumno alumnoABorrar = buscarAlumnoPorLu(lu);
		alumnoABorrar.setEstado(false);
		alumnoRepository.save(alumnoABorrar);

	}

	@Override
	public void modificarAlumno(Alumno alumnoModificado) {
		alumnoModificado.setEstado(true);
		alumnoRepository.save(alumnoModificado);
	}

	@Override
	public Alumno buscarAlumnoPorLu(String lu) {
		return alumnoRepository.findById(lu).orElse(null);
	}

}