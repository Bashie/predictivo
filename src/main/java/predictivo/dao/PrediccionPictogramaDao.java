package predictivo.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import predictivo.model.PrediccionPictograma;

@Component
public class PrediccionPictogramaDao extends BaseDAO<PrediccionPictograma> {
    public List<PrediccionPictograma> findAll() {
		TypedQuery<PrediccionPictograma> q = entityManager.createQuery("select p from PrediccionPictograma p", PrediccionPictograma.class);
		return q.getResultList();
	}
    
    public void crearPredicciones() {
    	
    	
    	
    }
    
}