package ar.edu.unju.fi.map;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import ar.edu.unju.fi.DTO.AlumnoDTO;
import ar.edu.unju.fi.model.Alumno;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AlumnoMapDTO {
	
	@Mapping(source = "dni",target = "dni")
	@Mapping(source = "nombres",target = "nombres")
	@Mapping(source = "apellido",target = "apellido")
	@Mapping(source = "email",target = "email")
	@Mapping(source = "telefono",target = "telefono")
	@Mapping(source = "fechaNacimiento",target = "fechaNacimiento")
	@Mapping(source = "lu",target = "lu")
	@Mapping(source = "carrera",target = "carrera")
	@Mapping(source = "estado",target = "estado")
	AlumnoDTO convertirAlumnoAAlumnoDTO(Alumno alumno);
	
	@InheritInverseConfiguration
	Alumno convertirAlumnoDTOAAlumno(AlumnoDTO alumnoDTO);
	
	
	List<AlumnoDTO> convertirListaAlumnosAListaAlumnosDTO (List<Alumno> listaDeAlumnos);
	
	List<Alumno> convertirListaAlumnosDTOAListaAlumnos (List<AlumnoDTO> listaDeAlumnosDTO);

}

