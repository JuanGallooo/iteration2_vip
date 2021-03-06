package com.simulationFramework.Simulation;

import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simulationFramework.DataSource.DataSource2;
import com.simulationFramework.GUI.Observer.Observer;
import com.simulationFramework.GUI.Observer.SubjectOberver;
import com.simulationFramework.Simulation.Event.Event;
import com.simulationFramework.Simulation.Event.EventProcessor.EventProcessorController;
import com.simulationFramework.Simulation.Event.EventProvider.EventProviderController;
import com.simulationFramework.Simulation.SimState.VariableController;
import com.simulationFramework.SystemState.TargetSystem;
import com.simulationFramework.SystemState.SITMFactory.SITMCalendar;
import com.simulationFramework.SystemState.SITMFactory.SITMLine;
import com.simulationFramework.SystemState.SITMFactory.SITMPlanVersion;
import com.simulationFramework.SystemState.SITMFactory.SITMStop;

import lombok.Getter;
import lombok.Setter;

@Getter
@Service
public class SimController implements SubjectOberver {

	// external packages
	private Observer observer;
	private DataSource2 dataSource;
	private TargetSystem targetSystem;

	// simulation package
	private Clock clock;
	private VariableController variables;
	private EventProviderController eventProvirderController;
	private EventProcessorController eventProcessorController;

	// execution thread
	private ExecutionThread executionThread;

	// variables
	private volatile int speed;
	private long lineID;
	private long planVersionID;
	private Date initialDate;
	private Date lastDate;

	@Autowired
	public SimController(DataSource2 dataSource) {

		this.dataSource=dataSource;

		// set default variables
		lineID = 140;
		planVersionID = 261;
		speed = Clock.NORMAL;

		// upload system state
		initialize_TargetSystem();

		// clock configuration
		clock = new Clock();
		clock.setClockMode(Clock.DISCRET);
		clock.setClockRate(Clock.ONE_TO_FIVE);

		// initialize relationships
		variables = new VariableController();
		executionThread = new ExecutionThread(this);
		eventProvirderController = new EventProviderController();
		eventProcessorController = new EventProcessorController();
		eventProvirderController.setDataSource(dataSource);
	}

	public void initialize_SCV(File sourceFile, String split) {
		dataSource = new DataSource2(sourceFile, split);
		eventProvirderController.setDataSource(dataSource);
	}

	public void initialize_DB() {
		dataSource = new DataSource2();
		eventProvirderController.setDataSource(dataSource);
	}
	
	public void initialize_TargetSystem() {
		this.targetSystem = new TargetSystem();
	}

	public void initialize_Variables(String[] headers) {
	}
	
	public void setPlanVersionID(long planVersionID) {
		this.planVersionID = planVersionID;
	}
	
	public void setLineId(long lineID) {
		this.lineID = lineID;
	}
	
	public void setColumnNumberForSimulationVariables(int clock, int gps_X, int gps_Y, int busID, int lineID) {
		dataSource.setColumnNumberForSimulationVariables(clock, gps_X, gps_Y, busID, lineID);
	}
	
	public void setHeaders(HashMap<String, Integer> headers) {
		dataSource.setHeaders(headers);
		
		String [] h = new String[headers.size()];
		
		int i = 0;
		for( HashMap.Entry<String, Integer> entry : headers.entrySet() ) {
			h[i] = entry.getKey();
			i++;
		}
		
		variables.addHeaders(h);

	}
	
	public void setDates(Date initialDate,Date lastDate) {
		this.initialDate = initialDate;
		this.lastDate = lastDate;
	}
	
	public ArrayList<SITMPlanVersion> getPlanVersions() {
		return dataSource.findAllPlanVersions();
	}
	
	public ArrayList<SITMCalendar> getDateByPlanVersion(long planVersionID) {
		return dataSource.findAllCalendarsByPlanVersion(planVersionID);
	}
	
