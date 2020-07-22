package DataSource.Persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import SystemState.SITMFactory.SITMOperationalTravels;

@Repository
public interface OperationalTravelsRepository extends CrudRepository<SITMOperationalTravels, Long> {}
