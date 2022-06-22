package predictivo.model;

import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;
import org.meanbean.test.EqualsMethodTester;
import org.meanbean.test.HashCodeMethodTester;

public class PrediccionPictogramaTest {

	@Test
	public void testPrediccionPictograma() {
		BeanTester beanTester = new BeanTester();
		beanTester.testBean(PrediccionPictograma.class);
	}

	@Test
	public void testPrediccionPictogramaEquals() {
		EqualsMethodTester tester = new EqualsMethodTester();
		tester.testEqualsMethod(PrediccionPictograma.class, "pictogramas");
	}

	@Test
	public void testPrediccionPictogramaHashcode() {
		HashCodeMethodTester tester = new HashCodeMethodTester();
		tester.testHashCodeMethod(PrediccionPictograma.class);
	}
}
