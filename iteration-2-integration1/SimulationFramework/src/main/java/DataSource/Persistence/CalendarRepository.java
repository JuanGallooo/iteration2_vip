package DataSource.Persistence;

import java.sql.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import SystemState.SITMFactory.SITMCalendar;

@Repository
public interface CalendarRepository extends CrudRepository<SITMCalendar, Long> {
	
	@Query("select * from calendar where planversionid = ?1")
	public Iterable<SITMCalendar> findAllCalendarsByPlanVersion(long planVersionID);
}
