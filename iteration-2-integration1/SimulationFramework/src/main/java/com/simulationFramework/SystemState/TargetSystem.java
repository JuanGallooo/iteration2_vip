package com.simulationFramework.SystemState;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import com.simulationFramework.SystemState.SITMFactory.SITMBus;


public class TargetSystem implements Serializable {

	private static final long serialVersionUID = 1L;

	private HashMap<Long, SITMBus> buses ;

	public TargetSystem() {
		buses = new HashMap<Long, SITMBus>();
	}

	public void moveBus(long busID, long lineId, double longitude, double latitude) {
		
		if(buses.containsKey(busID)) {
			
			buses.get(busID).setLatitude(latitude);
			buses.get(busID).setLongitude(longitude);
			buses.get(busID).setLineId(lineId);;
		}else {
			
			SITMBus bus = new SITMBus();
			bus.setBusID(busID);
			bus.setLatitude(latitude);
			bus.setLongitude(longitude);
			bus.setLineId(lineId);
			buses.put(busID, bus);
		}
		
	}

	public ArrayList<SITMBus> filterBusesByLineId(long lineID) {
		ArrayList<SITMBus> busesByLine = new ArrayList<>();

		for (Entry<Long, SITMBus> entry : buses.entrySet()) {
			SITMBus bus = entry.getValue();
			
			if(bus.getLineId()==lineID) {
				busesByLine.add(bus);
			}
		}
		
		return busesByLine;
	}

}
