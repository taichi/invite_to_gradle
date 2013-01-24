package props

import static org.junit.Assert.assertEquals

import org.junit.Test

public class SimpleBeanTest {

	@Test
	public void testName() throws Exception {
		def bean = new SimpleBean( name: "John" )
		assert "John" == bean.name
	}
}
