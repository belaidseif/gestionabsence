package tekup.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Week {
	private static List<LocalDate> weeks = new ArrayList<LocalDate>();
	
	public Week() {
		
	}
	public static List<LocalDate> getWeeks() {	
		setWeeks();
		return weeks;
	}

	public static  void setWeeks() {
		for(int i=0; i<14;i++)
			weeks.add(LocalDate.of(2019, 9, 9).plusDays(7*i));
	}
	
}
