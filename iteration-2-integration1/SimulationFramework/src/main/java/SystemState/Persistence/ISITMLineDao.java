package SystemState.Persistence;

import java.util.List;

import SystemState.SITMFactory.SITMLine;

public interface ISITMLineDao {

	public void save(SITMLine sitmLine);
	public void update(SITMLine sitmLine);
	public void remove(SITMLine sitmLine);
	public SITMLine findById(long id);
	public List<SITMLine> findAll();
}
