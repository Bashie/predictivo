package predictivo.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import predictivo.model.FraseUsada;

@Component
public class FraseUsadaDao extends BaseDAO<FraseUsada> {
    public List<FraseUsada> findAll() {
		TypedQuery<FraseUsada> q = entityManager.createQuery("select f from FraseUsada f", FraseUsada.class);
		return q.getResultList();
	}
}