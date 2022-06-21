package predictivo.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	
	public Set<PictogramaDto> getPredicciones(List<PictogramaDto> fraseUsada , Integer usuarioId) {

		List<Pictograma> pictosUsados = new ArrayList<>();
		for (PictogramaDto pictoDto : fraseUsada) {
			pictosUsados.add(getPictograma(pictoDto.getId()));
		}

		FraseUsada frase = getFraseUsada(pictosUsados, usuarioId);
		Set<PictogramaDto> prediccion = getPredicciones(frase);

		fraseUsadaDao.save(frase);
		
		calcularMatrizDePesos();
		
		return prediccion;
	}

	private FraseUsada getFraseUsada(List<Pictograma> fraseUsada, Integer usuarioId) {
		FraseUsada frase = new FraseUsada();
		frase.addFrase(fraseUsada);
		frase.setUsuario(usuarioDao.findById(usuarioId));
		fraseUsadaDao.addPictos(frase);
		return frase;
	}

	private void calcularMatrizDePesos() {
		// TODO Auto-generated method stub
		//prediccionPictogramaDao
	}

	private Set<PictogramaDto> getPredicciones(FraseUsada frase) {
		Set<PictogramaDto> resultado = new HashSet<>();
		List<FraseUsada> pred = fraseUsadaDao.getPredicciones(frase);
		for (FraseUsada fraseUsada : pred) {
			if (fraseUsada.getPictogramas().size() > frase.getPictogramas().size()) {
				resultado.add(fraseUsada.getPictogramas().get(frase.getPictogramas().size()).toDto());
			}
		}
		
		//return pictogramaDao.findAll().stream().map(Pictograma::toDto).collect(Collectors.toList());
		return resultado;
	}

	private Pictograma getPictograma(Integer id) {
		return pictogramaDao.findById(id);
	}


}
