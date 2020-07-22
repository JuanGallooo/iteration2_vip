package com.simulationFramework.DataSource.Persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.simulationFramework.SystemState.SITMFactory.SITMArc;

@Repository
public interface ArcRepository extends CrudRepository<SITMArc, Long> {}
