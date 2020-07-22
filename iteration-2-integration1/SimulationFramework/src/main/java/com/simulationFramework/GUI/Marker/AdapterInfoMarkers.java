package com.simulationFramework.GUI.Marker;

import java.util.List;

import org.json.JSONException;

import com.simulationFramework.SystemState.FactoryInerfaces.IBus;
import com.simulationFramework.SystemState.FactoryInerfaces.IStop;

public interface AdapterInfoMarkers  {

	public void saveStops(List<IStop> datos) throws JSONException;
	public void saveBus(List<IBus> datos) throws JSONException;
}
