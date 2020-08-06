package com.simulationFramework.DataSource;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simulationFramework.DataSource.Persistence.CalendarRepository;
import com.simulationFramework.DataSource.Persistence.LineRepository;
import com.simulationFramework.DataSource.Persistence.OperationalTravelsRepository;
import com.simulationFramework.DataSource.Persistence.PlanVersionRepository;
import com.simulationFramework.DataSource.Persistence.StopRepository;
import com.simulationFramework.SystemState.SITMFactory.SITMCalendar;
import com.simulationFramework.SystemState.SITMFactory.SITMLine;
import com.simulationFramework.SystemState.SITMFactory.SITMOperationalTravels;
import com.simulationFramework.SystemState.SITMFactory.SITMPlanVersion;
import com.simulationFramework.SystemState.SITMFactory.SITMStop;

@Service
public class Source_db implements IDateSource {

	private static final String dbURL = "jdbc:oracle:thin:@192.168.161.43:1521:liason";
	private static final String USER_NAME = "metrocali";
	private static final String USER_PASSWORD = "metrocali";

	@Autowired
	private PlanVersionRepository planVersionRepository;

	@Autowired
	private StopRepository stopRepository;

	@Autowired
	private LineRepository lineRepository;

	@Autowired
	private CalendarRepository calendarRepository;
	
	@Autowired
	private OperationalTravelsRepository operationalTravelsRepository;
	
	public Source_db() {
		super();
	}

	public boolean jdbTestConnection() {
		try {
			Connection connection = DriverManager.getConnection(dbURL, USER_NAME, USER_PASSWORD);
			System.out.println("Connection Succesfull !!");
			connection.close();
			return true;
		} catch (Exception e) {
			System.out.println("Error connection DB!");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public String[] getHeaders() {
		
		String[] headers = new String[10];
		headers[0] = "opertravelID";
		headers[1] = "busID";
		headers[2] = "laststopID";
		headers[3] = "GPS_X";
		headers[4] = "GPS_Y";
		headers[5] = "odometervalue";
		headers[6] = "lineID";
		headers[7] = "taskID";
		headers[8] = "tripID";
		headers[9] = "eventDate";
		
//		String[] headers = new String[26];
//		headers[0] = "OPERTRAVELID";
//		headers[1] = "BUSID";
//		headers[2] = "LASTSTOPID";
//		headers[3] = "GPS_X";
//		headers[4] = "GPS_Y";
//		headers[5] = "DEVIATIONTIME";
//		headers[6] = "ODOMETERVALUE";
//		headers[7] = "LINEID";
//		headers[8] = "TASKID";
//		headers[9] = "TRIPID";
//		headers[10] = "RIGHTCOURSE";
//		headers[11] = "ORIENTATION";
//		headers[12] = "EVENTDATE";
//		headers[13] = "EVENTTIME";
//		headers[14] = "REGISTERDATE";
//		headers[15] = "EVENTTYPEID";
//		headers[16] = "NEARESTSTOPID";
//		headers[17] = "LASTUPDATEDATE";
//		headers[18] = "NEARESTSTOPMTS";
//		headers[19] = "UPDNEARESTFLAG";
//		headers[20] = "LOGFILEID";
//		headers[21] = "NEARESTPLANSTOPID";
//		headers[22] = "NEARESTPLANSTOPMTS";
//		headers[23] = "PLANSTOPAUTH";
//		headers[24] = "RADIUSTOLERANCEMTS";
//		headers[25] = "TIMEDIFF";
		return headers;
	}

	@Override
	public HashMap<String, String> fetchNextRow(Date initialDate, Date finalDate, long line, long planVerionID) {

		HashMap<String, String> data = new HashMap<String, String>();

		String[] headers = getHeaders();

		for (int i = 0; i < headers.length; i++) {
			data.put(headers[i], "");

		}
		return data;
	}

	@Override
	public ArrayList<SITMPlanVersion> findAllPlanVersions() {

		ArrayList<SITMPlanVersion> returnAnswer = new ArrayList<SITMPlanVersion>();

		for (SITMPlanVersion element : planVersionRepository.findAll()) {
			returnAnswer.add(element);
		}

		return returnAnswer;
	}

	@Override
	public ArrayList<SITMCalendar> findAllCalendarsByPlanVersion(long planVersionID) {

		ArrayList<SITMCalendar> returnAnswer = new ArrayList<SITMCalendar>();

		for (SITMCalendar element : calendarRepository.findAllCalendarsbyPlanVersionID(planVersionID)) {
			returnAnswer.add(element);
		}

		return returnAnswer;
	}

	@Override
	public ArrayList<SITMLine> findAllLinesByPlanVersion(long planVersionID) {

		ArrayList<SITMLine> returnAnswer = new ArrayList<SITMLine>();

		for (SITMLine element : lineRepository.findAllLinesbyPlanVersionID(planVersionID)) {
			returnAnswer.add(element);
		}

		return returnAnswer;
	}

	@Override
	public ArrayList<SITMStop> findAllStopsByLine(long planVersionID, long lineID) {

		ArrayList<SITMStop> returnAnswer = new ArrayList<SITMStop>();

		for (SITMStop element : stopRepository.findAllStopsbyLineID(planVersionID, lineID)) {
			returnAnswer.add(element);
		}

		return returnAnswer;
	}

	@Override
	public ArrayList<SITMOperationalTravels> findAllOperationalTravelsByRange(Date initialDate, Date lastDate, long lineID){
		
		ArrayList<SITMOperationalTravels> returnAnswer = new ArrayList<SITMOperationalTravels>();

		for (SITMOperationalTravels element : operationalTravelsRepository.findAllOP(initialDate.toString(), lastDate.toString())) {
			returnAnswer.add(element);
		}

		return returnAnswer;
	}

}
