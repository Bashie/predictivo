package predictivo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import predictivo.dao.CategoriaDao;
import predictivo.model.Categoria;

@Component
public class CategoriaService {
	@Autowired
	private CategoriaDao categoriaDao;

	public List<Categoria> getCategorias(Integer tipo) {
		return categoriaDao.findAllByTipo(tipo);
	}

}
