package props;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SimpleBeanTest {

	@Test
	public void testName() throws Exception {
		SimpleBean bean = new SimpleBean();
		bean.setName("John");
		assertEquals("John", bean.getName());
	}
}
