package DateLibraryTest;

import DateLibrary.CustomDateChecker;
import DateLibrary.entrega1;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class entrega1Test {

	@Test
	public void validateCorrectNextDate() {
		assertEquals(entrega1.nextDay(1987, 2, 4), "(1987,2,5)");
	}
	
	@Test
	public void validateIncorrectNextDate() {
		assertNotEquals(entrega1.nextDay(1987, 2, 4), "(1987,2,7)");
	}
}
