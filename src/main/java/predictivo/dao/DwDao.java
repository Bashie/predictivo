package predictivo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;

import predictivo.model.FraseUsadaDw;

@Component
@EnableMongoRepositories
public class DwDao {

	@Autowired
    private ItemRepository groceryItemRepo;
	
	public void syncBaseADw() {
		cargar();
		limpiarTabla();
		
		
	}

	private void limpiarTabla() {
		// TODO Auto-generated method stub
		
	}

	private void cargar() {
		groceryItemRepo.save(new FraseUsadaDw("3002,3003,", 0));
		
	}
	
}
