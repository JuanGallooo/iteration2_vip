package SystemState.Persistence;

import java.util.List;

import SystemState.SITMFactory.SITMStop;

public interface ISITMStopDao {

	public void save(SITMStop sitmStop);
	public void update(SITMStop sitmStop);
	public void remove(SITMStop sitmStop);
	public SITMStop findById(long id);
	public List<SITMStop> findAll();
	List<SITMStop> findAllStopsFromLine(long planversionID, long LINEID);
	
}
