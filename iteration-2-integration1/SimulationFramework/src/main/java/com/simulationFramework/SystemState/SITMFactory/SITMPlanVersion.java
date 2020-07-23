package com.simulationFramework.SystemState.SITMFactory;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;

import com.simulationFramework.SystemState.FactoryInerfaces.IPlanVersion;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="PLANVERSIONS")
@BatchSize(size=25)
@Getter @Setter @ToString
public class SITMPlanVersion implements IPlanVersion,Serializable  {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="PLANVERSIONID")
	private long planVersionID;

	@Column(name="ACTIVATIONDATE")
	private Date activationDate;

	@Column(name="CREATIONDATE")
	private Date creationDate;

	public SITMPlanVersion () {
		super();
	}
	
	public SITMPlanVersion(long planVersionID, Date activationDate, Date creationDate) {
		super();
		this.planVersionID = planVersionID;
		this.activationDate = activationDate;
		this.creationDate = creationDate;
	}
	
	

}