package predictivo.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;

import predictivo.model.FraseUsadaDw;
import predictivo.model.Pictograma;

@Component
@EnableMongoRepositories
@Transactional
public class DwDao {

	@Autowired 
	private PictogramaDao pictogramaDao;
	
	@PersistenceContext
	protected EntityManager entityManager;

	@Autowired
	private ItemRepository frasesItemRepo;
	
	public void syncBaseADw() {
		cargar();
		limpiarTabla();

	}

	private void limpiarTabla() {
		entityManager.createNativeQuery("DELETE FROM frase_usada_dw").executeUpdate();
		
	}

	private void cargar() {
		List<Object[]> frases = entityManager.createNativeQuery("SELECT pictograma_Ids, peso FROM frase_usada_dw").getResultList();

		for (Object[] frase : frases) {
			String pictogramaIds = (String) frase[0];
			Integer peso = (Integer) frase[1];
			String[] ids = pictogramaIds.split(",");
			
			StringBuffer aarasacIds = new StringBuffer();
			for (String id : ids) {
				Pictograma picto = pictogramaDao.findById(Integer.parseInt(id));
				aarasacIds.append(picto.getAarasacId() + ",");
			}
			FraseUsadaDw fraseUsadaDw = frasesItemRepo.findItemByPictoIds(aarasacIds.toString());
			
			if(Objects.isNull(fraseUsadaDw)) {
				frasesItemRepo.save(new FraseUsadaDw(aarasacIds.toString(), peso));
			} else {
				fraseUsadaDw.addPeso(peso);
				frasesItemRepo.save(fraseUsadaDw);
			}
		}
	}

}
