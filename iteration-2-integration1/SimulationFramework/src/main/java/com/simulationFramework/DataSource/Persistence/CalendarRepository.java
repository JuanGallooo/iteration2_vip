package com.simulationFramework.DataSource.Persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.simulationFramework.SystemState.SITMFactory.SITMCalendar;

@Repository
public interface CalendarRepository extends CrudRepository<SITMCalendar, Long> {
	
	
	//@Query("select c from CALENDAR c where c.PLANVERSIONID = ?1")
	//public Iterable<SITMCalendar> findAllCalendarsbyPlanVersionID(long planVersionID);
	
}
