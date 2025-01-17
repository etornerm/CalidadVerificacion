package DateLibraryTest;

import DateLibrary.CustomDateChecker;
import DateLibrary.entrega1;
import org.junit.Assert;
import org.junit.Test;

public class TestingPart1 {

    @Test
    public void validDate() {
        boolean a = CustomDateChecker.isDateValid(1988, 9, 12);
        Assert.assertTrue(a);
    }

    @Test
    public void invalidYear() {
        boolean a = CustomDateChecker.isYearValid(1581);
        Assert.assertFalse(a);
    }

    @Test
    public void invalidYearZero() {
        boolean a = CustomDateChecker.isYearValid(0);
        Assert.assertFalse(a);
    }

    @Test
    public void invalidYearNegative() {
        boolean a = CustomDateChecker.isYearValid(-1584);
        Assert.assertFalse(a);
    }

    @Test
    public void invalidMonth() {
        boolean a = CustomDateChecker.isMonthValid(13);
        Assert.assertFalse(a);
    }

    @Test
    public void invalidMonthZero() {
        boolean a = CustomDateChecker.isMonthValid(0);
        Assert.assertFalse(a);
    }

    @Test
    public void invalidMonthNegative() {
        boolean a = CustomDateChecker.isMonthValid(-10);
        Assert.assertFalse(a);
    }

    @Test
    public void invalidDay() {
        boolean a = CustomDateChecker.isDayValid(1999, 12, 32);
        Assert.assertFalse(a);
    }

    @Test
    public void invalidDayNegative() {
        boolean a = CustomDateChecker.isDayValid(1999, 12, -15);
        Assert.assertFalse(a);
    }

    @Test
    public void invalidDayWrongMonth() {
        boolean a = CustomDateChecker.isDayValid(1999, 9, 31);
        Assert.assertFalse(a);
    }

    @Test
    public void invalidDayNotLeapYear() {
        boolean a = CustomDateChecker.isDayValid(1999, 2, 29);
        Assert.assertFalse(a);
    }

    @Test
    public void validLeapYear() {
        boolean a = CustomDateChecker.isLeapYear(2000);
        boolean b = CustomDateChecker.isLeapYear(2004);
        Assert.assertTrue(a && b);
    }

    @Test
    public void invalidLeapYearFirstConditionNotMet() {
        boolean a = CustomDateChecker.isLeapYear(1999);
        Assert.assertFalse(a);
    }

    @Test
    public void invalidLeapYearSecondConditionMetNotThird() {
        boolean a = CustomDateChecker.isLeapYear(1900);
        Assert.assertFalse(a);
    }

    @Test
    public void invalidLeapYearNegative() {
        boolean a = CustomDateChecker.isLeapYear(-1500);
        Assert.assertFalse(a);
    }

    @Test
    public void invalidLeapYear1582() {
        boolean a = CustomDateChecker.isLeapYear(1582);
        Assert.assertFalse(a);
    }

    @Test
    public void validNextDay() {
        String a = CustomDateChecker.getNextDay(1988, 9, 12);
        Assert.assertEquals("(1988,9,13)", a);
    }

    @Test
    public void validNextDayChangeMonth(){
        String a = CustomDateChecker.getNextDay(1988, 11, 30);
        Assert.assertEquals("(1988,12,1)", a);
    }

    @Test
    public void validNextDayChangeYear(){
        String a = CustomDateChecker.getNextDay(1988, 12, 31);
        Assert.assertEquals("(1989,1,1)", a);
    }

    @Test
    public void invalidNextDayInvalidDate() {
        String a = CustomDateChecker.getNextDay(1500, 13, 32);
        Assert.assertEquals("invalid date", a);
    }

    @Test
    public void validDayBetweenDates() {
        int [] dateOne = new int[] {2008, 12, 31};
        int [] dateTwo = new int[] {2009, 1, 1};
        int a = entrega1.dayBetweenDates(dateOne,dateTwo);
        Assert.assertEquals(1, a);
    }

    @Test
    public void validDayBetweenDatesSameDate() {
        int [] dateOne = new int[] {2014, 6, 12};
        int [] dateTwo = new int[] {2014, 6, 12};
        int a = entrega1.dayBetweenDates(dateOne,dateTwo);
        Assert.assertEquals(0, a);
    }

    @Test
    public void invalidDayBetweenDatesInvalidDate() {
        int [] dateOne = new int[] {1540, 15, 12};
        int [] dateTwo = new int[] {1520, 10, 10};
        int a = entrega1.dayBetweenDates(dateOne,dateTwo);
        Assert.assertEquals(-1, a);
    }

}
