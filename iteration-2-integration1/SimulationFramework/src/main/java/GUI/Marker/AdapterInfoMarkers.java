package GUI.Marker;

import java.util.List;

import org.json.JSONException;

import SystemState.FactoryInterfaces.IBus;
import SystemState.FactoryInterfaces.IStop;

public interface AdapterInfoMarkers  {

	public void saveStops(List<IStop> datos) throws JSONException;
	public void saveBus(List<IBus> datos) throws JSONException;
}
