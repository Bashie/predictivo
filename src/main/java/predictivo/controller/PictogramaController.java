package predictivo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import predictivo.model.Pictograma;
import predictivo.service.PictogramaService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PictogramaController {

	Logger logger = LoggerFactory.getLogger(PictogramaController.class);
	
	@Autowired
	private PictogramaService pictogramaService;
	
	@RequestMapping("/pictogramas/{categoria}")
	@ResponseBody
	public List<Pictograma> getPictogramas(@PathVariable(name="categoria", required = true) Integer categoria) {
		return pictogramaService.getPictogramas(categoria);
		
	}
	
}
