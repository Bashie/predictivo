package predictivo.dao;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import predictivo.model.Categoria;
import predictivo.util.UnitTestHelper;

public class CategoriaDaoTest {

	@Spy
	@InjectMocks
	private CategoriaDao categoriaDao;
	@Mock
	private EntityManager entityManager;
	@Mock
	private TypedQuery<Categoria> query;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void findAllTest() {
		doReturn(query).when(entityManager).createQuery(any(), any());
		List<Categoria> listaTurnos = new ArrayList<Categoria>() {{
			add(new Categoria() {{setNombre(UnitTestHelper.CATEGORIA_NOMBRE);}});
		}};
		doReturn(listaTurnos).when(query).getResultList();
		List<Categoria> resultado = categoriaDao.findAll();
		Assertions.assertEquals(resultado.size(), 1);
		Assertions.assertEquals(resultado.get(0).getNombre(), UnitTestHelper.CATEGORIA_NOMBRE);
	}
}
