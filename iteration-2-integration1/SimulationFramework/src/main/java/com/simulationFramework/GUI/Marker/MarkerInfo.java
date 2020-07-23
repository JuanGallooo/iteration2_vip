package com.simulationFramework.GUI.Marker;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import com.simulationFramework.SystemState.FactoryInerfaces.IBus;
import com.simulationFramework.SystemState.FactoryInerfaces.IStop;
import com.simulationFramework.SystemState.SITMFactory.SITMBus;
import com.simulationFramework.SystemState.SITMFactory.SITMStop;

public class MarkerInfo implements AdapterInfoMarkers {

	private List<MarkerFactory> markersBuses;
	private List<MarkerFactory> markersStops;
	
	public static final String imgBus = "bus_azul.png";
	public static final String imgStop = "estacion_roja.png";
	
	public static final String pathBus = "src/main/java/GUI/view/markerBus.json";
	public static final String pathStop = "src/main/java/GUI/view/markerStop.json";
	
	public static final String typeBus = "Bus";
	public static final String typeStop = "Stop";
	
	private WriterJson wJ;

	public MarkerInfo() {
		wJ = new WriterJson(this);
		markersBuses = new ArrayList<MarkerFactory>();
		markersStops = new ArrayList<MarkerFactory>();
	}

	public void deleteBuses() {
		markersBuses.clear();
	}

	public void addMarkerBus(long id, double lat, double lng) {
		markersBuses.add(new MarkerFactoryImp(id,imgBus, lat, lng));
	}
	
	public void deleteStops() {
		markersStops.clear();
	}

	public void addMarkerStop(long id, double lat, double lng) {
		markersStops.add(new MarkerFactoryImp(id,imgStop, lat, lng));
	}

	public void saveStops(List<IStop> datos) throws JSONException {

		deleteStops();
		
		for (IStop stop : datos) {
			SITMStop concreteStop = (SITMStop) stop;
			
			if(concreteStop.getDecimalLatitude()!=0 && concreteStop.getDecimalLongitude()!=0) {
				this.addMarkerStop(concreteStop.getStopID(),concreteStop.getDecimalLatitude(), concreteStop.getDecimalLongitude());
			}
		}
		wJ.write(typeStop,pathStop,markersStops);
	}

	public void saveBus(List<IBus> datos) throws JSONException {
		
		deleteBuses();
		
		for (IBus bus : datos) {
			SITMBus concreteBus = (SITMBus) bus;

			if (concreteBus.getLatitude() != 0 && concreteBus.getLongitude() != 0) {
				
				this.addMarkerBus(concreteBus.getBusID(), concreteBus.getLatitude(), concreteBus.getLongitude());
			}
		}
		
		wJ.write(typeBus,pathBus,markersBuses);
	}

}
