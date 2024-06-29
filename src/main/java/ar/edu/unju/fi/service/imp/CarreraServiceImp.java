package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.repository.CarreraRepository;
import ar.edu.unju.fi.service.ICarreraService;
@Service
public class CarreraServiceImp implements ICarreraService {
	@Autowired
	CarreraRepository carreraRepository;

	@Override
	public void guardarCarrera(Carrera carrera) {
		carrera.setEstado(true);
		carreraRepository.save(carrera);
	}

	@Override
	public List<Carrera> mostrarCarreras() {
		return carreraRepository.findCarreraByEstado(true);
	}

	@Override
	public void eliminarCarrera(String codigo) {
		Carrera carreraAEliminar=buscarCarreraPorCodigo(codigo);
		carreraAEliminar.setEstado(false);
		carreraRepository.save(carreraAEliminar);
	}

	@Override
	public void modificarCarrera(Carrera carreraModificada) {
		carreraModificada.setEstado(true);
		carreraRepository.save(carreraModificada);
	}

	@Override
	public Carrera buscarCarreraPorCodigo(String codigo) {
		return carreraRepository.findById(codigo).orElse(null);
	}

}
