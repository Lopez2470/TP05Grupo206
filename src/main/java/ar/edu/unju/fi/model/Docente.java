package ar.edu.unju.fi.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Component
@Data
@Entity
public class Docente {
	@Id
	@NotBlank(message="Debe ingresar el Legajo del Docente")
	private String legajo;
	
	@NotBlank(message="Debe ingresar nombre del Docente")
	@Size(min=3, max=20, message="El nombre debe contener como mínimo 3 caracteres y como máximo 20 caracteres")
	@Pattern(regexp= "[a-z A-Z]*")
	private String nombres;
	
	@NotBlank(message="Debe ingresar el apellido del Docente")
	@Size(min=3, max=20, message="El apellido debe contener como mínimo 3 caracteres y como máximo 20 caracteres")
	@Pattern(regexp= "[a-z A-Z]*")
	private String apellido;
	
	@NotBlank(message = "Ingrese un nro telefonico")
	@Email
	private String email;
	
	@NotBlank(message = "Ingrese un nro telefonico")
	//@Size(max=11)
	@Pattern(regexp="[0-9]{3}+-+[0-9]{7}")
	private String telefono;
	
	private Boolean estado;

}
