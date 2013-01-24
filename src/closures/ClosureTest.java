package closures;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class ClosureTest {

	@Test
	public void stringsToUpperCase() {
		List<String> list = Arrays.asList("a", "b", "c", "d");
		List<String> actual = new ArrayList<>();
		for (String s : list) {
			actual.add(s.toUpperCase());
		}
		assertEquals(Arrays.asList("A", "B", "C", "D"), actual);
	}

}
