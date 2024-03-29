package com.simulationFramework.SystemState.SITMFactory;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;

import com.simulationFramework.SystemState.FactoryInerfaces.IArc;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name="ARCS")
@BatchSize(size=25)
@Getter @Setter @ToString
public class SITMArc implements IArc,Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ARCID")
	private long arcID;
	
	@Column(name="STARTPOINT")
	private long starPoint;
	
	@Column(name="ENDPOINT")
	private long endPoint;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="ARCLENGTH")
	private String arcLength;
	
	@Column(name="PLANVERSIONID")
	private long planVersionID;


	public SITMArc() {
		super();
	}	
	
	public SITMArc(long arcID, long starPoint, long endPoint, String description, String arcLength,
			long planVersionID) {
		super();
		this.arcID = arcID;
		this.starPoint = starPoint;
		this.endPoint = endPoint;
		this.description = description;
		this.arcLength = arcLength;
		this.planVersionID = planVersionID;
	}



	
	
	

}