package SystemState.Persistence;

import java.util.List;

import SystemState.SITMFactory.SITMTrip;

public interface ISITMTripDao {

	public void save(SITMTrip sitmTrip);
	public void update(SITMTrip sitmTrip);
	public void remove(SITMTrip sitmTrip);
	public SITMTrip findById(long id);
	public List<SITMTrip> findAll();
	
}
