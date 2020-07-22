package DataSource.Persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import SystemState.SITMFactory.SITMTask;

@Repository
public interface TaskRepository extends CrudRepository<SITMTask, Long> {}
