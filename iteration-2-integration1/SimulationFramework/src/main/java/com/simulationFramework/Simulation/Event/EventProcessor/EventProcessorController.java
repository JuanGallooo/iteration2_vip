package com.simulationFramework.Simulation.Event.EventProcessor;

import com.simulationFramework.Simulation.Event.Event;
import com.simulationFramework.Simulation.Event.EventType;
import com.simulationFramework.SystemState.TargetSystem;

public class EventProcessorController {
	
	private IEventProcessor strategy;

	public void processEvent(Event event,TargetSystem TargetSystem) {
		
		if(event.getType().equals(EventType.POSICIONAMIENTO_GPS)) {
			strategy = new Processor_Posicionamiento_GPS();
			strategy.processEvent(event,TargetSystem);
		}else {
			
		}
		
		
		
	}
	
}