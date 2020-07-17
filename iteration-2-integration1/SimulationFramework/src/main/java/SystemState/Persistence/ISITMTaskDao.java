package SystemState.Persistence;

import java.util.List;

import SystemState.SITMFactory.SITMTask;

public interface ISITMTaskDao {

	public void save(SITMTask sitmTask);
	public void update(SITMTask sitmTask);
	public void remove(SITMTask sitmTask);
	public SITMTask findById(long id);
	public List<SITMTask> findAll();
	
}
