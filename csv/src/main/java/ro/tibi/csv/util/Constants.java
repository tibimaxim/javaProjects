package ro.tibi.csv.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class Constants {
	
	public enum AccountStatus{
		REQESTED,ONLINE,BLOCKED
	}
	
	public enum Sex{
		M,F
	}
	
	public static DateFormat CI_DATEFORMAT = new SimpleDateFormat("yyMMdd");
	
	
	
}
