package com.vip.simulation.simulation.Service;

import java.io.File;

import org.springframework.stereotype.Service;

@Service
public class RestService {
	
	public File getBus() {
		String path = File.separator+"SimulationFramework"
				  +File.separator+"src"
				  +File.separator+"main"
				  +File.separator+"java"
				  +File.separator+"com"
				  +File.separator+"simulationFramework"
				  +File.separator+"GUI"
				  +File.separator+"view"
				  +File.separator+ "markerBus.json";
	
		File file = new File(System.getProperty("user.dir")+File.separator+".."+path);
		return file;
	}
	

	public File getStop() {
		String path = File.separator+"SimulationFramework"
				  +File.separator+"src"
				  +File.separator+"main"
				  +File.separator+"java"
				  +File.separator+"com"
				  +File.separator+"simulationFramework"
				  +File.separator+"GUI"
				  +File.separator+"view"
				  +File.separator+ "markerStop.json";
	
		File file = new File(System.getProperty("user.dir")+File.separator+".."+path);
		return file;
	}

}
