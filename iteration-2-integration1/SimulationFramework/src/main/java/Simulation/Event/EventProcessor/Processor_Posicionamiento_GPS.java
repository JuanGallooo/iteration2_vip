package Simulation.Event.EventProcessor;

import Simulation.Event.Event;
import SystemState.TargetSystem;

public class Processor_Posicionamiento_GPS implements IEventProcessor {

	@Override
	public void processEvent(Event event, TargetSystem TargetSystem) {

		long idBus = Long.parseLong(event.getContext().get("busId"));
		long idLine = Long.parseLong(event.getContext().get("lineId"));
		double longitude = Double.parseDouble(event.getContext().get("longitude"));
		double latitude = Double.parseDouble(event.getContext().get("latitude"));

		if (longitude != -1 && latitude != -1) {

			TargetSystem.setLineToBus(idBus, idLine);
			TargetSystem.moveBus(idBus, longitude / 10000000, latitude / 10000000);
		}

	}

}