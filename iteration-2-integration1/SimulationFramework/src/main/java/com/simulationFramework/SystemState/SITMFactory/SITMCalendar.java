package com.simulationFramework.SystemState.SITMFactory;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;

import org.hibernate.annotations.BatchSize;

import com.simulationFramework.SystemState.FactoryInerfaces.ICalendar;

import lombok.Data;
import lombok.NonNull;

@Data
@Entity
@BatchSize(size=25)
public class SITMCalendar implements ICalendar,Serializable  {

	private static final long serialVersionUID = 1L;
	
	@NonNull
	private long calendarID;
	@NonNull
	private Date operationDate;
	@NonNull
	private String scheduleTypeID;
	@NonNull
	private long planVersionID;

}