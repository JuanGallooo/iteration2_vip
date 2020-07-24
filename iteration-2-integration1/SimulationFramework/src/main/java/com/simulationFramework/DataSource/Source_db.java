package com.simulationFramework.DataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simulationFramework.DataSource.Persistence.CalendarRepository;
import com.simulationFramework.DataSource.Persistence.PlanVersionRepository;
import com.simulationFramework.DataSource.Persistence.StopRepository;
import com.simulationFramework.SystemState.SITMFactory.SITMCalendar;
import com.simulationFramework.SystemState.SITMFactory.SITMPlanVersion;
import com.simulationFramework.SystemState.SITMFactory.SITMStop;


@Service
public class Source_db {
	
	private static final String dbURL = "jdbc:oracle:thin:@192.168.161.43:1521:liason";
	private static final String USER_NAME ="metrocali";
	private static final String USER_PASSWORD ="metrocali";
	
	private PlanVersionRepository planVersionRepository;
	
	private StopRepository stopRepository;
	
	private CalendarRepository calendarRepository;
	
	
	@Autowired
	public Source_db(PlanVersionRepository planVersionRepository, StopRepository stopRepository,
			CalendarRepository calendarRepository) {
		super();
		this.planVersionRepository = planVersionRepository;
		this.stopRepository = stopRepository;
		this.calendarRepository = calendarRepository;
	}

	public boolean jdbTestConnection() {
		try {
			Connection connection= DriverManager.getConnection(dbURL,USER_NAME,USER_PASSWORD);
			System.out.println("Connection Succesfull !!");
			connection.close();
			return true;
		} catch (Exception e) {
			System.out.println("Error connection DB!");
			e.printStackTrace();
			return false;
		}
	}
	
	public String[] getHeaders() {
		String[] headers= new String[26];
		headers[0]="OPERTRAVELID";
		headers[1]="BUSID";
		headers[2]="LASTSTOPID";
		headers[3]="GPS_X";
		headers[4]="GPS_Y";
		headers[5]="DEVIATIONTIME";
		headers[6]="ODOMETERVALUE";
		headers[7]="LINEID";
		headers[8]="TASKID";
		headers[9]="TRIPID";
		headers[10]="RIGHTCOURSE";
		headers[11]="ORIENTATION";
		headers[12]="EVENTDATE";
		headers[13]="EVENTTIME";
		headers[14]="REGISTERDATE";
		headers[15]="EVENTTYPEID";
		headers[16]="NEARESTSTOPID";
		headers[17]="LASTUPDATEDATE";
		headers[18]="NEARESTSTOPMTS";
		headers[19]="UPDNEARESTFLAG";
		headers[20]="LOGFILEID";
		headers[21]="NEARESTPLANSTOPID";
		headers[22]="NEARESTPLANSTOPMTS";
		headers[23]="PLANSTOPAUTH";
		headers[24]="RADIUSTOLERANCEMTS";
		headers[25]="TIMEDIFF";
		return headers;
	}

	public HashMap<String, String> fetchNextRow(Date initialDate, Date finalDate, long line,long planVerionID) {
		
		HashMap<String, String> data = new HashMap<String, String>();
		
		String[] headers = getHeaders();

		for (int i = 0; i < headers.length; i++) {
			data.put(headers[i], "");
			
		}
		return data;
	}
	
	public Iterable<SITMPlanVersion> findAllPlanVersions() {
		return planVersionRepository.findAll();
	}

	public Iterable<SITMStop> findAllStopsByLine(long planVersionID,long lineID) {
		//return stopRepository.findAllStopsbyLineID(planVersionID, lineID);
		return null;
	}

	public Iterable<SITMCalendar> findAllCalendarsByPlanVersion(long planVersionID) {
		//return calendarRepository.findAllCalendarsbyPlanVersionID(planVersionID);
		return null;
	}

}
