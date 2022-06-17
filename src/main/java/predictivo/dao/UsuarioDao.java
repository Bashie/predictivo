package predictivo.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import predictivo.model.Usuario;

@Component
public class UsuarioDao extends BaseDAO<Usuario> {
    public List<Usuario> findAll() {
		TypedQuery<Usuario> q = entityManager.createQuery("select t from Usuario t", Usuario.class);
		return q.getResultList();
	}

	public Usuario findByUsername(String username) throws NoResultException {
		TypedQuery<Usuario> q = entityManager.createQuery("select t from Usuario t where email=:username", Usuario.class).setParameter("username", Integer.parseInt(username));
		return q.getSingleResult();
	}
    
}