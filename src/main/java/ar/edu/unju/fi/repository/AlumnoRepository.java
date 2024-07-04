package ar.edu.unju.fi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.model.Materia;

public interface AlumnoRepository extends JpaRepository<Alumno, String> {
	List<Alumno> findAlumnoByEstado(Boolean estado);
	
    List<Alumno> findByMateriasContaining(Materia materia);

}
