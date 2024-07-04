package ar.edu.unju.fi.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Materia {
	@Id
	@NotBlank(message="Debe ingresar el codigo de Materia")
	private String codigo;
	
	@NotBlank(message="Debe ingresar nombre de la materia")
	@Size(min=3, max=20, message="El nombre debe contener como mínimo 3 caracteres y como máximo 20 caracteres")
	private String nombre;
	
	@Min(value=1, message="El curso debe ser entre 1 a 6")
	@Max(value=6, message="El curso debe ser entre 1 a 6")
	private String curso;
	
	@Min(value=1, message="La cantidad de horas de una materia esta comprendida entre 1 y 3")
	@Max(value=3, message="La cantidad de horas de una materia esta comprendida entre 1 y 3")
	private Integer horas;
	
	@NotBlank(message="Debe seleccionar una modalidad")
	private String modalidad;
	
	@NotNull
	@OneToOne
	@JoinColumn(name = "legajo")
	private Docente docente; 
	
	@NotNull
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="codigoCar")
	private Carrera carrera;
	
	private Boolean estado;
}
