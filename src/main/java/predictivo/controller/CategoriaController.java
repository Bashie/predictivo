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

import predictivo.model.Categoria;
import predictivo.service.CategoriaService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CategoriaController {

	Logger logger = LoggerFactory.getLogger(CategoriaController.class);
	
	@Autowired
	private CategoriaService categoriaService;
	
	@RequestMapping("/categorias/{tipo}")
	@ResponseBody
	public List<Categoria> getPictogramas(@PathVariable(name="tipo", required = true) Integer tipo) {
		return categoriaService.getCategorias(tipo);
	}
}
