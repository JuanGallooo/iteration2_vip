package com.vip.simulation.simulation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class SimulationApplication {
	
	public static void main(String[] args) {
		ConfigurableApplicationContext c = SpringApplication.run(SimulationApplication.class, args);		
	}
}
