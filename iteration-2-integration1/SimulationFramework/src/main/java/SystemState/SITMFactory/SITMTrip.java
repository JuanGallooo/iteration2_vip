package SystemState.SITMFactory;

import java.io.Serializable;

import javax.persistence.Entity;

import org.hibernate.annotations.BatchSize;

import SystemState.FactoryInterfaces.ITrip;
import lombok.Data;
import lombok.NonNull;

@Data
@Entity
@BatchSize(size=25)
public class SITMTrip implements ITrip,Serializable {

	private static final long serialVersionUID = 1L;
	
	@NonNull
	private long tripID;
	@NonNull
	private String tripSequence;
	@NonNull
	private String startTask;
	@NonNull
	private String description;
	@NonNull
	private boolean orientation;
	@NonNull
	private long planVersionID;
	@NonNull
	private long lineID;
	@NonNull
	private long startStopID;
	@NonNull
	private long endStopID;

}