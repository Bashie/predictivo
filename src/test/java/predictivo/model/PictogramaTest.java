package predictivo.model;

import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;
import org.meanbean.test.EqualsMethodTester;
import org.meanbean.test.HashCodeMethodTester;

import predictivo.controller.dto.PictogramaDto;

public class PictogramaTest {

	@Test
	public void testPictograma() {
		BeanTester beanTester = new BeanTester();
		beanTester.testBean(Pictograma.class);
	}

	@Test
	public void testPictogramaEquals() {
		EqualsMethodTester tester = new EqualsMethodTester();
		tester.testEqualsMethod(Pictograma.class);
	}

	@Test
	public void testPictogramaHashcode() {
		HashCodeMethodTester tester = new HashCodeMethodTester();
		tester.testHashCodeMethod(Pictograma.class);
	}
}
