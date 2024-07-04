package ar.edu.unju.fi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.fi.DTO.AlumnoDTO;
import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.model.Materia;


@Service
public interface IAlumnoService {
	public void guardarAlumno(Alumno alumno);
	public List<AlumnoDTO> mostrarAlumnos();
	public void eliminarAlumno(String lu);
	public void modificarAlumno(Alumno alumno);
	public Alumno buscarAlumnoPorLu(String lu);
	
	public List<Alumno> buscarAlumnosPorMateria(Materia materia);


}
