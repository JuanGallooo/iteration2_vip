package com.simulationFramework.SimulationProject;

import java.io.Serializable;

import com.simulationFramework.DataSource.DataSource2;
import com.simulationFramework.Simulation.Clock;
import com.simulationFramework.Simulation.SimState.VariableController;
import com.simulationFramework.SystemState.TargetSystem;

import lombok.Data;
import lombok.NonNull;

@Data
public class Project implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NonNull
	private String name;
	
	@NonNull
	private Long id;
		
	private DataSource2 dataSource;
	private TargetSystem targetSystem;
	private Clock clock;
	private VariableController variables;
}
