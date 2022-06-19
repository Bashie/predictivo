package predictivo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
	
	public List<PictogramaDto> getPredicciones(List<PictogramaDto> fraseUsada , Integer usuarioId) {

		List<Pictograma> pictosUsados = new ArrayList<>();
		for (PictogramaDto pictoDto : fraseUsada) {
			pictosUsados.add(getPictograma(pictoDto.getId()));
		}

		List<PictogramaDto> prediccion = getPredicciones(pictosUsados);
		
		fraseUsadaDao.save(getFraseUsada(pictosUsados, usuarioId));
		
		calcularMatrizDePesos();
		
		return prediccion;
	}

	private FraseUsada getFraseUsada(List<Pictograma> fraseUsada, Integer usuarioId) {
		FraseUsada frase = new FraseUsada();
		frase.addFrase(fraseUsada);
		frase.setUsuario(usuarioDao.findById(usuarioId));		
		return frase;
	}

	private void calcularMatrizDePesos() {
		// TODO Auto-generated method stub
		
	}

	private List<PictogramaDto> getPredicciones(List<Pictograma> fraseUsada) {
		return pictogramaDao.findAll().stream().map(Pictograma::toDto).collect(Collectors.toList());
	}

	private Pictograma getPictograma(Integer id) {
		return pictogramaDao.findById(id);
	}


}
