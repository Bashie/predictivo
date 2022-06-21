package predictivo.controller.dto;

import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;
import org.meanbean.test.EqualsMethodTester;
import org.meanbean.test.HashCodeMethodTester;

public class PictogramaDtoTest {

	@Test
	public void testPictogramaDto() {
		BeanTester beanTester = new BeanTester();
		beanTester.testBean(PictogramaDto.class);
	}

	@Test
	public void testPictogramaDtoEquals() {
		EqualsMethodTester tester = new EqualsMethodTester();
		tester.testEqualsMethod(PictogramaDto.class, "categoria");
	}
	
	@Test
	  public void testPictogramaDtoHashcode() {
	    HashCodeMethodTester tester = new HashCodeMethodTester();
		tester.testHashCodeMethod(PictogramaDto.class);
	}
}
