package DataSource.Persistence;

import java.util.List;

import SystemState.SITMFactory.SITMPlanVersion;

public interface ISITMPlanVersionDao {

	public void save(SITMPlanVersion sitmPlanVersion);
	public void update(SITMPlanVersion sitmPlanVersion);
	public void remove(SITMPlanVersion sitmPlanVersion);
	public SITMPlanVersion findById(long id);
	public List<SITMPlanVersion> findAll();
	
}
