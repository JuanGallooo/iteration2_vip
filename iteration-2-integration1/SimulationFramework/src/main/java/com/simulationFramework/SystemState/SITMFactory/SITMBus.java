package com.simulationFramework.SystemState.SITMFactory;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;

import com.simulationFramework.SystemState.FactoryInerfaces.IBus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name="BUSES")
@BatchSize(size=25)
@Getter @Setter @ToString
public class SITMBus implements IBus,Serializable  {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="BUSID")
	private long busID;
	
	@Column(name="BUSNUMBER")
	private long busNumber;
	
	@Column(name="IDENTIFICATION")
	private String identification;
	
	@Column(name="PLANVERSIONID")
	private long planVersionID;
	
	@Column(name="BUSTYPEID")
	private long busTypeID;
	
	private double latitude;
	
	private double longitude;
	
	private long lineId;

	public SITMBus(long busID, long busNumber, String identification, long busTypeID, long planVersionID) {
		super();
		this.busID = busID;
		this.busNumber = busNumber;
		this.identification = identification;
		this.busTypeID = busTypeID;
		this.planVersionID = planVersionID;
	}
	
	


}