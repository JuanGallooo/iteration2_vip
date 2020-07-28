package com.simulationFramework.Simulation.Event;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import lombok.Data;

@Data
public class Event {
	
	private EventType type;
	private HashMap<String, String> context;
	private Date date;
	
	public Event() {
		context = new HashMap<String, String>();
	}

    public void setDate(String eventDate) {

    	DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
    	
    	try {
			date = new Date(dateFormat.parse(eventDate).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
    }
        
}
