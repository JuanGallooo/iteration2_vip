package com.simulationFramework.SystemState.SITMFactory;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;

import com.simulationFramework.SystemState.FactoryInerfaces.ICalendar;
import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name="CALENDAR")
@BatchSize(size=25)
@Getter @Setter @ToString
public class SITMCalendar implements ICalendar,Serializable  {

	private static final long serialVersionUID = 1L;
	
	@Id
	@NotNull
	@Column(name="CALENDARID")
	private long calendarID;
	
	@NotNull
	@Column(name="OPERATIONDAY")
	private Date operationDay;
	
	@NotNull
	@Column(name="SCHEDULETYPEID")
	private long scheduleTypeID;
	
	@NotNull
	@Column(name="PLANVERSIONID")
	private long planVersionID;

	public SITMCalendar () {
		super();
	}
	
	public SITMCalendar(long calendarID, Date operationDay, long scheduleTypeID, long planVersionID) {
		super();
		this.calendarID = calendarID;
		this.operationDay = operationDay;
		this.scheduleTypeID = scheduleTypeID;
		this.planVersionID = planVersionID;
	}
	
	

}