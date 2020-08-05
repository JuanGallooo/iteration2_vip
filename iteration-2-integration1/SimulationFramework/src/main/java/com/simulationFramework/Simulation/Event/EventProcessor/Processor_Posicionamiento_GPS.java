package com.simulationFramework.Simulation.Event.EventProcessor;

import com.simulationFramework.Simulation.Event.Event;
import com.simulationFramework.SystemState.TargetSystem;

public class Processor_Posicionamiento_GPS implements IEventProcessor {

	@Override
	public void processEvent(Event event, TargetSystem TargetSystem) {

		long idBus = Long.parseLong(event.getContext().get("busID"));
		long idLine = Long.parseLong(event.getContext().get("lineID"));
		double longitude = Double.parseDouble(event.getContext().get("GPS_X"));
		double latitude = Double.parseDouble(event.getContext().get("GPS_Y"));
		
		if (longitude != -1 && latitude != -1) {

			TargetSystem.setLineToBus(idBus, idLine);
			TargetSystem.moveBus(idBus, longitude / 10000000, latitude / 10000000);
		}

	}

}