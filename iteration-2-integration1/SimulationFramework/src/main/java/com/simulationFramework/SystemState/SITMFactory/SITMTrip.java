package com.simulationFramework.SystemState.SITMFactory;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;

import com.simulationFramework.SystemState.FactoryInerfaces.ITrip;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name="TRIPS")
@BatchSize(size=25)
@Getter @Setter @ToString
public class SITMTrip implements ITrip,Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="TRIPID")
	private long tripID;
	
	@Column(name="SCHEDULETYPEID")
	private long scheduleTypeID;
	
	
	@Column(name="TRIPSEQUENCE")
	private long tripSequence;
	
	@Column(name="STARTTIME")
	private String startTime;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="ORIENTATION")
	private boolean orientation;
	
	@Column(name="PLANVERSIONID")
	private long planVersionID;
	
	@Column(name="TASKID")
	private String taskID;
	
	@Column(name="LINEID")
	private long lineID;
	
	@Column(name="STARTSTOPID")
	private long startStopID;
	
	@Column(name="ENDSTOPID")
	private long endStopID;

	public SITMTrip(long tripID, long scheduleTypeID, long tripSequence, String startTime, String description,
			boolean orientation, long planVersionID, String taskID, long lineID, long startStopID, long endStopID) {
		super();
		this.tripID = tripID;
		this.scheduleTypeID = scheduleTypeID;
		this.tripSequence = tripSequence;
		this.startTime = startTime;
		this.description = description;
		this.orientation = orientation;
		this.planVersionID = planVersionID;
		this.taskID = taskID;
		this.lineID = lineID;
		this.startStopID = startStopID;
		this.endStopID = endStopID;
	}

	
	
	
}