package SystemState.SITMFactory;

import java.io.Serializable;

import javax.persistence.Entity;

import org.hibernate.annotations.BatchSize;

import SystemState.FactoryInterfaces.ILine;
import lombok.Data;
import lombok.NonNull;

@Data
@Entity
@BatchSize(size=25)
public class SITMLine implements ILine,Serializable  {

	private static final long serialVersionUID = 1L;
	
	@NonNull
	private long lineid;
	@NonNull
	private String shortName;
	@NonNull
	private String description;
	@NonNull
	private long planVersionID;
	
	@Override
	public String toString() {
		return shortName+" "+description; 
	}

}