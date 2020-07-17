package SimulationProject;

import java.io.Serializable;

import DataSource.DataSource;
import Simulation.Clock;
import Simulation.SimState.VariableController;
import SystemState.TargetSystem;
import lombok.Data;
import lombok.NonNull;

@Data
public class Project implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NonNull
	private String name;
	
	@NonNull
	private Long id;
		
	private DataSource dataSource;
	private TargetSystem targetSystem;
	private Clock clock;
	private VariableController variables;
}
