package com.simulationFramework.DataSource.Persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.simulationFramework.SystemState.SITMFactory.SITMScheduleTypes;

@Repository
public interface ScheludeTypesRepository extends CrudRepository<SITMScheduleTypes, Long> {}
