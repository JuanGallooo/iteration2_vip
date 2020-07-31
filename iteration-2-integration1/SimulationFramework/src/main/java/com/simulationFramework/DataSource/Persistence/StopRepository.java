package com.simulationFramework.DataSource.Persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.simulationFramework.SystemState.SITMFactory.SITMStop;

@Repository
public interface StopRepository extends CrudRepository<SITMStop, Long> {
	
	@Query(value="select s.STOPID, s.PLANVERSIONID, s.SHORTNAME, s.LONGNAME, s.GPS_X, s.GPS_Y, s.DECIMALLONGITUDE, s.DECIMALLATITUDE "
			 + "from operationaltravels o, stops s "
			 + "where o.LASTSTOPID = s.STOPID and s.PLANVERSIONID = ?1 and o.LINEID = ?2 and rownum<1000 "
			 + "group by s.STOPID, s.PLANVERSIONID, s.SHORTNAME, s.LONGNAME, s.GPS_X, s.GPS_Y, s.DECIMALLONGITUDE, s.DECIMALLATITUDE"
			 ,nativeQuery=true)
	public Iterable<SITMStop>findAllStopsbyLineID(long planVersionID,long lineID);


	
}