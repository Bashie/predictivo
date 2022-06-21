package predictivo.controller.dto;

import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;
import org.meanbean.test.EqualsMethodTester;

public class CategoriaDtoTest {

	@Test
	public void testCategoriaDto() {
		BeanTester beanTester = new BeanTester();
		beanTester.testBean(CategoriaDto.class);
	}

	@Test
	public void testCategoriaDtoEquals() {
		EqualsMethodTester tester = new EqualsMethodTester();
		tester.testEqualsMethod(CategoriaDto.class);
	}
}
