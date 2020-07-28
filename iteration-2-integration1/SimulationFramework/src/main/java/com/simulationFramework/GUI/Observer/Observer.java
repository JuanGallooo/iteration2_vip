package com.simulationFramework.GUI.Observer;

import java.util.ArrayList;

import com.simulationFramework.Simulation.SimState.Variable;
import com.simulationFramework.SystemState.FactoryInerfaces.IBus;
import com.simulationFramework.SystemState.FactoryInerfaces.IStop;
import com.simulationFramework.SystemState.SITMFactory.SITMStop;

public interface Observer {

	public void updateVariables(ArrayList<Variable> variables);

	public void updateStops(ArrayList<SITMStop> arrayList);

	public void updateBuses(ArrayList<IBus> bus);

}
