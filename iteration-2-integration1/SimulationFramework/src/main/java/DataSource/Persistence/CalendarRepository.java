package DataSource.Persistence;

import java.util.List;

import SystemState.SITMFactory.SITMCalendar;

public interface ISITMCalendarDao {

	public void save(SITMCalendar sitmCalendar);
	public void update(SITMCalendar sitmCalendar);
	public void remove(SITMCalendar sitmCalendar);
	public SITMCalendar findById(long id);
	public List<SITMCalendar> findAll();
	public List<SITMCalendar> findDatesByPlanversion(long planVersionID);
}
