package DataSource.Persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import SystemState.SITMFactory.SITMStop;

@Repository
public interface StopRepository extends CrudRepository<SITMStop, Long> {}
