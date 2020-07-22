package com.simulationFramework.SystemState.SITMFactory;
import lombok.Data;
import lombok.NonNull;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;

import org.hibernate.annotations.BatchSize;

import com.simulationFramework.SystemState.FactoryInerfaces.ILineStop;

@Data
@Entity
@BatchSize(size=25)
public class SITMLineStop implements ILineStop,Serializable{
	private static final long serialVersionUID = 1L;
	@NonNull
	private long lineStopid;
	@NonNull
	private long stopsequence;
	@NonNull
	private long orientation;
	@NonNull
	private long lineid;
	@NonNull
	private long stopid;
	@NonNull
	private long planVersionid;
	@NonNull
	private long lineVariant;
	@NonNull
	private Date registerDate;
	@NonNull
	private long lineVariantType;
}
