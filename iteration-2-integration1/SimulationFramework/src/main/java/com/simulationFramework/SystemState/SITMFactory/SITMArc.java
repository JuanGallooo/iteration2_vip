package com.simulationFramework.SystemState.SITMFactory;

import java.io.Serializable;

import javax.persistence.Entity;

import org.hibernate.annotations.BatchSize;

import com.simulationFramework.SystemState.FactoryInerfaces.IArc;

import lombok.Data;
import lombok.NonNull;

@Data
@Entity
@BatchSize(size=25)
public class SITMArc implements IArc,Serializable {

	private static final long serialVersionUID = 1L;
	
	@NonNull
	private long arcID;
	@NonNull
	private long starPoint;
	@NonNull
	private long endPoint;
	@NonNull
	private String description;
	@NonNull
	private String ArcLength;
	@NonNull
	private long planVersionID;

}