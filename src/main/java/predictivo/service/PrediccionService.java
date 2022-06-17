package predictivo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import predictivo.controller.dto.PictogramaDto;
import predictivo.dao.FraseUsadaDao;
import predictivo.dao.PictogramaDao;
import predictivo.dao.PrediccionPictogramaDao;
import predictivo.dao.UsuarioDao;
import predictivo.model.FraseUsada;
import predictivo.model.Pictograma;

@Component
public class PrediccionService {
	@Autowired
	private PictogramaDao pictogramaDao;
	@Autowired
	private PrediccionPictogramaDao prediccionPictogramaDao;
	@Autowired
	private FraseUsadaDao fraseUsadaDao;
	@Autowired
	private UsuarioDao usuarioDao;
	
	public List<PictogramaDto> getPredicciones(String[] ids, Integer usuarioId) {

		List<Pictograma> fraseUsada = new ArrayList<>();
		for (String id : ids) {
			fraseUsada.add(getPictograma(id));
		}

		List<PictogramaDto> prediccion = getPredicciones(fraseUsada);
		
		fraseUsadaDao.save(getFraseUsada(fraseUsada, usuarioId));
		
		calcularMatrizDePesos();
		
		return prediccion;
	}

	private FraseUsada getFraseUsada(List<Pictograma> fraseUsada, Integer usuarioId) {
		FraseUsada frase = new FraseUsada();
		frase.setFrase(fraseUsada);
		frase.setUsuario(usuarioDao.findById(usuarioId));		
		return frase;
	}

	private void calcularMatrizDePesos() {
		// TODO Auto-generated method stub
		
	}

	private List<PictogramaDto> getPredicciones(List<Pictograma> fraseUsada) {
		return prediccionPictogramaDao.getPredicciones();
	}

	private Pictograma getPictograma(String id) {
		return pictogramaDao.findById(Integer.parseInt(id));
	}

}
