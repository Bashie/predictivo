package predictivo.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import predictivo.model.Guardable;

@Component
@Repository
@Transactional
public abstract class BaseDAO<T extends Guardable> {

	@PersistenceContext
	protected EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
	public void persist(T entity) {
		entityManager.persist(entity);
		entityManager.flush();
	}
	
	public T save(T entity) {
		if(Objects.isNull(entity.getId()) || Objects.isNull(this.findById(entity.getId()))) {
			this.persist(entity);
		} else {
			this.update(entity);
		}
		return entity;
	}
	
	public void update(T entity) {
		entityManager.merge(entity);
	}

	public T findById(Integer id) {
		T book = (T) entityManager.find(getClassType(), id);
		return book;
	}

	private Class getClassType() {
		return ((Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
	}
	
	public void delete(T entity) {
		entityManager.remove(entity);
	}

	public abstract List<T> findAll();

	public void deleteAll() {
		List<T> entityList = findAll();
		for (T entity : entityList) {
			delete(entity);
		}
	}
}
