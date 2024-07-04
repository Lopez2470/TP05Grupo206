package ar.edu.unju.fi.map;

import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ar.edu.unju.fi.DTO.MateriaDTO;
import ar.edu.unju.fi.model.Materia;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MateriaMapDTO {
	@Mapping(source = "codigo",target = "codigo")
	@Mapping(source = "nombre",target = "nombre")
	@Mapping(source = "curso",target = "curso")
	@Mapping(source = "horas",target = "horas")
	@Mapping(source = "modalidad",target = "modalidad")
	@Mapping(source = "docente",target = "docente")
	@Mapping(source = "carrera",target = "carrera")
	@Mapping(source = "estado",target = "estado")
	MateriaDTO convertirMateriaAMateriaDTO(Materia materia);
	
	@InheritInverseConfiguration
	Materia convertirMateriaDTOAMateria(MateriaDTO materiaDTO);
		
	List<MateriaDTO> convertirListaMateriasAListaMateriasDTO (List<Materia> listaDeMaterias);
	
	List<Materia> convertirListaMateriasDTOAListaMaterias (List<MateriaDTO> listaDeMateriasDTO);
}
