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
				context.put("GPS_X", operationaTravels.get(i).getGPS_X()+"");
				context.put("GPS_Y", operationaTravels.get(i).getGPS_Y()+"");
				context.put("lineID", operationaTravels.get(i).getLineID()+"");
				context.put("eventDate", operationaTravels.get(i).getEventDate().toGMTString());
				
				event.setContext(context);
				eventlist.add(event);	
			}
			
		}
		
		return eventlist;
	}

}
