package closures

import org.junit.Test

public class ClosureTest {

	@Test
	public void stringsToUpperCase() {
		def list = ["a", "b", "c", "d"]
		def actual = list.collect { it.toUpperCase() }
		assert ["A", "B", "C", "D"]== actual
	}
}
