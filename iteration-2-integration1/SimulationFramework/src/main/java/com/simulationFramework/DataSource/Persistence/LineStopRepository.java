package com.simulationFramework.DataSource.Persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.simulationFramework.SystemState.SITMFactory.SITMLineStop;

@Repository
public interface LineStopRepository extends CrudRepository<SITMLineStop, Long> {}