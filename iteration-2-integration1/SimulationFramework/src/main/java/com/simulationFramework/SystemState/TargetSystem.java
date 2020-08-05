package com.simulationFramework.SystemState;

import java.io.Serializable;
import java.util.ArrayList;

import com.simulationFramework.SystemState.FactoryInerfaces.AbstractModelFactory;
import com.simulationFramework.SystemState.FactoryInerfaces.IBus;
import com.simulationFramework.SystemState.SITMFactory.ConcreteSITMFactory;
import com.simulationFramework.SystemState.SITMFactory.SITMBus;


public class TargetSystem implements Serializable {

	private static final long serialVersionUID = 1L;

	private ArrayList<IBus> buses;
	private AbstractModelFactory factory;

	public TargetSystem() {

		factory = new ConcreteSITMFactory();
		buses = factory.createBuses();
	}

	public void moveBus(double idBus, double longitude, double latitude) {
		
		for (int i = 0; i < buses.size(); i++) {

			if (((SITMBus) buses.get(i)).getBusID() == idBus) {
				((SITMBus) buses.get(i)).setLatitude(latitude);
				((SITMBus) buses.get(i)).setLongitude(longitude);
			}
		}
	}

	public ArrayList<SITMBus> filterBusesByLineId(long lineId) {
		ArrayList<SITMBus> busesByLine = new ArrayList<>();

		for (int i = 0; i < buses.size(); i++) {
			SITMBus bus = (SITMBus) buses.get(i);
			if (bus.getLineId() == lineId) {
				busesByLine.add(bus);
			}

		}

		return busesByLine;
	}

	public void setLineToBus(long busId, long lineId) {

		for (int i = 0; i < buses.size(); i++) {
			SITMBus bus = (SITMBus) buses.get(i);
			if (bus.getBusID() == busId) {
				bus.setLineId(lineId);
			}

		}
	}

}
