package com.simulationFramework.SystemState;

import java.io.Serializable;
import java.util.ArrayList;

import com.simulationFramework.SystemState.FactoryInerfaces.AbstractModelFactory;
import com.simulationFramework.SystemState.FactoryInerfaces.IArc;
import com.simulationFramework.SystemState.FactoryInerfaces.IBus;
import com.simulationFramework.SystemState.FactoryInerfaces.ICalendar;
import com.simulationFramework.SystemState.FactoryInerfaces.ILine;
import com.simulationFramework.SystemState.FactoryInerfaces.ILineStop;
import com.simulationFramework.SystemState.FactoryInerfaces.IPlanVersion;
import com.simulationFramework.SystemState.FactoryInerfaces.IScheduleTypes;
import com.simulationFramework.SystemState.FactoryInerfaces.IStop;
import com.simulationFramework.SystemState.FactoryInerfaces.ITask;
import com.simulationFramework.SystemState.SITMFactory.ConcreteSITMFactory;
import com.simulationFramework.SystemState.SITMFactory.SITMBus;
import com.simulationFramework.SystemState.SITMFactory.SITMLineStop;
import com.simulationFramework.SystemState.SITMFactory.SITMStop;
import com.simulationFramework.SystemState.SITMFactory.SITMTrip;

import lombok.Getter;


public class TargetSystem implements Serializable {

	private static final long serialVersionUID = 1L;

	// SITM configuration
	private IPlanVersion planVersion;
	private ArrayList<IBus> buses;
	private ArrayList<IArc> arcs;
	private ArrayList<ICalendar> calendars;
	private ArrayList<ILine> lines;
	private ArrayList<IScheduleTypes> stypes;
	private ArrayList<IStop> stops;
	private ArrayList<ITask> tasks;
	private ArrayList<SITMTrip> trips;
	private ArrayList<ILineStop> lineStops;

	private AbstractModelFactory factory;

	public TargetSystem() {

		factory = new ConcreteSITMFactory();
		planVersion = factory.createPlanVersion();
		buses = factory.createBuses();
		arcs = factory.createArcs();
		calendars = factory.createCalendars();
		lines = factory.createLines();
		stypes = factory.createScheduleTypes();
		stops = factory.createStops();
		tasks = factory.createTasks();
		lineStops = factory.createLineStops();
	}

	public void moveBus(double idBus, double longitude, double latitude) {

		for (int i = 0; i < buses.size(); i++) {

			if (((SITMBus) buses.get(i)).getBusID() == idBus) {
				((SITMBus) buses.get(i)).setLatitude(latitude);
				((SITMBus) buses.get(i)).setLongitude(longitude);
			}
		}
	}

	public ArrayList<IStop> filterStopsByLineId(long lineId) {
		ArrayList<IStop> stopsByLine = new ArrayList<>();

		for (int i = 0; i < lineStops.size(); i++) {
			SITMLineStop lineStop = (SITMLineStop) lineStops.get(i);

			if (lineStop.getLineID() == lineId) {

				for (int j = 0; j < stops.size(); j++) {
					SITMStop stop = (SITMStop) stops.get(j);
					if (stop.getStopID() == lineStop.getStopID()) {
						stopsByLine.add(stop);
					}
				}
			}
		}

		return stopsByLine;
	}

	public ArrayList<IBus> filterBusesByLineId(long lineId) {
		ArrayList<IBus> busesByLine = new ArrayList<>();

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
