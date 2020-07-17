package SystemState;

import java.io.Serializable;
import java.util.ArrayList;

import SystemState.FactoryInterfaces.AbstractModelFactory;
import SystemState.FactoryInterfaces.IArc;
import SystemState.FactoryInterfaces.IBus;
import SystemState.FactoryInterfaces.ICalendar;
import SystemState.FactoryInterfaces.ILine;
import SystemState.FactoryInterfaces.ILineStop;
import SystemState.FactoryInterfaces.IPlanVersion;
import SystemState.FactoryInterfaces.IScheduleTypes;
import SystemState.FactoryInterfaces.IStop;
import SystemState.FactoryInterfaces.ITask;
import SystemState.SITMFactory.ConcreteSITMFactory;
import SystemState.SITMFactory.SITMBus;
import SystemState.SITMFactory.SITMLineStop;
import SystemState.SITMFactory.SITMStop;
import SystemState.SITMFactory.SITMTrip;
import lombok.Getter;

@Getter
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

			if (((SITMBus) buses.get(i)).getBusId() == idBus) {
				((SITMBus) buses.get(i)).setLatitude(latitude);
				((SITMBus) buses.get(i)).setLongitude(longitude);
			}
		}
	}

	public ArrayList<IStop> filterStopsByLineId(long lineId) {
		ArrayList<IStop> stopsByLine = new ArrayList<>();

		for (int i = 0; i < lineStops.size(); i++) {
			SITMLineStop lineStop = (SITMLineStop) lineStops.get(i);

			if (lineStop.getLineid() == lineId) {

				for (int j = 0; j < stops.size(); j++) {
					SITMStop stop = (SITMStop) stops.get(j);
					if (stop.getStopId() == lineStop.getStopid()) {
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
			if (bus.getBusId() == busId) {
				bus.setLineId(lineId);
			}

		}

	}

}
