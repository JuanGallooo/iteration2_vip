package com.simulationFramework.DataSource;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

		return headers;
	}
	
	public HashMap<String,String> getLastRow(){
		return null;
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
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
		DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
		
		if(initialDate.toString().equals(lastDate.toString())) {
			
			
			String date = dateFormat.format(initialDate);

			try {
				
				long startHour = (hourFormat.parse(hourFormat.format(initialDate)).getTime()-18000000)/1000;
				System.out.println(startHour);
				
				long endHour = (hourFormat.parse(hourFormat.format(lastDate)).getTime()-18000000)/1000;
				System.out.println(endHour);
				
				System.out.println(date);
			
				for (SITMOperationalTravels element : operationalTravelsRepository.findAllOPDay(date, startHour, endHour)) {
					returnAnswer.add(element);
				}	

			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			
		}else {
			for (SITMOperationalTravels element : operationalTravelsRepository.findAllOP("01/08/19","01/08/19")) {
				returnAnswer.add(element);
			}
		}
		

		return returnAnswer;
	}

}
