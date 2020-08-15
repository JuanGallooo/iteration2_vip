package com.simulationFramework;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.simulationFramework.Simulation.SimController;
import com.simulationFramework.Simulation.Event.Event;
import com.simulationFramework.Simulation.Event.EventType;
import com.simulationFramework.SystemState.SITMFactory.SITMCalendar;
import com.simulationFramework.SystemState.SITMFactory.SITMLine;
import com.simulationFramework.SystemState.SITMFactory.SITMPlanVersion;
import com.simulationFramework.SystemState.SITMFactory.SITMStop;

public class simulationMain {

	public static void main(String[] args) throws IOException, ParseException {
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		dataTest();
		startTest();
		reader.readLine();
	}
	
	public static <T> void dataTest(){
		
		SimController sm = new SimController(null);
		sm.initialize_SCV(new File(""), ",");
		
		System.out.println("plan Versions ========================================================================================================================================\n");
		ArrayList<SITMPlanVersion> planversions = sm.getPlanVersions();
		for (int i = 0; i < planversions.size(); i++) {System.out.println(planversions.get(i));}
		sm.setPlanVersionID(261);
		System.out.println();
		
		
		System.out.println("calendars ========================================================================================================================================\n");
		ArrayList<SITMCalendar> calendars = sm.getDateByPlanVersion(261);
		for (int i = 0; i < calendars.size(); i++) {System.out.println(calendars.get(i));}
		sm.setDates(calendars.get(0).getOperationDay(), calendars.get(calendars.size()-1).getOperationDay());
		System.out.println();
		
		
		System.out.println("lines =========================================================================================================================================\n");
		ArrayList<SITMLine> lines = sm.getLinesByPlanVersion();
		for (int i = 0; i < lines.size(); i++) {System.out.println(lines.get(i));}
		sm.setLineId(131);
		System.out.println();
		
		
		System.out.println("Stops ========================================================================================================================================\n");
		ArrayList<SITMStop> stops = sm.getDataSource().findAllStopsByLine(261, 131);
		for (int i = 0; i < stops.size(); i++) {System.out.println(stops.get(i));}
		System.out.println();
		
	}

	public static void startTest() throws ParseException {
		
		SimController sm = new SimController(null);
		sm.initialize_SCV(new File("C:/Users/Nicolas Biojo Bermeo/Downloads/datagrams.csv"), ",");
		sm.setPlanVersionID(261);
		ArrayList<SITMCalendar> calendars = sm.getDateByPlanVersion(261);
		sm.setDates(calendars.get(0).getOperationDay(), calendars.get(calendars.size()-1).getOperationDay());
		sm.setLineId(131);
		
		System.out.println("Events ============================================\n");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		Date init = new Date(dateFormat.parse("2019-06-20 18:00:08").getTime());
		Date last = new Date(dateFormat.parse("2019-06-20 18:10:00").getTime());
		sm.setDates(init, last);
		ArrayList<Event> events = sm.getNextEvents();
		
		while(events!=null) {
			
			for (int i = 0; i < events.size(); i++){
				if(events.get(i).getType().equals(EventType.POSICIONAMIENTO_GPS)) {
					System.out.println("BusID="+events.get(i).getContext().get("busID")+" "+
							"GPS_X="+events.get(i).getContext().get("GPS_X")+" "+
							"GPS_Y="+events.get(i).getContext().get("GPS_Y"));
				}
			}
			
			System.out.println();
			events = sm.getNextEvents();
		}
		
		
		System.out.println();

	}
}
