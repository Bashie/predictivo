package predictivo.controller.dto;

import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;
import org.meanbean.test.EqualsMethodTester;

public class FraseUsadaDtoTest {

	 @Test
	 public void testFraseUsadaDto() {
	  BeanTester beanTester = new BeanTester();
	  beanTester.testBean(FraseUsadaDto.class);
	 }	
}
