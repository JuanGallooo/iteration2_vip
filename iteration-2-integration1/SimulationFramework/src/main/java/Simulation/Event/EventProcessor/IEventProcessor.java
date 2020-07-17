package Simulation.Event.EventProcessor;

import Simulation.Event.Event;
import SystemState.TargetSystem;

public interface IEventProcessor {

	public void processEvent(Event event,TargetSystem TargetSystem);
	
}
