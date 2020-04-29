package fantasyBaseball;

import static org.junit.Assert.*;

import org.junit.Test;

public class DraftTest {
	private static final int DEFAULT_TIMEOUT = 2000;

	@Test (timeout = DEFAULT_TIMEOUT)
	public void isTeamValidTest() {
		Draft d1 = new Draft();
		
		assertEquals(true, d1.isTeamValid("A"));
		assertEquals(false, d1.isTeamValid("F"));
		assertEquals(false, d1.isTeamValid("a"));
	}
}