	public ArrayList<SITMLine> getLinesByPlanVersion() {
		return dataSource.findAllLinesByPlanVersion(planVersionID);
	}
	
	public ArrayList<SITMStop> getStopsByLine(){
		return dataSource.findAllStopsByLine(this.planVersionID, this.lineID);
	}

	public HashMap<String,String> getLastRow(){
		return dataSource.getLastRow();
	}
	
	public void start() {
		
		if(executionThread.isPause()) {
			executionThread.setPause(false);
			System.out.println("=======> simulation resumed");
		}else {
			executionThread.start();
			System.out.println("=======> simulation started");
		}	
	}

	public void pause() {
		executionThread.setPause(true);
		System.out.println("=======> simulation paused");
	}

	public void resume() {
		executionThread.setPause(false);
		System.out.println("=======> simulation resumed");
	}

	public void stop() {
		executionThread.kill();
		System.out.println("=======> simulation finished");
	}

	public void setFastSpeed() {
		this.speed = Clock.FAST;
		System.out.println("=======> set Fast Speed");
	}

	public void setNormalSpeed() {
		this.speed = Clock.NORMAL;
		System.out.println("=======> set Normal Speed");
	}

	public void setSlowSpeed() {
		this.speed = Clock.SLOW;
		System.out.println("=======> set Slow Speed");
	}

	public void setOneToOneSpeed() {
		this.clock.setClockRate(Clock.ONE_TO_ONE);
		System.out.println("=======> set One To One Speed");
	}

	public void setOneToFiveSpeed() {
		this.clock.setClockRate(Clock.ONE_TO_FIVE);
		System.out.println("=======> set One To Five Speed");
	}

	public void setOneToTenSpeed() {
		this.clock.setClockRate(Clock.ONE_TO_TEN);
		System.out.println("=======> set One To Ten Speed");
	}

	public void setOneToThirtySpeed() {
		this.clock.setClockRate(Clock.ONE_TO_THIRTY);
		System.out.println("=======> set One To Thirty Speed");
	}

	public void setOneToSixtySpeed() {
		this.clock.setClockRate(Clock.ONE_TO_SIXTY);
		System.out.println("=======> set One To Sixty Speed");
	}

	public ArrayList<Event> getNextEvents(){
		
		Date nextDate = new Date(initialDate.getTime()+clock.getClockRate());
		ArrayList<Event> events = new ArrayList<>();
		
		if(nextDate.getTime()>lastDate.getTime()) {
			events = null;
		}else {
			events = eventProvirderController.getNextEvent(initialDate,nextDate,lineID);
			getClock().getNextTick(nextDate);
			initialDate = nextDate;
		}
		return events;
	}
	
	public HashMap<String, String> getLastVariables(){
		return null;
	}

	@Override
	public void subscribe(Observer observer) {
		this.observer = observer;
	}
}

@Getter
@Setter
class ExecutionThread extends Thread {

	private volatile boolean pause = false;
	private volatile boolean killed = false;
	private SimController simController;

	public ExecutionThread(SimController simController) {
		this.simController = simController;
	}

	public void kill() {
		pause = true;
		killed = true;
	}

	@Override
	public void run() {
		while (!killed) {
			while (!pause) {
				try {

					ArrayList<Event> events = simController.getNextEvents();
									
					if(events==null) {
						
						pause = true;
						killed = true;
						System.out.println("=======> simulation finished");
						
					}else if (!events.isEmpty()) {
						
						simController.getVariables().updateAllValues(simController.getLastRow());			

						for (int i = 0; i < events.size(); i++) {
							simController.getEventProcessorController().processEvent(events.get(i),simController.getTargetSystem());
						}

						simController.getObserver().updateBuses(simController.getTargetSystem().filterBusesByLineId(simController.getLineID()));
						simController.getObserver().updateVariables(simController.getVariables().getAllVariables());

						sleep(simController.getSpeed());
					}
					
				} catch (Exception e) {
					e.printStackTrace();
					pause = true;
				}
			}
		}
	}
}
