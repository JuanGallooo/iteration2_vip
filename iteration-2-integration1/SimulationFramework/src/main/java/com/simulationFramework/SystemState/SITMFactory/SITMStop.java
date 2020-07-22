package com.simulationFramework.SystemState.SITMFactory;

import java.io.Serializable;

import javax.persistence.Entity;

import org.hibernate.annotations.BatchSize;

import com.simulationFramework.SystemState.FactoryInerfaces.IStop;

import lombok.Data;
import lombok.NonNull;

@Data
@Entity
@BatchSize(size=25)
public class SITMStop implements IStop,Serializable  {
	
	private static final long serialVersionUID = 1L;
	
	@NonNull
	private String longName;
	@NonNull
	private double GPSX;
	@NonNull
	private double GPSY;
	@NonNull
	private double decimalLongitude;
	@NonNull
	private double decimalLatitude;
	@NonNull
	private long planVersionID;
	@NonNull
	private long stopId;
	@NonNull
	private String shortName;

}