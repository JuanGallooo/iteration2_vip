package com.simulationFramework.SystemState.SITMFactory;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name="OPERATIONALTRAVELS")
@BatchSize(size=25)
@Getter @Setter @ToString
public class SITMOperationalTravels {
	
	@Id
	@Column(name="OPERTRAVELID")
	private long opertravelID;
	
	@Column(name="BUSID")
	private long busID;
	
	@Column(name="LASTSTOPID")
	private long laststopID;
	
	@Column(name="GPS_X")
	private String GPS_X;
	
	@Column(name="GPS_Y")
	private String GPS_Y;
	
	@Column(name="DEVIATIONTIME")
	private long deviationTime;
	
	@Column(name="ODOMETERVALUE")
	private long odometervalue;
	
	@Column(name="LINEID")
	private long lineID;
	
	@Column(name="TASKID")
	private long taskID;
	
	@Column(name="TRIPID")
	private long tripID;
	
	@Column(name="RIGHTCOURSE")
	private long rightcourse;
	
	@Column(name="ORIENTATION")
	private long orientation;
	
	@Column(name="EVENTDATE")
	private Date eventDate;
	
	@Column(name="EVENTTIME")
	private long eventTime;
	
	@Column(name="REGISTERDATE")
	private Date registerDate;
	
	@Column(name="EVENTTYPEID")
	private long eventTypeID;
	
	@Column(name="NEARESTSTOPID")
	private long nearestStopID;
	
	@Column(name="LASTUPDATEDATE")
	private Date lastUpDateDate;
	
	@Column(name="NEARESTSTOPMTS")
	private long nearestStopMTS;
	
	@Column(name="UPDNEARESTFLAG")
	private String updNearestFlag;
	
	@Column(name="LOGFILEID")
	private long logFileID;
	
	@Column(name="NEARESTPLANSTOPID")
	private long nearestPlanStopID;
	
	@Column(name="NEARESTPLANSTOPMTS")
	private long nearestPlanStopMTS;
	
	@Column(name="PLANSTOPAUTH")
	private String planStopAuth;
	
	@Column(name="RADIUSTOLERANCEMTS")
	private long radiusToleranceMTS;
	
	@Column(name="TIMEDIFF")
	private long timeDiff;
	
	public SITMOperationalTravels () {
		super();
	}

	public SITMOperationalTravels(long opertravelID, long busID, long laststopID, String gPS_X, String gPS_Y,
			long deviationTime, long odometervalue, long lineID, long taskID, long tripID, long rightcourse,
			long orientation, Date eventDate, long eventTime, Date registerDate, long eventTypeID, long nearestStopID,
			Date lastUpDateDate, long nearestStopMTS, String updNearestFlag, long logFileID, long nearestPlanStopID,
			long nearestPlanStopMTS, String planStopAuth, long radiusToleranceMTS, long timeDiff) {
		super();
		this.opertravelID = opertravelID;
		this.busID = busID;
		this.laststopID = laststopID;
		GPS_X = gPS_X;
		GPS_Y = gPS_Y;
		this.deviationTime = deviationTime;
		this.odometervalue = odometervalue;
		this.lineID = lineID;
		this.taskID = taskID;
		this.tripID = tripID;
		this.rightcourse = rightcourse;
		this.orientation = orientation;
		this.eventDate = eventDate;
		this.eventTime = eventTime;
		this.registerDate = registerDate;
		this.eventTypeID = eventTypeID;
		this.nearestStopID = nearestStopID;
		this.lastUpDateDate = lastUpDateDate;
		this.nearestStopMTS = nearestStopMTS;
		this.updNearestFlag = updNearestFlag;
		this.logFileID = logFileID;
		this.nearestPlanStopID = nearestPlanStopID;
		this.nearestPlanStopMTS = nearestPlanStopMTS;
		this.planStopAuth = planStopAuth;
		this.radiusToleranceMTS = radiusToleranceMTS;
		this.timeDiff = timeDiff;
	}

		
	
	
}
