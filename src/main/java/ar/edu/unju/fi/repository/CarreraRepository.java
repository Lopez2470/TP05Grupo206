package ar.edu.unju.fi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.model.Carrera;

public interface CarreraRepository extends JpaRepository<Carrera, String> {
	List <Carrera> findCarreraByEstado(Boolean estado);
	
	List<Alumno> findALumnosByCodigo(String codigo);
	
	
	
	//  @Query("SELECT a FROM Alumno a WHERE a.carrera = :carrera")
	 //   List<Alumno> findAlumnosByCarrera(Carrera carrera);
	  
	  
	  @Query("SELECT a FROM Alumno a WHERE a.carrera.codigo = :codigo")
	    List<Alumno> findAlumnosByCodigo(@Param("codigo") String codigo);
	  
	  Carrera findByAlumnosContaining(Alumno alumno);
	  
	  


}
