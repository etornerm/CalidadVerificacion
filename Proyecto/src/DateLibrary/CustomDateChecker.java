package DateLibrary;

import java.util.stream.IntStream;

public class CustomDateChecker {

    public static boolean isDateValid(int year, int month, int day) {
        return (isYearValid(year) && isMonthValid(month) && isDayValid(year, month, day));
    }

    public static boolean isYearValid(int year) {
        int MINIMUM_YEAR = 1582;
        return year >= MINIMUM_YEAR;
    }

    public static boolean isMonthValid(int month) {
        return month >= 1 && month <= 12;
    }

    public static boolean isDayValid(int year, int month, int day) {
        return day >= 1 && day <= daysByMonth(year, month);
    }

    private static int daysByMonth(int year, int month) {
        int[] MONTHS_WITH_31_DAYS = {1, 3, 5, 7, 8, 10, 12};
        if(IntStream.of(MONTHS_WITH_31_DAYS).anyMatch(x -> x == month))
            return 31;
        else if(month == 2)
            return isLeapYear(year) ? 29 : 28;
        else
            return 30;
    }

    public static boolean isLeapYear(int year) {
        boolean condition1 = yearIsDivisibleBy(4, year);
        boolean condition2 = yearIsDivisibleBy(100, year);
        boolean condition3 = yearIsDivisibleBy(400, year);
        return isYearValid(year) && ((condition1 && !condition2)||(condition2 && condition3));
    }

    private static boolean yearIsDivisibleBy(int divisor, int year) {
        return year % divisor == 0;
    }

    public static String getNextDay(int year,int month, int day){
        if(!isDateValid(year, month, day))
            return "invalid date";
        day = increaseDayByOne(day, month, year);
        if(day == 1){
            month = increaseMonthByOne(month);
            if(month == 1)
                year = increaseYearByOne(year);
        }
        return "("+year+","+month+","+ day +")";
    }

    private static int increaseDayByOne(int day, int month, int year) {
        int DAYS_IN_MONTH = daysByMonth(year, month);
        return day < DAYS_IN_MONTH ? day+1 : 1;
    }

    private static int increaseMonthByOne(int month) {
        return month < 12 ? month+1 : 1;
    }

    private static int increaseYearByOne(int year) {
        return year+1;
    }

    public static String dayOfWeek(int year,int month, int day){
        String[] DAYS_OF_WEEK = {"Domingo","Lunes","Martes","Miercoles","Jueves","Viernes","Sabado"};
        if(isDateValid(year,month,day)){
            int a,y,m,d;

            a = (14 - month) / 12;
            y = year - a;
            m = month + 12*a - 2;
            d = (day + y + y/4 - y/100 + y/400 + (31*m)/12) % 7;
            return DAYS_OF_WEEK[d];
        }else {
            return "N/A";
        }
    }

}
