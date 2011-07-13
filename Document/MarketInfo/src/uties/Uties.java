package uties;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Uties {
	
	public static String Now(String dateFormat) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(cal.getTime());
	}
		
	public static String NowGetTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("H:mm:ss:SSS");
        return sdf.format(cal.getTime());
	}
	
	public static String FormatIntegerNumber(String sNumber) {
		if(sNumber == null) {
			return "";
		}
		
		if(sNumber.isEmpty()) {
			return sNumber;
		}
		
		if(sNumber.equalsIgnoreCase("0")) {
			return "";
		}
		
		try {
			double number = Double.parseDouble(sNumber);
			NumberFormat usFormat = NumberFormat.getIntegerInstance(Locale.US);
			return usFormat.format(number);
		} catch(Exception e) {
			e.printStackTrace();
		}		 
		
		return "";
	}
 
 	public static boolean CheckInteger(String sNumber) {
 		try {
 			Integer.parseInt(sNumber);
 			return true;
 		} catch(Exception e) {
 			return false;
 		}
 	}
}
