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

import predictivo.model.FraseUsada;
import predictivo.util.UnitTestHelper;

public class FraseUsadaDaoTest {

	@Spy
	@InjectMocks
	private FraseUsadaDao fraseUsadaDao;
	@Mock
	private EntityManager entityManager;
	@Mock
	private PictogramaDao pictogramaDao;
	@Mock
	private TypedQuery<FraseUsada> query;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		doReturn(query).when(entityManager).createQuery(any(), any());
	}
	
	@Test
	public void findAllTest() {
		List<FraseUsada> listaTurnos = new ArrayList<FraseUsada>() {{
			add(new FraseUsada() {{setPictogramaIds(UnitTestHelper.PICTOGRAMAS_ID);}});
		}};
		doReturn(listaTurnos).when(query).getResultList();
		List<FraseUsada> resultado = fraseUsadaDao.findAll();
		Assertions.assertEquals(resultado.size(), 1);
		Assertions.assertEquals(resultado.get(0).getPictogramaIds(), UnitTestHelper.PICTOGRAMAS_ID);
	}
	
	@Test
	public void getFraseUsadaTest() {
		doReturn(UnitTestHelper.getFraseUsada()).when(query).getSingleResult();
		doReturn(query).when(query).setParameter(any(String.class), any());
		FraseUsada resultado = fraseUsadaDao.getFraseUsada(UnitTestHelper.getFraseUsada());
		Assertions.assertEquals(UnitTestHelper.FRASEUSADA_ID , resultado.getId());
		Assertions.assertEquals(UnitTestHelper.PICTOGRAMAS_ID , resultado.getPictogramaIds());
	}
	
	@Test
	public void saveTestUpdate() {
		doReturn(UnitTestHelper.getFraseUsada()).when(fraseUsadaDao).getFraseUsada(any());
		FraseUsada resultado = fraseUsadaDao.save(UnitTestHelper.getFraseUsada());
		Assertions.assertEquals(UnitTestHelper.FRASEUSADA_PESO +1 , resultado.getPeso());
	}
	
	@Test
	public void saveTestNuevo() {
		doReturn(null).when(fraseUsadaDao).getFraseUsada(any());
		FraseUsada resultado = fraseUsadaDao.save(UnitTestHelper.getFraseUsada());
		Assertions.assertEquals(UnitTestHelper.FRASEUSADA_PESO, resultado.getPeso());
	}
	
	@Test
	public void addPictosTest() {
		doReturn(UnitTestHelper.getPictograma()).when(pictogramaDao).findById(1);
		doReturn(UnitTestHelper.getPictograma()).when(pictogramaDao).findById(2);
		doReturn(UnitTestHelper.getPictograma()).when(pictogramaDao).findById(3);
		FraseUsada resultado = fraseUsadaDao.addPictos(UnitTestHelper.getFraseUsada());
		Assertions.assertEquals(3, resultado.getPictogramas().size());
		Assertions.assertEquals(UnitTestHelper.AARASAC_ID, resultado.getPictogramas().get(0).getAarasacId());
		
		
		
	}
	
}

