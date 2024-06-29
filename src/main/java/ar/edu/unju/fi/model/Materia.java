package ar.edu.unju.fi.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Component
@Data
@Entity
public class Materia {
	@Id
	private String codigo;
	private String nombre;
	private String curso;
	private Integer horas;
	private String modalidad;
	/*
	  private Docente docente; 
	  private Carrera carrera;
	*/ 
	private Boolean estado;

}
