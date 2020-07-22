package DataSource;

import java.util.Date;
import java.util.HashMap;

public interface IDateSource {

	public String[] getHeaders();
	public HashMap<String, String> fetchNextRow(Date initialDate, Date finalDate, long line,long planVerionID) throws Exception;
	
}
