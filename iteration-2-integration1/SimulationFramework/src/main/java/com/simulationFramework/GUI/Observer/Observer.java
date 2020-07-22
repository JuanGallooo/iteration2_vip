package com.simulationFramework.GUI.Observer;

import java.util.ArrayList;

import com.simulationFramework.Simulation.SimState.Variable;
import com.simulationFramework.SystemState.FactoryInerfaces.IBus;
import com.simulationFramework.SystemState.FactoryInerfaces.IStop;

public interface Observer {

	public void updateVariables(ArrayList<Variable> variables);

	public void updateStops(ArrayList<IStop> arrayList);

	public void updateBuses(ArrayList<IBus> bus);

}
