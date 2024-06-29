package ar.edu.unju.fi.model;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Component
@Data
@Entity
public class Alumno {
	private String dni;
	private String nombres;
	private String apellido;
	private String email;
	private String telefono;
	private LocalDate fechaNacimiento;
	@Id
	private String lu;
	private Boolean estado;
}