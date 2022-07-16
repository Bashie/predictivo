package predictivo.dao;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import predictivo.model.FraseUsada;
import predictivo.model.PrediccionPictograma;

@Component
public class PrediccionPictogramaDao extends BaseDAO<PrediccionPictograma> {
	
	@Autowired
	private PictogramaDao pictogramaDao;
	
    public List<PrediccionPictograma> findAll() {
		TypedQuery<PrediccionPictograma> q = entityManager.createQuery("select p from PrediccionPictograma p", PrediccionPictograma.class);
		return q.getResultList();
	}
    
    public List<PrediccionPictograma> getPredicciones(FraseUsada fraseUsada) {
    	TypedQuery<PrediccionPictograma> q = entityManager.createQuery("select pp from PrediccionPictograma pp where pictogramaIds like :pictogramaIds order by peso DESC", PrediccionPictograma.class)
    			.setParameter("pictogramaIds", fraseUsada.getPictogramaIds()+"%");
    	List<PrediccionPictograma> frases = q.getResultList();
    	return frases.stream().map(frase -> addPictos(frase)).collect(Collectors.toList());
    }
    
    public PrediccionPictograma addPictos(PrediccionPictograma frase) {
    	for (String pictoId : frase.getPictogramaIds().split(",")) {
    		frase.getPictogramas().add(pictogramaDao.findById(Integer.parseInt(pictoId)));
		}
    	return frase; 
    }
}