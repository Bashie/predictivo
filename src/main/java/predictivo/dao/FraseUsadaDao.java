package predictivo.dao;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import predictivo.model.FraseUsada;

@Component
public class FraseUsadaDao extends BaseDAO<FraseUsada> {
	@Autowired
	private PictogramaDao pictogramaDao;
	
    public List<FraseUsada> findAll() {
		TypedQuery<FraseUsada> q = entityManager.createQuery("select f from FraseUsada f", FraseUsada.class);
		return q.getResultList();
	}
    
    public FraseUsada getFraseUsada(FraseUsada fraseUsada) {
    	TypedQuery<FraseUsada> q = entityManager.createQuery("select f from FraseUsada f where pictogramaIds=:pictogramaIds", FraseUsada.class).setParameter("pictogramaIds", fraseUsada.getPictogramaIds());
		try {
			return q.getSingleResult();
		} catch (Exception e) {
			return null;
		}
    }
    
    @Override
    public FraseUsada save(FraseUsada entity) {
    	FraseUsada frase = this.getFraseUsada(entity);
		if(Objects.isNull(frase)) {
			this.persist(entity);
		} else {
			frase.addUso();
			this.update(entity);
		}
		return entity;
	}
    
    public List<FraseUsada> getPredicciones(FraseUsada fraseUsada) {
    	TypedQuery<FraseUsada> q = entityManager.createQuery("select f from FraseUsada f where pictogramaIds like :pictogramaIds and usuario=:usuario order by peso DESC", FraseUsada.class)
    			.setParameter("pictogramaIds", fraseUsada.getPictogramaIds()+"%")
    			.setParameter("usuario", fraseUsada.getUsuario());
    	List<FraseUsada> frases = q.getResultList();
    	return frases.stream().map(frase -> addPictos(frase)).collect(Collectors.toList());
    }
    
    public FraseUsada addPictos(FraseUsada frase) {
    	for (String pictoId : frase.getPictogramaIds().split(",")) {
    		frase.getPictogramas().add(pictogramaDao.findById(Integer.parseInt(pictoId)));
		}
    	return frase; 
    }
    
    public Integer getCantidadFrasesUsadas() {
    	Query count = entityManager.createQuery("select count(*) from FraseUsada f");
    	return (Integer) count.getSingleResult();    	
    }
}