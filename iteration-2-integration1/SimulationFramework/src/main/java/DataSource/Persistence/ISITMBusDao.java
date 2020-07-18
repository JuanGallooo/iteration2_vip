package DataSource.Persistence;

import java.util.List;

import SystemState.SITMFactory.SITMBus;

public interface ISITMBusDao {
	
	public void save(SITMBus sitmBus);
	public void update(SITMBus sitmBus);
	public void remove(SITMBus sitmBus);
	public SITMBus findById(long id);
	public List<SITMBus> findAll();

}
