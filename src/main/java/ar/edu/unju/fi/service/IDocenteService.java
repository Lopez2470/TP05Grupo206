package ar.edu.unju.fi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.fi.DTO.DocenteDTO;
import ar.edu.unju.fi.model.Docente;

//import ar.edu.unju.fi.model.Docente;
@Service
public interface IDocenteService {
	/*Usar DocenteDTO*/

	public void guardarDocente(Docente docente);
	public List<DocenteDTO> mostrarDocentes();
	public void eliminarDocente(String legajo);
	public void modificarDocente(Docente docente);
	public Docente buscarDocentePorLegajo(String legajo);


}