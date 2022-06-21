package predictivo.model;

import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;

public class PictogramaTest {

	 @Test
	 public void testPictograma() {
	  BeanTester beanTester = new BeanTester();
	  beanTester.testBean(Pictograma.class);
	 }
	
}
