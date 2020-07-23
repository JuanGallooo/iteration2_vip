package com.simulationFramework.SystemState.SITMFactory;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;

import com.simulationFramework.SystemState.FactoryInerfaces.ILine;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name="LINES")
@BatchSize(size=25)
@Getter @Setter @ToString
public class SITMLine implements ILine,Serializable  {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="LINEID")
	private long lineID;
	
	@Column(name="SHORTNAME")
	private String shortName;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="PLANVERSIONID")
	private long planVersionID;
	

	public SITMLine(long lineID, String shortName, String description, long planVersionID) {
		super();
		this.lineID = lineID;
		this.shortName = shortName;
		this.description = description;
		this.planVersionID = planVersionID;
	}
	
	

}