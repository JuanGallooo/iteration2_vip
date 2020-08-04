package com.simulationFramework.GUI.Marker;

import java.util.List;

import org.json.JSONException;

import com.simulationFramework.SystemState.SITMFactory.SITMBus;
import com.simulationFramework.SystemState.SITMFactory.SITMStop;

public interface AdapterInfoMarkers  {

	public void saveStops(List<SITMStop> datos) throws JSONException;
	public void saveBus(List<SITMBus> datos) throws JSONException;
}
