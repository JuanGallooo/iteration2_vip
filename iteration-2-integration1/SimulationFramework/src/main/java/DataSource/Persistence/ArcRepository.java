package DataSource.Persistence;

import java.util.List;

import SystemState.SITMFactory.SITMArc;

public interface ISITMArcDao {

	public void save(SITMArc sitmArc);
	public void update(SITMArc sitmArc);
	public void remove(SITMArc sitmArc);
	public SITMArc findById(long id);
	public List<SITMArc> findAll();
}
