package com.simulationFramework.SystemState.SITMFactory;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;

import com.simulationFramework.SystemState.FactoryInerfaces.ILineStop;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name="LINESTOPS")
@BatchSize(size=25)
@Getter @Setter @ToString
public class SITMLineStop implements ILineStop,Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="LINESTOPID")
	private long lineStopID;
	
	@Column(name="STOPSEQUENCE")
	private long stopsequence;
	
	@Column(name="ORIENTATION")
	private long orientation;
	
	@Column(name="LINEID")
	private long lineID;
	
	@Column(name="STOPID")
	private long stopID;
	
	@Column(name="PLANVERSIONID")
	private long planVersionID;
	
	@Column(name="LINEVARIANT")
	private long lineVariant;
	
	@Column(name="REGISTERDATE")
	private Date registerDate;
	
	@Column(name="LINEVARIANTTYPE")
	private long lineVariantType;

	public SITMLineStop () {
		super();
	}
	
	public SITMLineStop(long lineStopID, long stopsequence, long orientation, long lineID, long stopID,
			long planVersionID, long lineVariant, Date registerDate, long lineVariantType) {
		super();
		this.lineStopID = lineStopID;
		this.stopsequence = stopsequence;
		this.orientation = orientation;
		this.lineID = lineID;
		this.stopID = stopID;
		this.planVersionID = planVersionID;
		this.lineVariant = lineVariant;
		this.registerDate = registerDate;
		this.lineVariantType = lineVariantType;
	}
	
	
}
