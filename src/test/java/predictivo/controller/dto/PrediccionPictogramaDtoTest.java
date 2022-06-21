package predictivo.controller.dto;

import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;

public class PrediccionPictogramaDtoTest {

	 @Test
	 public void testPrediccionPictogramaDto() {
	  BeanTester beanTester = new BeanTester();
	  beanTester.testBean(PrediccionPictogramaDto.class);
	 }
	
}
