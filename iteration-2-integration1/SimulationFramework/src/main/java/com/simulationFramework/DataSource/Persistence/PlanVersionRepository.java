package com.simulationFramework.DataSource.Persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.simulationFramework.SystemState.SITMFactory.SITMPlanVersion;

@Repository
public interface PlanVersionRepository extends CrudRepository<SITMPlanVersion, Long>{}