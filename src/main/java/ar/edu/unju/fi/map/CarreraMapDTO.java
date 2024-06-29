package ar.edu.unju.fi.map;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import ar.edu.unju.fi.DTO.CarreraDTO;
import ar.edu.unju.fi.model.Carrera;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CarreraMapDTO {
	@Mapping(source = "codigo",target = "codigo")
	@Mapping(source = "nombre",target = "nombre")
	@Mapping(source = "duracion",target = "duracion")
	@Mapping(source = "estado",target = "estado")
	CarreraDTO convertirCarreraACarreraDTO(Carrera carrera);
	
	@InheritInverseConfiguration
	Carrera convertirCarreraDTOACarrera(CarreraDTO carreraDTO);
	
	
	List<CarreraDTO> convertirListaCarrerasAListaCarrerasDTO (List<Carrera> listaDeCarreras);
	
	List<Carrera> convertirListaCarrerasDTOAListaCarreras (List<CarreraDTO> listaDeCarrerasDTO);

}
