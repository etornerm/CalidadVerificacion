package DateLibraryTest;

import DateLibrary.entrega1;

import org.junit.Assert;
import org.junit.Test;

import DateLibrary.entrega1;

import static org.junit.Assert.*;

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
    public void validateIsLeapYear_1() {
        Assert.assertFalse(entrega1.isLeapYear(1582));
    }
	
	 @Test
    public void validateIsLeapYear_2() {
        Assert.assertFalse(entrega1.isLeapYear(-1500));
    }

	@Test
	public void validateIsLeapYear_3() {
		assertFalse(entrega1.isLeapYear(1583));
	}
	
	@Test
    public void validateIsLeapYear_4() {
		assertTrue(entrega1.isLeapYear(1852));
    }
	
    @Test
    public void validateIsLeapYear_5() {
        Assert.assertFalse(entrega1.isLeapYear(1900));
    }

    @Test
    public void validateIsLeapYear_6() {
        Assert.assertFalse(entrega1.isLeapYear(1999));
    }

    @Test
    public void validateIsLeapYear_7() {
        Assert.assertTrue(entrega1.isLeapYear(2000));
        Assert.assertTrue(entrega1.isLeapYear(2004));
    }
 
	/**Casos de prueba para el método dayOfWeek**/

	@Test
	public void validateDayOfWeek_1() {
		assertEquals(entrega1.dayOfWeek(1582, 2, 12), "N/A");
	}
	
	@Test
	public void validateDayOfWeek_2() {
		assertEquals(entrega1.dayOfWeek(-2009, 12, 25), "N/A");
	}
	
	@Test
	public void validateDayOfWeek_3() {
		assertEquals(entrega1.dayOfWeek(1583, 1, 30), "Domingo");
	}
	
	@Test
	public void validateDayOfWeek_4() {
		assertEquals(entrega1.dayOfWeek(2015,5,31), "Domingo");
	}
	
	@Test
	public void validateDayOfWeek_5() {
		assertEquals(entrega1.dayOfWeek(1998,-3,4), "N/A");
	}
	
	@Test
	public void validateDayOfWeek_6() {
		assertEquals(entrega1.dayOfWeek(1776,0,13), "N/A");
	}
	
	@Test
	public void validateDayOfWeek_7() {
		assertEquals(entrega1.dayOfWeek(2003,1,17), "Viernes");
	}

	@Test
	public void validateDayOfWeek_8() {
		assertEquals(entrega1.dayOfWeek(1678,12,8), "Jueves");
	}
	
	@Test
	public void validateDayOfWeek_9() {
		assertEquals(entrega1.dayOfWeek(1991,4,-6), "N/A");
	}
	
	@Test
	public void validateDayOfWeek_10() {
		assertEquals(entrega1.dayOfWeek(2003,8,0), "N/A");
	}
	
	@Test
	public void validateDayOfWeek_11() {
		assertEquals(entrega1.dayOfWeek(1936,10,1), "Jueves");
	}
	
	@Test
	public void validateDayOfWeek_12() {
		assertEquals(entrega1.dayOfWeek(1852,3,31), "Miercoles");
	}
	
	@Test
	public void validateDayOfWeek_13() {
		assertEquals(entrega1.dayOfWeek(1583, 12, 31), "Sabado");
	}
	
	/**Casos de prueba para los métodos encargados de obtener la fecha futura**/
	
	@Test(expected = Exception.class)
	public void validatePastDate_1() throws Exception {
		int [] date = new int[] {1511, 1, 13};
		entrega1.getPastDate(date, 14);
	}

	@Test(expected = Exception.class)
	public void validatePastDate_2() throws Exception{
		int [] date = new int[] {1900, 1, 13};
		entrega1.getPastDate(date, 0);
	}

	@Test(expected = Exception.class)
	public void validatePastDate_3() throws Exception{
		int [] date = new int[] {1900, 1, 13};
		entrega1.getPastDate(date, -1);
	}

	@Test(expected = Exception.class)
	public void validatePastDate_4() throws Exception{
		int [] date = new int[] {1900, 1, 13, 12};
		entrega1.getPastDate(date, 1);
	}

	@Test
	public void validatePastDate_5() throws Exception{
		int [] date = new int[] {2018, 1, 13};
		int [] expected = new int [] {2018, 1, 10};
		assertArrayEquals(expected, entrega1.getPastDate(date, 3));
	}

	@Test
	public void validatePastDate_6() throws Exception{
		int [] date = new int[] {2018, 9, 12};
		int [] expected = new int [] {2018, 8, 31};
		assertArrayEquals(expected, entrega1.getPastDate(date, 12));
	}

	@Test
	public void validatePastDate_7() throws Exception{
		int [] date = new int[] {2018, 9, 12};
		int [] expected = new int [] {2018, 7, 31};
		assertArrayEquals(expected, entrega1.getPastDate(date, 43));
	}

	@Test
	public void validatePastDate_8() throws Exception{
		int [] date = new int[] {2018, 1, 15};
		int [] expected = new int [] {2018, 1, 15};
		assertArrayEquals(expected, entrega1.getPastDate(date, 0));
	}

	@Test
	public void validatePastDate_9() throws Exception{
		int [] date = new int[] {2018, 1, 1};
		int [] expected = new int [] {2017, 12, 31};
		assertArrayEquals(expected, entrega1.getPastDate(date, 1));
	}

	/**Casos de prueba para los métodos encargados de obtener la cantidad de dias entre dos fechas**/

	@Test
	public void validDayBetweenDates_1() {
		int [] dateOne = new int[] {-2005, 12, 31};
		int [] dateTwo = new int[] {2009, 1, 1};
		int a = entrega1.dayBetweenDates(dateOne,dateTwo);
		Assert.assertEquals(-1, a);
	}
	@Test
	public void validDayBetweenDates_2() {
		int [] dateOne = new int[] {1400, 11, 25};
		int [] dateTwo = new int[] {2009, 1, 1};
		int a = entrega1.dayBetweenDates(dateOne,dateTwo);
		Assert.assertEquals(-1, a);
	}
	@Test
	public void validDayBetweenDates_3() {
		int [] dateOne = new int[] {2018, 2, 40};
		int [] dateTwo = new int[] {2018, 2, 30};
		int a = entrega1.dayBetweenDates(dateOne,dateTwo);
		Assert.assertEquals(-1, a);
	}
	@Test
	public void validDayBetweenDates_4() {
		int [] dateOne = new int[] {2018, 4, 1};
		int [] dateTwo = new int[] {2018, 4, 1};
		int a = entrega1.dayBetweenDates(dateOne,dateTwo);
		Assert.assertEquals(0, a);
	}
	@Test
	public void validDayBetweenDates_5() {
		int [] dateOne = new int[] {2018, 2, 14};
		int [] dateTwo = new int[] {2018, 6, 1};
		int a = entrega1.dayBetweenDates(dateOne,dateTwo);
		Assert.assertEquals(107, a);
	}
	@Test
	public void validDayBetweenDates_6() {
		int [] dateOne = new int[] {2018, 12, 31};
		int [] dateTwo = new int[] {2018, 1, 1};
		int a = entrega1.dayBetweenDates(dateOne,dateTwo);
		Assert.assertEquals(364, a);
	}
}
