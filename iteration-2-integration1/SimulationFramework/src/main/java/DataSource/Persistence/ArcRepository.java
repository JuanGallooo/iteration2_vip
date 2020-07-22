package DataSource.Persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import SystemState.SITMFactory.SITMArc;

@Repository
public interface ArcRepository extends CrudRepository<SITMArc, Long> {}
