package closures

import static org.junit.Assert.assertEquals

import org.junit.Test

public class ClosureTest {

	@Test
	public void stringsToUpperCase() {
		def list = ["a", "b", "c", "d"]
		def actual = list.collect { s ->
			s.toUpperCase()
		}
		assert ["A", "B", "C", "D"]== actual
	}
}
