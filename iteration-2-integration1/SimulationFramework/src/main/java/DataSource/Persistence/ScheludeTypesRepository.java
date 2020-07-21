package DataSource.Persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import SystemState.SITMFactory.SITMScheduleTypes;

@Repository
public interface ScheludeTypesRepository extends CrudRepository<SITMScheduleTypes, Long> {}
