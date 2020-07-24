package com.simulationFramework.Simulation.Event.EventProvider;

import java.util.ArrayList;
import java.util.Date;

import com.simulationFramework.DataSource.DataSource2;
import com.simulationFramework.Simulation.Event.Event;

public class EventProviderController {

	private EventFetcher eventFecher;
	private EventGenerator eventGenerator;

	public EventProviderController() {
		eventFecher = new EventFetcher();
		eventGenerator = new EventGenerator();
	}

	public ArrayList<Event> getNextEvent(int rate, long lineId,Date initialDate, Date finalDate, long line,long planVerionID) throws Exception {

		ArrayList<Event> events = eventFecher.nextFetch(rate, lineId,initialDate,finalDate,line,planVerionID);
		Event eventGenerated = eventGenerator.generate();
	
		if (eventGenerated != null) {
			events.add(0,eventGenerated);
		}

		return events;
	}

	public void setDataSource(DataSource2 dataSource) { 
		eventFecher.setDataSource(dataSource);
	}


}
