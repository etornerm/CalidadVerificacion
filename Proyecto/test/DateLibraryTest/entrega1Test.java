package DateLibraryTest;

import DateLibrary.entrega1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import DateLibrary.CustomDateChecker;

public class entrega1Test {

	// El número que contiene cada método corresponde al número del caso de prueba de la tabla
	
	/** Set de pruebas para validar el día siguiente a una fecha dada**/
	@Test
	public void validateNextDay_1() {
		assertEquals(entrega1.nextDay(1582, 2, 12), "N/A");
	}
	
	@Test
	public void validateNextDay_2() {
		assertEquals(entrega1.nextDay(-2009, 12, 25), "N/A");
	}
	
	@Test
	public void validateNextDay_3() {
		assertEquals(entrega1.nextDay(1583, 1, 30), "(1583,1,31)");
	}
	
	@Test
	public void validateNextDay_4() {
		assertEquals(entrega1.nextDay(2015,5,31), "(2015,6,1)");
	}
	
	@Test
	public void validateNextDay_5() {
		assertEquals(entrega1.nextDay(1998,-3,4), "N/A");
	}
	
	@Test
	public void validateNextDay_6() {
		assertEquals(entrega1.nextDay(1776,0,13), "N/A");
	}
	
	@Test
	public void validateNextDay_7() {
		assertEquals(entrega1.nextDay(2003,1,17), "(2003,1,18)");
	}
	
	@Test
	public void validateNextDay_8() {
		assertEquals(entrega1.nextDay(1678,12,8), "(1678,12,9)");
	}
	
	@Test
	public void validateNextDay_9() {
		assertEquals(entrega1.nextDay(1991,4,-6), "N/A");
	}
	
	@Test
	public void validateNextDay_10() {
		assertEquals(entrega1.nextDay(2003,8,0), "N/A");
	}
	
	@Test
	public void validateNextDay_11() {
		assertEquals(entrega1.nextDay(1936,10,1), "(1936,10,2)");
	}
	
	@Test
	public void validateNextDay_12() {
		assertEquals(entrega1.nextDay(1852,3,31), "(1852,4,1)");
	}
	
	@Test
	public void validateNextDay_13() {
		assertEquals(entrega1.nextDay(1583, 12, 31), "(1584,1,1)");
	}
	
	/** Set de pruebas para validar el día del anno**/
	
	@Test
	public void validateDayOfYear_1() {
		assertEquals(entrega1.dayOfYear(1582, 2, 12), 0);
	}
	
	@Test
	public void validateDayOfYear_2() {
		assertEquals(entrega1.dayOfYear(-2009, 12, 25), 0);
	}
	
	@Test
	public void validateDayOfYear_3() {
		assertEquals(entrega1.dayOfYear(1583, 1, 30), 30);
	}
	
	@Test
	public void validateDayOfYear_4() {
		assertEquals(entrega1.dayOfYear(2015,5,31), 151);
	}
	
	@Test
	public void validateDayOfYear_5() {
		assertEquals(entrega1.dayOfYear(1998,-3,4), 0);
	}
	
	@Test
	public void validateDayOfYear_6() {
		assertEquals(entrega1.dayOfYear(1776,0,13), 0);
	}
	
	@Test
	public void validateDayOfYear_7() {
		assertEquals(entrega1.dayOfYear(2003,1,17), 17);
	}

	@Test
	public void validateDayOfYear_8() {
		assertEquals(entrega1.dayOfYear(1678,12,8), 342);
	}
	
	@Test
	public void validateDayOfYear_9() {
		assertEquals(entrega1.dayOfYear(1991,4,-6), 0);
	}
	
	@Test
	public void validateDayOfYear_10() {
		assertEquals(entrega1.dayOfYear(2003,8,0), 0);
	}
	
	@Test
	public void validateDayOfYear_11() {
		assertEquals(entrega1.dayOfYear(1936,10,1), 275);
	}
	
	@Test
	public void validateDayOfYear_12() {
		assertEquals(entrega1.dayOfYear(1852,3,31), 91);
	}
	
	@Test
	public void validateDayOfYear_13() {
		assertEquals(entrega1.dayOfYear(1583, 12, 31), 365);
	}
	
/**Casos de prueba para el método isLeapYear**/
	
	@Test
	public void isValidDate() {
		assertFalse(entrega1.isLeapYear(1582));
	}

	
	@Test
	public void validateIsLeapYear_2() {
		assertFalse(entrega1.isLeapYear(-2009));
	}
	
	@Test
	public void validateIsLeapYear_3() {
		assertFalse(entrega1.isLeapYear(1583));
	}
	
	@Test
	public void validateIsLeapYear_4() {
		assertTrue(entrega1.isLeapYear(1852));
	}
	
	/**Casos de prueba para el método dayOfWeek**/

	

}
