package predictivo.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import predictivo.model.FraseUsadaDw;

public interface ItemRepository extends MongoRepository<FraseUsadaDw, String> {

	@Query("{pictogramaIds:'?0'}")
	FraseUsadaDw findItemByPictoIds(String pictogramaIds);

	@Override
	List<FraseUsadaDw> findAll();

	public long count();

}
