package DataSource.Persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import SystemState.SITMFactory.SITMLine;

@Repository
public interface LineRepository extends CrudRepository<SITMLine, Long> {}
