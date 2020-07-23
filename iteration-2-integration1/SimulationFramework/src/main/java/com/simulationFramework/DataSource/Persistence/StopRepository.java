package com.simulationFramework.DataSource.Persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.simulationFramework.SystemState.SITMFactory.SITMStop;

@Repository
public interface StopRepository extends CrudRepository<SITMStop, Long> {
	
	/*
	@Query("select stops.STOPID,stops.PLANVERSIONID,stops.SHORTNAME,stops.GPS_X,stops.GPS_Y, stops.DECIMALLONGITUDE,stops.DECIMALLATITUDE "
		 + "from operationaltravels,planversions,stops "
		 + "where operationaltravels.LASTSTOPID = stops.STOPID and stops.planversionID = ?1 and operationaltravels.LINEID= ?2 and rownum<100000 "
		 + "group by stops.STOPID,stops.PLANVERSIONID,stops.SHORTNAME,stops.GPS_X,stops.GPS_Y, stops.DECIMALLONGITUDE,stops.DECIMALLATITUDE")
	public Iterable<SITMStop>findAllStopsbyLineID(long planVersionID,long lineID);
	*/
	
}