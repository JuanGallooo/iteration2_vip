package SystemState.SITMFactory;

import java.io.Serializable;

import javax.persistence.Entity;

import org.hibernate.annotations.BatchSize;

import SystemState.FactoryInterfaces.ITask;
import lombok.Data;
import lombok.NonNull;

@Data
@Entity
@BatchSize(size=25)
public class SITMTask implements ITask,Serializable  {

	private static final long serialVersionUID = 1L;
	
	@NonNull
	private long taskID;
	@NonNull
	private long scheduleTypeID;
	@NonNull
	private long LineID;
	@NonNull
	private long planVersionID;

}