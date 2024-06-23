package ar.edu.unju.fi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.fi.model.Docente;
@Service
public interface IDocenteService {
	public void guardarDocente(Docente docente);
	public List<Docente> mostrarDocentes();
	public void eliminarDocente(String legajo);
	public void modificarDocente(Docente docente);
	public Docente buscarDocentePorLegajo(String legajo);

}
