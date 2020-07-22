package com.simulationFramework.DataSource.Persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.simulationFramework.SystemState.SITMFactory.SITMBus;

@Repository
public interface BusRepository extends CrudRepository<SITMBus, Long> {}
