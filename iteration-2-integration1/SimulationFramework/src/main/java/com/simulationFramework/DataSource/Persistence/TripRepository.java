package com.simulationFramework.DataSource.Persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.simulationFramework.SystemState.SITMFactory.SITMTrip;

@Repository
public interface TripRepository extends CrudRepository<SITMTrip, Long> {}
