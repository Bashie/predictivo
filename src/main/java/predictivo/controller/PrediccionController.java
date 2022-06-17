package predictivo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import predictivo.controller.dto.PictogramaDto;
import predictivo.service.PrediccionService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PrediccionController {

	Logger logger = LoggerFactory.getLogger(PrediccionController.class);
	
	@Autowired
	private PrediccionService prediccionService;
	
	
	@RequestMapping("/prediccion")
	@ResponseBody
	public String buscarPrediccion(@RequestParam(name="fraseUsada", required = true) String[] fraseUsada, @RequestParam(name="usuarioId", required = true) Integer usuarioId) {
		prediccionService.getPredicciones(fraseUsada, usuarioId);
		
		
		
		return null;
	}

}
