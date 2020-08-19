package com.simulationFramework.Simulation.Event;

import java.util.HashMap;

import lombok.Data;

@Data
public class Event {
	
	private EventType type;
	private HashMap<String, String> context;
	//private Date date;
	
	public Event() {
		context = new HashMap<String, String>();
	}
        
}
