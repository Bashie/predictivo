package predictivo.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import predictivo.model.Categoria;

@Component
public class CategoriaDao extends BaseDAO<Categoria> {
    public List<Categoria> findAll() {
		TypedQuery<Categoria> q = entityManager.createQuery("select c from Categoria c", Categoria.class);
		return q.getResultList();
	}

	public Categoria findByName(String nombreCategoria) {
		TypedQuery<Categoria> q = entityManager.createQuery("select c from Categoria c where c.nombre = :nombre", Categoria.class).setParameter("nombre", nombreCategoria);
		try {
			return q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}