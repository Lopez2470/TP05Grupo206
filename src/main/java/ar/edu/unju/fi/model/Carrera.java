package ar.edu.unju.fi.model;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Carrera {
	@Id
	@NotBlank(message="Debe ingresar el codigo de carrera")
	private String codigo;
	
	@NotBlank(message="Debe ingresar nombre de la carrera")
	@Size(min=3, max=20, message="El nombre debe contener como mínimo 3 caracteres y como máximo 20 caracteres")
	@Pattern(regexp= "[a-z A-Z]*")
	private String nombre;
	
	@Min(value=3, message="La duracion de las carreras esta entre 3 a 6 años")
	@Max(value=6, message="La duracion de las carreras esta entre 3 a 6 años")
	//@NotEmpty(message="Debe ingresar el codigo de carrera")
	private Integer duracion;

	@NotNull
	@OneToMany(mappedBy="carrera", cascade=CascadeType.ALL)
	private List<Materia> materias = new ArrayList<Materia>();
	
	@OneToMany(mappedBy = "carrera", cascade = CascadeType.ALL)
    private List<Alumno> alumnos = new ArrayList<Alumno>();
	
	private Boolean estado;
}
