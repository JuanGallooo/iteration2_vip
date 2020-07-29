package com.simulationFramework.DataSource.Persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.simulationFramework.SystemState.SITMFactory.SITMStop;

@Repository
public interface StopRepository extends CrudRepository<SITMStop, Long> {
	
	
//	@Query(value="select stops.stopID,stops.planversionID,stops.shortName,stops.GPS_X,stops.GPS_Y, stops.decimalLongitude,stops.decimalLatitude "
//		 + "from operationaltravels ,stops "
//		 + "where operationaltravels.laststopID = stops.stopID and stops.planversionID = ?1 and operationaltravels.lineID = ?2 and rownum<100000 "
//		 + "group by stops.stopId,stops.planversionID,stops.shortName,stops.GPS_X,stops.GPS_Y, stops.decimalLongitude,stops.decimalLatitude",
//		 nativeQuery=true)
	@Query(value="select s "
			 + "from stops s "
			 + "inner join operationaltravels o on o.laststopID = s.stopID "
			 + "where s.planversionID = ?1 and rownum<100000"
			 ,nativeQuery=true)
	public Iterable<SITMStop>findAllStopsbyLineID(long planVersionID,long lineID);

	
}