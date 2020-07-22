package com.simulationFramework.SystemState.SITMFactory;

import java.io.Serializable;

import javax.persistence.Entity;

import org.hibernate.annotations.BatchSize;

import com.simulationFramework.SystemState.FactoryInerfaces.IBus;

import lombok.Data;
import lombok.NonNull;

@Data
@Entity
@BatchSize(size=25)
public class SITMBus implements IBus,Serializable  {

	private static final long serialVersionUID = 1L;
	
	@NonNull
	private long busId;
	@NonNull
	private long busNumber;
	@NonNull
	private String identification;
	@NonNull
	private long planVersionID;
	
	private double latitude;
	private double longitude;
	private long lineId;
	


}