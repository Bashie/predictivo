package predictivo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import predictivo.dao.DwDao;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CargarDwController {

	Logger logger = LoggerFactory.getLogger(CargarDwController.class);
	
	@Autowired
	private DwDao dwDao;
	
	@RequestMapping("/cargarDw")
	@ResponseBody
	public void getPictogramas() {
		dwDao.syncBaseADw();	
	}

}
