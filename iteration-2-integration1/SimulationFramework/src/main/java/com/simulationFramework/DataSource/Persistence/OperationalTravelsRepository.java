package com.simulationFramework.DataSource.Persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.simulationFramework.SystemState.SITMFactory.SITMOperationalTravels;

@Repository
public interface OperationalTravelsRepository extends CrudRepository<SITMOperationalTravels, Long> {
	
	//@Query(value="select operationaltravels.eventdate, operationaltravels.busid, operationaltravels.laststopid "
	@Query(value="select o.OPERTRAVELID, o.BUSID, o.LASTSTOPID, o.GPS_X, o.GPS_Y, o.DEVIATIONTIME, o.ODOMETERVALUE, o.LINEID, o.TASKID, o.TRIPID, o.RIGHTCOURSE, o.ORIENTATION, o.EVENTDATE, o.EVENTTIME, o.REGISTERDATE, o.EVENTTYPEID, o.NEARESTSTOPID, o.LASTUPDATEDATE, o.NEARESTSTOPMTS, o.UPDNEARESTFLAG, o.LOGFILEID, o.NEARESTPLANSTOPID, o.NEARESTPLANSTOPMTS, o.PLANSTOPAUTH, o.RADIUSTOLERANCEMTS, o.TIMEDIFF "
			+ "from operationaltravels o "
			+ "where o.eventdate between '01/08/19' and  '02/08/19' and rownum<1000 "
			,nativeQuery=true)
	public Iterable<SITMOperationalTravels>findAllOP();
}
