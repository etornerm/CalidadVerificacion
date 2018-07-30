package DateLibrary;

import java.util.stream.IntStream;
import static java.lang.System.*;

public class entrega1
{
  
  public static void main(String[] args)
  {
	  out.println(dayOfYear(1853,12,31));
	  out.println(isLeapYear(1700));
	  out.println(isValidDate(1992,5,13));
	  out.println(dayOfWeek(1853,12,31));
	  out.println(nextDay(1852,3,31));
	  
	  int [] fecha = new int[] {1956,12,15};
	  out.println(futureDate(fecha, 80));
	  out.println(dayBetweenDates(new int[] {2009, 1, 1}, new int[] {2009, 1, 1}));
  }

/**
 * Metodo que devuelve el dia del año que refresenta una fecha dada.
 * @param month es el mes de la fecha que se desea calcular
 * @param dayOfMonth es el dia del mes de la fecha que se desea calcular
 * @param year es el año de la fecha que se desea calcular
 * @return el metodo devuelve el numero de dia de ese año, si la fecha no fuera valida devuelve 0.
 * por ejemplo, dayOfYear(2,29,2008)=60.
 */
public static int dayOfYear(int year,int month, int dayOfMonth){
	int dayOfYear = 0;
	
	if(isValidDate(year,month,dayOfMonth)){
		dayOfYear = dayOfMonth;
		
		for(int i = 1; i < month; i++)
		{
			dayOfYear = dayOfYear + daysByMonth(i,year);
		}
		
	}
	
	return dayOfYear;
}

/**
 * Metodo que devuelve los dias del mes
 * @param month es el mes del cual se tiene que obtener los dias del mes.
 * @param year es el año, que se utiliza para identificar si el año es bisiesto.
 * @return el metodo devuelve el total de dias de ese mes.
 * por ejemplo, daysByMonth(2,1700)=28.
 */
	public static int daysByMonth(int month,int year){
		int[] MONTH_WITH_31_DAYS = {1,3,5,7,8,10,12};
		int MONTH_DAYS_31 = 31;
		int MONTH_DAYS_28 = 28;
		int MONTH_DAYS_30 = 30;
		
		int currentMonthDays =  0;
	  
		if(IntStream.of(MONTH_WITH_31_DAYS).anyMatch(x -> x == month)){
			currentMonthDays =  MONTH_DAYS_31;
		} else if(month == 2){
			currentMonthDays =  MONTH_DAYS_28 + (isLeapYear(year) ? 1 : 0);
		} else {
			 currentMonthDays = MONTH_DAYS_30;
		}
		
		return currentMonthDays;
	}

/**
 * Metodo que permite calcular si un año es bisiesto o no.
 * @param year es el año que se va verificar.(se asume que el año es mayor o igual a 1582)
 * @return el metodo devuelve un valor booleano que verifica si es o no bisiesto.
 * Por ejemplo, isLeapYear(1700)=false.
 */
	public static boolean isLeapYear(int year){
		int[] DIVISIBLE_BY = {400,4};
		int[] NOT_DIVISIBLE_BY = {100};
		
		Boolean leapYear = false;
		
		if(year % DIVISIBLE_BY[0] == 0 || (year % DIVISIBLE_BY[1] == 0 && year % NOT_DIVISIBLE_BY[0] != 0)){
			leapYear =  true;
		}

	    return leapYear;
	}

/**
 * Metodo que verifica si una fecha dada es correcta.
 * @param year es el año que se va verificar.
 * @param month es el mes que se va verificar.
 * @param day es el dia que se va verificar.
 * @return el metodo devuelve un valor booleano que verifica si la fecha es valida.
 * Por ejemplo, isValidDate(1,20,1890)=true.
 */
	public static boolean isValidDate(int year,int month, int day){
		int MIMIMUM_YEAR = 1583;
		Boolean dateValid = true;
		
		if(year < MIMIMUM_YEAR){
			dateValid =  false;
		}
		if(month > 12 || month < 1) {
			dateValid =  false;
		}
		
		if( day <= 0 || daysByMonth(month,year) < day){
			dateValid =  false;
		}
		
		return dateValid;
	}

/**
 * Metodo que determina la fecha del dia siguiente
 * @param year es el año que se va verificar.
 * @param month es el mes que se va verificar.
 * @param day es el dia que se va verificar.
 * @return el metodo devuelve una fecha en tuplas de tres números enteros positivos (ternas),
 * en este orden: (año, mes, día), si la fecha no es valida devuelve N/A.
 * Por ejemplo, nextDay(1890,12,31)=(1891,1,1).
 */
public static String nextDay(int year,int month, int day){
	int ADD_DAY = 1;
	int ADD_MONTH = 1;
	int ADD_YEAR = 1;
	String nextDayInfo = "";

	if(isValidDate(year,month,day)){
		if(isValidDate(year,month,day + ADD_DAY)){
			nextDayInfo =  "("+year+","+month+","+(day + ADD_DAY)+")";
		}else if(isValidDate(year,month + ADD_MONTH,ADD_DAY)){
			nextDayInfo =  "("+year+","+(month + ADD_MONTH)+","+ADD_DAY+")";
		}else {
			nextDayInfo =  "("+(year+ADD_YEAR)+","+ADD_MONTH+","+ADD_DAY+")";
		}
	}else {
		nextDayInfo = "N/A";
	}
	
	return nextDayInfo;
}

