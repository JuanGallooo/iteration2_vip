package Simulation.SimState;

import lombok.Data;
import lombok.NonNull;

@Data
public class Variable {
	
	@NonNull
	private String header;
	@NonNull
	private String value;
	
	public Variable(String header, String value) {
		super();
		this.header = header;
		this.value = value;
	}
	
	
}
