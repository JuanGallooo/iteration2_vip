package com.simulationFramework.DataSource.Persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.simulationFramework.SystemState.SITMFactory.SITMOperationalTravels;

@Repository
public interface OperationalTravelsRepository extends CrudRepository<SITMOperationalTravels, Long> {}
