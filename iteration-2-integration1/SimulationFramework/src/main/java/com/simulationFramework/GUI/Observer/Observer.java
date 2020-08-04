package com.simulationFramework.GUI.Observer;

import java.util.ArrayList;

import com.simulationFramework.Simulation.SimState.Variable;
import com.simulationFramework.SystemState.SITMFactory.SITMBus;

public interface Observer {

	public void updateVariables(ArrayList<Variable> variables);

	public void updateStops();

	public void updateBuses(ArrayList<SITMBus> bus);

}
