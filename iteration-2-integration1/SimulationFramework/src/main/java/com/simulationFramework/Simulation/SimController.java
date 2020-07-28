package com.simulationFramework.Simulation;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

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
import com.simulationFramework.SystemState.SITMFactory.SITMPlanVersion;

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
	private long lineID;
	private volatile int speed;
	private long planVersionID;

	private Date iniDate;
	private Date finalDate;

	@Autowired
	public SimController(DataSource2 dataSource) {

		this.dataSource=dataSource;

		// set default variables
		lineID = 140;
		planVersionID = 185;
		speed = Clock.NORMAL;

		// upload system state
		initTargetSystem();

		// clock configuration
		clock = new Clock();
		clock.setClockMode(Clock.DISCRET);
		clock.setClockRate(Clock.ONE_TO_FIVE);

		// initialize relationships
		variables = new VariableController();
		executionThread = new ExecutionThread(this);
		eventProvirderController = new EventProviderController();
		eventProcessorController = new EventProcessorController();
	}

	public Iterable<SITMCalendar> getDateByPlanVersion(long planVersionID) {
		return dataSource.findAllCalendarsByPlanVersion(planVersionID);
	}

	public Iterable<SITMPlanVersion> getPlanVersions() {
		System.out.println(getDateByPlanVersion(185));
		return dataSource.findAllPlanVersions();
	}

	public void initTargetSystem() {
		this.targetSystem = new TargetSystem();
	}

	public void initialize_SCV(File sourceFile, String split) {
		dataSource = new DataSource2(sourceFile, split);
		eventProvirderController.setDataSource(dataSource);
	}

	public void initializeDB() {
		iniDate = new Date();
		finalDate = new Date();
		dataSource = new DataSource2();
		eventProvirderController.setDataSource(dataSource);

	}

	public void initVariables(String[] headers) {
		variables.addHeaders(headers);
	}

	public void start() {
		clock.start();
		executionThread.start();
		System.out.println("=======> simulation started");
	}

	public void pause() {
		clock.pause();
		executionThread.setPause(true);
		System.out.println("=======> simulation paused");
	}

	public void resume() {
		clock.resumeClock();
		executionThread.setPause(false);
		System.out.println("=======> simulation resumed");
	}

	public void stop() {
		clock.stopClock();
		executionThread.kill();
		System.out.println("=======> simulation stoped");
	}

	public void setLineId(long lineId) {
		this.lineID = lineId;
		System.out.println("=======> filter to line " + lineId);
		if (dataSource.getType().equals(DataSource2.FILE_CSV)) {
			observer.updateStops(targetSystem.filterStopsByLineId(lineId));
		} else {
			//observer.updateStops(dataSource.findAllStopsByLine(planVersionID, lineID));
		}
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

	public ArrayList<Event> getNextEvents() throws Exception {
		return eventProvirderController.getNextEvent(clock.getClockRate(), lineID, iniDate, finalDate, lineID,
				planVersionID);
	}

	@Override
	public void subscribe(Observer observer) {
		this.observer = observer;
	}

	public void setPlanVersionID(long planVersionID) {
		this.planVersionID = planVersionID;
	}

	public long getPlanVersionID() {
		return this.planVersionID;
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

					if (!events.isEmpty()) {

						simController.getVariables().updateAllValues(events.get(events.size() - 1).getContext());
						simController.getClock().getNextTick(events.get(events.size() - 1).getDate());

						for (int i = 0; i < events.size(); i++) {
							simController.getEventProcessorController().processEvent(events.get(i),
									simController.getTargetSystem());

						}

						simController.getObserver().updateBuses(
								simController.getTargetSystem().filterBusesByLineId(simController.getLineID()));
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
