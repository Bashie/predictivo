package predictivo.model;

import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;

public class UsuarioTest {

	 @Test
	 public void testUsuario() {
	  BeanTester beanTester = new BeanTester();
	  beanTester.testBean(Usuario.class);
	 }
	
}
