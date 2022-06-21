package predictivo.model;

import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;

public class CategoriaTest {

	 @Test
	 public void testCategoria() {
	  BeanTester beanTester = new BeanTester();
	  beanTester.testBean(Categoria.class);
	 }
	
}
