package com.simulationFramework.Simulation.Event.EventProvider;

import java.sql.Date;
import java.util.ArrayList;

import com.simulationFramework.DataSource.DataSource2;
import com.simulationFramework.Simulation.Event.Event;

public class EventProviderController {

	private EventFetcher eventFecher;
	private EventGenerator eventGenerator;

	public EventProviderController() {
		eventFecher = new EventFetcher();
		eventGenerator = new EventGenerator();
	}

	public ArrayList<Event> getNextEvent(Date initialDate, Date lastDate, long lineID){
		
		ArrayList<Event> events = eventFecher.allFetch(initialDate, lastDate, lineID);

		Event eventGenerated = eventGenerator.generate();

		if (eventGenerated != null) {
			events.add(0, eventGenerated);
		}

		return events;
	}

	public void setDataSource(DataSource2 dataSource) {
		eventFecher.setDataSource(dataSource);
	}

}
