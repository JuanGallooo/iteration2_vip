package DataSource;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import SystemState.FactoryInterfaces.IStop;

public interface IDateSource {

	public String[] getHeaders();
	public HashMap<String, String> fetchNextRow(Date initialDate, Date finalDate, long line,long planVerionID) throws Exception;
	public boolean jdbTestConnection();
}
