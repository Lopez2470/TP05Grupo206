package ar.edu.unju.fi.DTO;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.model.Carrera;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Component
public class AlumnoDTO {
	private String dni;
	private String nombres;
	private String apellido;
	private String email;
	private String telefono;
	private LocalDate fechaNacimiento;
	private String lu;
	private Carrera carrera;
	private Boolean estado;

}
