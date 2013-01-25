package jp.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Ignore;
import org.junit.Test;

public class FirstTest {

	@Test
	public void testFoo() {
		assertEquals(3, 1 + 2);
	}

	@Test
	public void testBar() throws Exception {
		assertEquals("moge", "moge");
	}

	@Test
	@Ignore
	public void testFail() throws Exception {
		fail("Fail");
	}
}
