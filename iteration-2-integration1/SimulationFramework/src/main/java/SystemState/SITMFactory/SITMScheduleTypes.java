package SystemState.SITMFactory;

import java.io.Serializable;

import javax.persistence.Entity;

import org.hibernate.annotations.BatchSize;

import SystemState.FactoryInterfaces.IScheduleTypes;
import lombok.Data;
import lombok.NonNull;

@Data
@Entity
@BatchSize(size=25)
public class SITMScheduleTypes implements IScheduleTypes,Serializable  {

	private static final long serialVersionUID = 1L;
	
	@NonNull
	private long scheduleTypesid;
	@NonNull
	private String shortName;
	@NonNull
	private String description;
	@NonNull
	private long planVersionID;

}