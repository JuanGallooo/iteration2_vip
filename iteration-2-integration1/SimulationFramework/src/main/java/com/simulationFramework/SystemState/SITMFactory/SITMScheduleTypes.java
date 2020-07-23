package com.simulationFramework.SystemState.SITMFactory;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;

import com.simulationFramework.SystemState.FactoryInerfaces.IScheduleTypes;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name="SCHEDULETYPES")
@BatchSize(size=25)
@Getter @Setter @ToString
public class SITMScheduleTypes implements IScheduleTypes,Serializable  {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="SCHEDULETYPEID")
	private long scheduleTypeID;
	
	@Column(name="SHORTNAME")
	private String shortName;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="PLANVERSIONID")
	private long planVersionID;

	public SITMScheduleTypes(long scheduleTypeID, String shortName, String description, long planVersionID) {
		super();
		this.scheduleTypeID = scheduleTypeID;
		this.shortName = shortName;
		this.description = description;
		this.planVersionID = planVersionID;
	}
	
	

}