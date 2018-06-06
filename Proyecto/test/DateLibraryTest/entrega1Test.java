package DateLibraryTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.jupiter.api.Test;

import DateLibrary.entrega1;

class entrega1Test {

	@Test
	void validateCorrectNextDate() {
		assertEquals(entrega1.nextDay(1987, 2, 4), "(1987,2,5)");
	}
	
	@Test
	void validateIncorrectNextDate() {
		assertNotEquals(entrega1.nextDay(1987, 2, 4), "(1987,2,7)");
	}

}
