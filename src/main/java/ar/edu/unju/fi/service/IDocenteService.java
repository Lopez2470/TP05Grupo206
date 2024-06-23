package ar.edu.unju.fi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.fi.DTO.DocenteDTO;

//import ar.edu.unju.fi.model.Docente;
@Service
public interface IDocenteService {
	/*Usar DocenteDTO*/
	public void guardarDocenteDTO(DocenteDTO docenteDTO);
	public List<DocenteDTO> mostrarDocentesDTO();
	public void eliminarDocenteDTO(String legajo);
	public void modificarDocenteDTO(DocenteDTO docenteDTO);
	public DocenteDTO buscarDocenteDTOPorLegajo(String legajo);
	
	
	/*
	public void guardarDocente(Docente docente);
	public List<Docente> mostrarDocentes();
	public void eliminarDocente(String legajo);
	public void modificarDocente(Docente docente);
	public Docente buscarDocentePorLegajo(String legajo);
	 
	 */

}