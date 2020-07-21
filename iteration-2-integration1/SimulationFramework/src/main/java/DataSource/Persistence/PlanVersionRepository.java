package DataSource.Persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import SystemState.SITMFactory.SITMPlanVersion;

@Repository
public interface PlanVersionRepository extends CrudRepository<SITMPlanVersion, Long>{}