package predictivo.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import predictivo.model.FuncionSintactica;
import predictivo.model.Pictograma;

@Component
public class PictogramaDao extends BaseDAO<Pictograma> {
	public List<Pictograma> findAll() {
		TypedQuery<Pictograma> q = entityManager.createQuery("select p from Pictograma p", Pictograma.class);
		return q.getResultList();
	}

	public Pictograma findByName(String nombre) {
		TypedQuery<Pictograma> q = entityManager
				.createQuery("select p from Pictograma p where p.nombre = :nombre", Pictograma.class)
				.setParameter("nombre", nombre);
		try {
			return q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<Pictograma> findAllMaxResultados(Integer maxResultados) {
		TypedQuery<Pictograma> q = entityManager.createQuery("select p from Pictograma p", Pictograma.class).setMaxResults(maxResultados);
		return q.getResultList();
	}
	
	public List<Pictograma> findByCategoria(Integer categoria) {
		TypedQuery<Pictograma> q = entityManager
				.createQuery("select p from Pictograma p where p.funcionSintactica = :funcion_sintactica",
						Pictograma.class)
				.setParameter("funcion_sintactica", FuncionSintactica.fromDbValue(categoria)).setMaxResults(50);
		return q.getResultList();
	}
}