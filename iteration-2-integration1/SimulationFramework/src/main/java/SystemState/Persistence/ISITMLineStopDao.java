package SystemState.Persistence;

import java.util.List;

import SystemState.SITMFactory.SITMLineStop;

public interface ISITMLineStopDao {

	public void save(SITMLineStop sitmLineStop);
	public void update(SITMLineStop sitmLineStop);
	public void remove(SITMLineStop sitmLineStop);
	public SITMLineStop findById(long id);
	public List<SITMLineStop> findAll();
}
