package com.simulationFramework.DataSource.Persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.simulationFramework.SystemState.SITMFactory.SITMCalendar;

@Repository
public interface CalendarRepository extends CrudRepository<SITMCalendar, Long> {
	
	
	@Query(value="select * from Calendar  where planVersionID = ?1 order by operationday", nativeQuery=true)
	Iterable<SITMCalendar> findAllCalendarsbyPlanVersionID(long planVersionID);
	
}
