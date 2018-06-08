package DateLibraryTest;

import DateLibrary.entrega1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class entrega1Test {

	@Test
	public void validateCorrectNextDay() {
		assertEquals(entrega1.nextDay(1987, 2, 4), "(1987,2,5)");
	}
	
	@Test
	public void validateIncorrectNextDay() {
		assertNotEquals(entrega1.nextDay(1987, 2, 4), "(1987,2,7)");
	}

}
