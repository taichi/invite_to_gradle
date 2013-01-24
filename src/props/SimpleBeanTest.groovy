package props

import static org.junit.Assert.*

import org.junit.Test

public class SimpleBeanTest {

	@Test
	public void testName() throws Exception {
		def bean = new SimpleBean( name: "John" )
		assert "John" == bean.name
	}

	@Test
	public void testJson() throws Exception {
		def bean = new SimpleBean( name: "John", age: 18)
		assert "{\"name\":\"John\", \"age\":18}" == bean.toJson()
	}
}
