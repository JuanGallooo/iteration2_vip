package com.simulationFramework.Simulation.Event.EventProvider;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

import com.simulationFramework.DataSource.DataSource2;
import com.simulationFramework.Simulation.Event.Event;
import com.simulationFramework.Simulation.Event.EventType;
import com.simulationFramework.SystemState.SITMFactory.SITMOperationalTravels;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventFetcher {

	private DataSource2 dataSource;
	
	public Event fetch(Date initialDate, Date finalDate, long line,long planVerionID) throws Exception {
		
		Event event = new Event();
		event.setType(EventType.POSICIONAMIENTO_GPS);
		event.setContext(dataSource.fetchNextRow(initialDate,finalDate,line,planVerionID));
		event.setDate(event.getContext().get("datagramDate"));
		return event;
	}

	public ArrayList<Event> nextFetch(int rate, Date initialDate, Date finalDate, long line,long planVerionID) throws Exception {
		
		Event actualEvent = fetch(initialDate,finalDate,line,planVerionID);
		Date actualDate = actualEvent.getDate();
		Date limitDate = new Date(actualEvent.getDate().getTime() + (rate));
		
		ArrayList<Event> eventlist = new ArrayList<>();
		
		while (actualDate.compareTo(limitDate) <= 0) {

			long actualId = Long.parseLong(actualEvent.getContext().get("lineId"));
			
			if (actualId == line) {
				eventlist.add(actualEvent);
			}

			actualEvent = fetch(initialDate,finalDate,line,planVerionID);
			actualDate = actualEvent.getDate();
		}
		
		return eventlist;
	}

	@SuppressWarnings("deprecation")
	public ArrayList<Event> allFetch(Date initialDate, Date lastDate, long lineID){
		
		
		ArrayList<SITMOperationalTravels> operationaTravels = dataSource.findAllOperationalTravelsByRange(initialDate, lastDate, lineID);
		ArrayList<Event> eventlist = new ArrayList<>();
		
		for (int i = 0; i < operationaTravels.size(); i++) {
			
			if(!operationaTravels.get(i).getGPS_X().equals("-1") && !operationaTravels.get(i).getGPS_Y().equals("-1")) {
				
				Event event = new Event();
				event.setType(EventType.POSICIONAMIENTO_GPS);
				
				HashMap<String, String> context = new HashMap<String, String>();
				context.put("opertravelID", operationaTravels.get(i).getOpertravelID()+"");
				context.put("busID", operationaTravels.get(i).getBusID()+"");
				context.put("laststopID", operationaTravels.get(i).getLaststopID()+"");
				context.put("GPS_X", operationaTravels.get(i).getGPS_X()+"");
				context.put("GPS_Y", operationaTravels.get(i).getGPS_Y()+"");
				context.put("odometervalue", operationaTravels.get(i).getOdometervalue()+"");
				context.put("lineID", operationaTravels.get(i).getLineID()+"");
				context.put("taskID", operationaTravels.get(i).getTaskID()+"");
				context.put("tripID", operationaTravels.get(i).getTripID()+"");
				context.put("eventDate", operationaTravels.get(i).getEventDate().toGMTString());
				
				event.setContext(context);
				eventlist.add(event);	
			}
			
		}
		
		return eventlist;
	}

}
