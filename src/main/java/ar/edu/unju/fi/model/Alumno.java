package ar.edu.unju.fi.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Component
@Data
@Entity
public class Alumno {
	@Size(min=8,max=8, message="El DNI debe contener 8 dígitos")
	@Pattern(regexp="[0-9]+")
	private String dni;
	
	@NotBlank(message="Debe ingresar nombre del alumno")
	@Size(min=3, max=20, message="El nombre debe contener como mínimo 3 caracteres y como máximo 20 caracteres")
	@Pattern(regexp= "[a-z A-Z]*")
	private String nombres;
	
	@NotBlank(message="Debe ingresar apellido del alumno")
	@Size(min=3, max=20, message="El apellido debe contener como mínimo 3 caracteres y como máximo 20 caracteres")
	@Pattern(regexp= "[a-z A-Z]*")
	private String apellido;
	
	@NotBlank(message = "Ingrese un nro telefonico")
	@Email
	private String email;
	
	@NotBlank(message = "Ingrese un nro telefonico")
	//@Size(max=11)
	@Pattern(regexp="[0-9]{3}+-+[0-9]{7}")
	//@Pattern(regexp = "[0-9]{10}")
	private String telefono;
	
	@Past (message="La fecha de nacimiento debe ser anterior a la fecha actual")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@NotNull(message = "Ingrese una fecha")
	private LocalDate fechaNacimiento;
	
	@Id
	@NotBlank(message="Debe ingresar el LU")
	private String lu;
	
	@NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codigo")
    private Carrera carrera;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "materiaAlumno", joinColumns = @JoinColumn(name = "lu"),
	inverseJoinColumns = @JoinColumn(name = "codigo"))
	private List<Materia> materias;

	private Boolean estado;
	


}