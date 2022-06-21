package predictivo.controller.dto;

import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;
import org.meanbean.test.EqualsMethodTester;

public class UsuarioDtoTest {

	@Test
	public void testUsuarioDto() {
		BeanTester beanTester = new BeanTester();
		beanTester.testBean(UsuarioDto.class);
	}
}
