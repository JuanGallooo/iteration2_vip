package com.simulationFramework.SystemState.SITMFactory;

import java.util.Date;

import javax.persistence.Entity;

import org.hibernate.annotations.BatchSize;

import lombok.Data;
import lombok.NonNull;

@Data
@Entity
@BatchSize(size=25)
public class SITMOperationalTravels {
	@NonNull
	private long OPERTRAVELID;
	@NonNull
	private long BUSID;
	private long LASTSTOPID;
	private String GPS_X;
	private String GPS_Y;
	private long DEVIATIONTIME;
	@NonNull
	private long ODOMETERVALUE;
	private long LINEID;
	private long TASKID;
	private long TRIPID;
	private long RIGHTCOURSE;
	private long ORIENTATION;
	@NonNull
	private long EVENTDATE;
	@NonNull
	private long EVENTTIME;
	@NonNull
	private long REGISTERDATE;
	@NonNull
	private long EVENTTYPEID;
	private long NEARESTSTOPID;
	private long LASTUPDATEDATE;
	private long NEARESTSTOPMTS;
	private String UPDNEARESTFLAG;
	@NonNull
	private long LOGFILEID;
	private long NEARESTPLANSTOPID;
	private long NEARESTPLANSTOPMTS;
	private String PLANSTOPAUTH;
	private long RADIUSTOLERANCEMTS;
	private long TIMEDIFF; 
}
