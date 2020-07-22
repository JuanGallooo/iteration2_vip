package com.simulationFramework.Simulation.Event.EventProvider;

import java.util.Hashtable;

import com.simulationFramework.Simulation.DataGenerationTools.ProbabilisticDistribution;
import com.simulationFramework.Simulation.Event.Event;
import com.simulationFramework.Simulation.Event.EventType;

public class EventGenerator {

	private ProbabilisticDistribution probabilisticDistribution;

	public EventGenerator() {

		String type = "ChiSquaredDistribution", arg1 = "degreesOfFreedom";
		Hashtable<String, Object> params = new Hashtable<>();
		params.put(arg1, 3.0);

		probabilisticDistribution = new ProbabilisticDistribution(type, params);

	}

	public Event generate() {

		double number = probabilisticDistribution.getNextDistributionValue();
		if (number > 0.8) {

			Event event = new Event();
			event.setType(EventType.REINICIO_IVU_BOX_FUENTE_PODER);
			return event;
		}

		return null;
	}

}
