package predictivo.model;

import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;

public class PrediccionSintacticaTest {

	 @Test
	 public void testPrediccionSintactica() {
	  BeanTester beanTester = new BeanTester();
	  beanTester.testBean(PrediccionSintactica.class);
	 }
	
}
