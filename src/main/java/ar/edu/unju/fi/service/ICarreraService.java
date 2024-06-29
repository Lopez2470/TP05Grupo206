package ar.edu.unju.fi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.fi.DTO.CarreraDTO;
import ar.edu.unju.fi.model.Carrera;

@Service
public interface ICarreraService {
	public void guardarCarrera(Carrera carrera);
	public List<CarreraDTO> mostrarCarreras();
	public void eliminarCarrera(String codigo);
	public void modificarCarrera(Carrera carrera);
	public Carrera buscarCarreraPorCodigo(String codigo);

}

