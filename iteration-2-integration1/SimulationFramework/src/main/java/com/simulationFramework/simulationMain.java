package com.simulationFramework;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.simulationFramework.DataSource.DataSource2;
import com.simulationFramework.Simulation.SimController;
import com.simulationFramework.SystemState.SITMFactory.SITMCalendar;
import com.simulationFramework.SystemState.SITMFactory.SITMLine;
import com.simulationFramework.SystemState.SITMFactory.SITMPlanVersion;
import com.simulationFramework.SystemState.SITMFactory.SITMStop;

public class simulationMain {

	public static void main(String[] args) throws IOException {
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		SimController sm = new SimController(new DataSource2(new File("C:\\Users\\Nicolas Biojo Bermeo\\Downloads"), ";"));
		
		
		System.out.println("plan Versions ====================================================================\n");
		ArrayList<SITMPlanVersion> planversions = sm.getPlanVersions();
		for (int i = 0; i < planversions.size(); i++) {System.out.println(planversions.get(i));}
		sm.setPlanVersionID(180);
		System.out.println();
		
		
		System.out.println("calendars ====================================================================\n");
		ArrayList<SITMCalendar> calendars = sm.getDateByPlanVersion();
		System.out.println("initDate: "+calendars.get(0));
		System.out.println("lastDate: "+calendars.get(calendars.size()-1));
		sm.setDates(calendars.get(0).getOperationDay(), calendars.get(calendars.size()-1).getOperationDay());
		System.out.println();
		
		
		System.out.println("lines =====================================================================\n");
		ArrayList<SITMLine> lines = sm.getLinesByPlanVersion();
		for (int i = 0; i < lines.size(); i++) {System.out.println(lines.get(i));}
		sm.setLineId(131);
		System.out.println();
		
		
		System.out.println("Stops ====================================================================\n");
		ArrayList<SITMStop> stops = sm.getDataSource().findAllStopsByLine(180, 131);
		for (int i = 0; i < stops.size(); i++) {System.out.println(stops.get(i));}
		System.out.println();
		
		reader.readLine();
	}

}
