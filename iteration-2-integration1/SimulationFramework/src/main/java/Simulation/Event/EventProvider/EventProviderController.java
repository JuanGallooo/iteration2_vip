package Simulation.Event.EventProvider;

import java.util.ArrayList;
import java.util.Date;

import DataSource.DataSource;
import Simulation.Event.Event;

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

	public void setDataSource(DataSource dataSource) { 
		eventFecher.setDataSource(dataSource);
	}


}
