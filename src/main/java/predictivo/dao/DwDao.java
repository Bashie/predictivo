package predictivo.dao;

import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;

import predictivo.model.FraseUsadaDw;
import predictivo.model.Pictograma;
import predictivo.model.PrediccionPictograma;

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
	@Autowired
	private PrediccionPictogramaDao prediccionPictogramaDao;
	
	public void syncBaseADw() {
		cargar();
		limpiarTabla();
		traerDatos();
	}

	private void traerDatos() {
		List<FraseUsadaDw> resultados = frasesItemRepo.findAll();

		for (FraseUsadaDw fraseUsadaDw : resultados) {
			try {
				PrediccionPictograma pred = PrediccionPictograma.fromFraseUsadaDw(fraseUsadaDw.getPeso(), getLocalIds(fraseUsadaDw.getPictogramaIds()));
				System.out.println(pred);
				prediccionPictogramaDao.save(pred);
			} catch (NoResultException e) {
				//saltear porque no existe el picto local
			}
		}
		
	}

	private String getLocalIds(String pictogramaIds) throws NoResultException {
		String[] ids = pictogramaIds.split(",");
		StringBuffer localIds = new StringBuffer();
		for (String id : ids) {
			Pictograma picto = pictogramaDao.findByAarasacId(Integer.parseInt(id));
			localIds.append(picto.getId() + "," );
		}
		return localIds.toString();
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
