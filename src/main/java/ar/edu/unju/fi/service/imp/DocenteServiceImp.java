package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.repository.DocenteRepository;
import ar.edu.unju.fi.service.IDocenteService;
@Service
public class DocenteServiceImp implements IDocenteService{
	@Autowired
	DocenteRepository docenteRepository;

	@Override
	public void guardarDocente(Docente docente) {
		docente.setEstado(true);
		docenteRepository.save(docente);
		
	}

	@Override
	public List<Docente> mostrarDocentes() {
		// TODO Auto-generated method stub
		//return docenteRepository.findAll();
		return docenteRepository.findDocenteByEstado(true);
	}

	/**
	 *
	 */
	@Override
	public void eliminarDocente(String legajo) {
		Docente docenteABuscar = buscarDocentePorLegajo(legajo);
		docenteABuscar.setEstado(false);
		docenteRepository.save(docenteABuscar);

	}

	@Override
	public void modificarDocente(Docente docenteModificado) {
		// TODO Auto-generated method stub
		docenteModificado.setEstado(true);
		docenteRepository.save(docenteModificado);		
	}

	@Override
	public Docente buscarDocentePorLegajo(String legajo) {
		// TODO Auto-generated method stub
		for (Docente docente : docenteRepository.findAll()) {
			if (docente.getLegajo().equals(legajo)) {
				return docente;
			}
		}
		return null;
	}
					
	

}
