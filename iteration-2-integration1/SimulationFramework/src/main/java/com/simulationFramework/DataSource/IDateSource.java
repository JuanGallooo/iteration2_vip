package com.simulationFramework.DataSource;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.simulationFramework.SystemState.SITMFactory.SITMCalendar;
import com.simulationFramework.SystemState.SITMFactory.SITMLine;
import com.simulationFramework.SystemState.SITMFactory.SITMPlanVersion;
import com.simulationFramework.SystemState.SITMFactory.SITMStop;

public interface IDateSource {

	public String[] getHeaders();
	public HashMap<String, String> fetchNextRow(Date initialDate, Date finalDate, long line,long planVerionID) throws Exception;
	
	public ArrayList<SITMPlanVersion> findAllPlanVersions() ;
	public ArrayList<SITMCalendar> findAllCalendarsByPlanVersion(long planVersionID);
	public ArrayList<SITMLine> findAllLinesByPlanVersion(long planVersionID);
	public ArrayList<SITMStop> findAllStopsByLine(long planVersionID,long lineID);
	
}
