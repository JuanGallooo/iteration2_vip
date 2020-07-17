package Simulation.Event.EventProcessor;

import Simulation.Event.Event;
import Simulation.Event.EventType;
import SystemState.TargetSystem;

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