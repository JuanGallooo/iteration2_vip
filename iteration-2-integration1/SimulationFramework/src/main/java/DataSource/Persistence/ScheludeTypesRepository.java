package DataSource.Persistence;

import java.util.List;

import SystemState.SITMFactory.SITMScheduleTypes;

public interface ISITMScheludeTypesDao {

	public void save(SITMScheduleTypes sitmScheduleType);
	public void update(SITMScheduleTypes sitmScheduleType);
	public void remove(SITMScheduleTypes sitmScheduleType);
	public SITMScheduleTypes findById(long id);
	public List<SITMScheduleTypes> findAll();
}
