package SystemState.SITMFactory;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;

import org.hibernate.annotations.BatchSize;

import SystemState.FactoryInterfaces.IPlanVersion;
import lombok.Data;
import lombok.NonNull;

@Data
@Entity
@BatchSize(size=25)
public class SITMPlanVersion implements IPlanVersion,Serializable  {

	private static final long serialVersionUID = 1L;
	
	@NonNull
	private long planVersionid;
	@NonNull
	private Date activationDate;
	@NonNull
	private Date creationDate;

}