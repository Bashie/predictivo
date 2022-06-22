package predictivo.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import predictivo.model.FraseUsadaDw;

public interface ItemRepository extends MongoRepository<FraseUsadaDw, String> {

	@Query("{pictogramaIds:'?0'}")
	FraseUsadaDw findItemByName(String name);

	@Query(value = "{pictogramaIds:'?0'}", fields = "{'pictogramaIds' : 1, 'peso' : 1}")
	List<FraseUsadaDw> findAll(String pictogramaIds);

	public long count();

}
