package ar.edu.unju.fi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.model.Materia;

public interface MateriaRepository extends JpaRepository<Materia, String> {
	List<Materia> findMateriaByEstado(Boolean estado);
	
	
	
	  //@Query("SELECT m FROM Materia m WHERE m.carrera = :carrera")
	  //  List<Materia> findMateriaByCarrera(Carrera carrera);
	  
	  List<Materia> findMateriaByCarrera(Carrera carrera);
	  
	  @Query("SELECT a FROM Alumno a WHERE a.carrera.codigo = :codigo")
	    List<Alumno> findAlumnosByCodigo(@Param("codigo") String codigo);

}

