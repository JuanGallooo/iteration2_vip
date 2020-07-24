package com.simulationFramework.Simulation.Event.EventProvider;

import java.util.ArrayList;
import java.util.Date;

import com.simulationFramework.DataSource.DataSource2;
import com.simulationFramework.Simulation.Event.Event;
import com.simulationFramework.Simulation.Event.EventType;

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

	public ArrayList<Event> nextFetch(int rate, long lineId,Date initialDate, Date finalDate, long line,long planVerionID) throws Exception {
		
		Event actualEvent = fetch(initialDate,finalDate,line,planVerionID);
		Date actualDate = actualEvent.getDate();
		Date limitDate = new Date(actualEvent.getDate().getTime() + (rate));
		
		ArrayList<Event> eventlist = new ArrayList<>();
		
		while (actualDate.compareTo(limitDate) <= 0) {

			long actualId = Long.parseLong(actualEvent.getContext().get("lineId"));
			
			if (actualId == lineId) {
				eventlist.add(actualEvent);
			}

			actualEvent = fetch(initialDate,finalDate,line,planVerionID);
			actualDate = actualEvent.getDate();
		}

		
		
		return eventlist;
	}


}