    /**
     * Metodo que determina el dia de la semana de una fecha determinada. El algoritmo usado se tomo de esta pagina http://stason.org/TULARC/society/calendars/index.html
     * @param year es el año que se va verificar.
     * @param month es el mes que se va verificar.
     * @param day es el dia que se va verificar.
     * @return el metodo devuelve el dia de la semana, de de una fecha determinada. si la fecha no es valida devuelve N/A
     */
    public static String dayOfWeek(int year,int month, int day){
    	
    		String dayOfWeekInfo = "";
    		
        if(isValidDate(year,month,day)){
            int a,countOfYears,countOfMonths,countOfDays;
            String[] DAYS_OF_WEEK = {"Domingo","Lunes","Martes","Miercoles","Jueves","Viernes","Sabado"};
            a = (14 - month) / 12;
            countOfYears = year - a;
            countOfMonths = month + 12*a - 2;
            countOfDays = (day + countOfYears + countOfYears/4 - countOfYears/100 + countOfYears/400 + (31*countOfMonths)/12) % 7;
            dayOfWeekInfo =  DAYS_OF_WEEK[countOfDays];
        }else {
        		dayOfWeekInfo =  "N/A";
        }
        
        return dayOfWeekInfo;
    }

	/**
	 * Metodo que determina la fecha que será en N días en el futuro
	 * @param currentDate arreglo que contiene 3 numeros enteros (year, month, day).
	 * @param futureDays es el número de días que se sumará a la fecha dada (es un número entero no negativo).
	 * @return fecha que será en los días dados a futuro
	 */
	public static String futureDate(int[]currentDate, int futureDays){
		int year = currentDate[0];
		int month = currentDate[1];
		int day = currentDate[2];
		int sumOfDays = futureDays + day;
		
		String futureDateMessage = "";
		
		if(futureDays > 0 && isValidDate(year, month, day)) {
			futureDateMessage = getFutureDate(year, month, sumOfDays);
		}else {
			futureDateMessage = "N/A";
		}
		return  futureDateMessage;
	}
	
	/**
	 * Metodo que valida y agrega dias a la fecha deseada
	 * @param year es el año que se va verificar.
     * @param month es el mes que se va verificar.
     * @param day es el dia que se va verificar. (ya contiene los días que se desean sumar a la fecha)
	 * @return fecha que será en los días dados a futuro
	 */

	private static String getFutureDate(int year, int month, int day){
		int MONTH_NUMBER = 12;
		
		if(day > daysByMonth(month,year) ){
			day -= daysByMonth(month,year);
			month += 1;
			if (month > MONTH_NUMBER){
				month = 1;
				year += 1;
			}
		}else{
			if (day <= daysByMonth(month, year)){
				return "("+year+","+month+","+day+")";
			}
		}

		return getFutureDate(year, month, day);
	}

	/**
	 * Metodo que devuelve la fecha n dias antes a la dada
	 * @param date fecha de referencia en orden: año, mes y dia.
	 * @param offset dias de diferencia.
	 * @return fecha en el pasado
	 */
	public static int[] getPastDate(int[] date, int offset) throws Exception {
		if (!isValidDate(date[0], date[1], date[2]) || offset < 0 || date.length != 3)
			throw new Exception();
		if (offset >= date[2]){
			recalculateAndRecallPastDate(date, offset);
		} else {
			date[2] -= offset;
		}
		return date;
	}

	private static void recalculateAndRecallPastDate(int[] date, int offset) throws Exception {
		offset = Math.abs(offset -= date[2]);
		date[1]--;
		if (date[1] == 0) {
			date[0]--;
			date[1] = 12;
		}
		date[2] = daysByMonth(date[1], date[0]);
		getPastDate(date, offset);
	}
    /**
     * Metodo que valida y verificar la diferencia de dias entre dos fechas.
     * @param dateOne arreglo que contiene 3 numeros enteros (year, month, day).
     * @param dateTwo arreglo que contiene 3 numeros enteros (year, month, day).
     * @return numero entero de la diferencia en dias de las dos fechas dadas, si alguna fecha fuera invalida, se retorna -1.
     */

    public static int dayBetweenDates(int[]dateOne, int[]dateTwo){
        int yearOne = dateOne[0];
        int monthOne = dateOne[1];
        int dayOne = dateOne[2];

        int yearTwo = dateTwo[0];
        int monthTwo = dateTwo[1];
        int dayTwo = dateTwo[2];

        int different;

        if(!isValidDate(yearOne, monthOne, dayOne) || !isValidDate(yearTwo, monthTwo, dayTwo)){
            return -1;
        }

        if(yearOne == yearTwo && monthOne == monthTwo && dayOne == dayTwo){
            return 0;
        }

        different = getDaysFromDate(yearOne,monthOne,dayOne) - getDaysFromDate(yearTwo,monthTwo,dayTwo);

        if(different < 0){
            different = different * -1;
        }

        return different;
    }


    /**
     * Metodo que devuelve la cantidad de days de una fecha dada,formula tomada de https://alcor.concordia.ca/~gpkatch/gdate-algorithm.html.
     * @param year es el año que se va verificar.
     * @param month es el mes que se va verificar.
     * @param day es el dia que se va verificar.
     * @return número de días transcurridos de la fecha dada.
     */
    private static int getDaysFromDate(int year, int month, int day){
        int m = (month + 9) % 12;
        int y = year - m/10;
        int d = day;
        return 365*y + y/4 - y/100 + y/400 + (m*306 + 5)/10 + ( d - 1 );
    }
}