import java.lang.Math; 
import java.util.stream.IntStream;
import static java.lang.System.*;

public class entrega1
{
  
  public static void main(String[] args)
  {
    out.println(dayOfWeek(2008,6,5));
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
	if(isValidDate(year,month,dayOfMonth)){
		int dayOfYear = dayOfMonth;
		
		for(int i = 1; i < month; i++)
		{
			dayOfYear = dayOfYear + daysByMonth(i,year);
		}
		
		return dayOfYear;
	}
	
	return 0;
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
	  
		if(IntStream.of(MONTH_WITH_31_DAYS).anyMatch(x -> x == month)){
			return 31;
		} else if(month == 2){
			return 28 + (isLeapYear(year) ? 1 : 0);
		} else {
			return 30;
		}
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
		
		if(year % NOT_DIVISIBLE_BY[0] == 0 ){
			return false;
		}
		if(	(year % DIVISIBLE_BY[0] == 0) || 
			(year % DIVISIBLE_BY[1] == 0)){
			return true;
		}
	  
	  return false;
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
		int MIMIMUM_YEAR = 1582;
		
		if(year < MIMIMUM_YEAR){
			return false;
		}
		if(month > 12 || month < 1) {
			return false;
		}
		
		if(daysByMonth(month,year) < day){
			return false;
		}
		
		return true;
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

	if(isValidDate(year,month,day)){
		if(isValidDate(year,month,day + ADD_DAY)){
			return "("+year+","+month+","+(day + ADD_DAY)+")";
		}else if(isValidDate(year,month + ADD_MONTH,ADD_DAY)){
            return "("+year+","+(month + ADD_MONTH)+","+ADD_DAY+")";
		}else {
            return "("+(year+ADD_YEAR)+","+ADD_MONTH+","+ADD_DAY+")";
		}
	}else {
		return "N/A";
	}
}

    /**
     * Metodo que determina el dia de la semana de una fecha determinada. El algoritmo usado se tomo de esta pagina http://stason.org/TULARC/society/calendars/index.html
     * @param year es el año que se va verificar.
     * @param month es el mes que se va verificar.
     * @param day es el dia que se va verificar.
     * @return el metodo devuelve el dia de la semana, de de una fecha determinada. si la fecha no es valida devuelve N/A
     */
    public static String dayOfWeek(int year,int month, int day){
        if(isValidDate(year,month,day)){
            int a,y,m,d;
            String[] DAYS_OF_WEEK = {"Domingo","Lunes","Martes","Miercoles","Jueves","Viernes","Sabado"};
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