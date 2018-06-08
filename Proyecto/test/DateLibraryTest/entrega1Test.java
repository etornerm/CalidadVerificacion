package DateLibraryTest;

import DateLibrary.entrega1;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class entrega1Test {

	// El número que contiene cada método corresponde al número del caso de prueba de la tabla
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

}
