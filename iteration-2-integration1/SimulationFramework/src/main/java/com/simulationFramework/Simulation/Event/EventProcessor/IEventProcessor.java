package com.simulationFramework.Simulation.Event.EventProcessor;

import com.simulationFramework.Simulation.Event.Event;
import com.simulationFramework.SystemState.TargetSystem;

public interface IEventProcessor {

	public void processEvent(Event event,TargetSystem TargetSystem);
	
}
