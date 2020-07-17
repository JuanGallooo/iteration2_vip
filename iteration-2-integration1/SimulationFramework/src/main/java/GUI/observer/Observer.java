package GUI.observer;

import java.util.ArrayList;

import Simulation.SimState.Variable;
import SystemState.FactoryInterfaces.IBus;
import SystemState.FactoryInterfaces.IStop;

public interface Observer {

	public void updateVariables(ArrayList<Variable> variables);

	public void updateStops(ArrayList<IStop> stops);

	public void updateBuses(ArrayList<IBus> bus);

}
