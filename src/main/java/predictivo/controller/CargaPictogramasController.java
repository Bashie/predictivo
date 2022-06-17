package predictivo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import predictivo.service.PictogramaService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CargaPictogramasController {

	
Logger logger = LoggerFactory.getLogger(CargaPictogramasController.class);
	
	@Autowired
	private PictogramaService pictogramaService;
	
	@RequestMapping("/cargaPictogramas/{from}/{to}")
	@ResponseBody
	public String cargarPictogramas(@PathVariable(name="from", required = true) Integer from, @PathVariable(name="to", required = true) Integer to) {
		if (from <= to) {
			return "Pictogramas cargados: " + pictogramaService.cargarPictogramas(from, to);
		}
		return "La llamada debe ser /cargaPictogramas/{desde}/{hasta} siende <desde> menor que <hasta>";
	}
	
}
