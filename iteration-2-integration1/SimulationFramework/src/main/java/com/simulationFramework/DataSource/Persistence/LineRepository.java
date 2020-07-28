package com.simulationFramework.DataSource.Persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.simulationFramework.SystemState.SITMFactory.SITMLine;

@Repository
public interface LineRepository extends CrudRepository<SITMLine, Long> {
	
	@Query(value="select * from lines where planversionID=?1",nativeQuery=true)
	Iterable<SITMLine> findAllLinesbyPlanVersionID(long planVersionID);
}
