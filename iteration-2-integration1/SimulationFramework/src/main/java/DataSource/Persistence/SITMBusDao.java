package DataSource.Persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import SystemState.SITMFactory.SITMBus;

@Repository
public class SITMBusDao implements ISITMBusDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(SITMBus sitmBus) {
		entityManager.persist(sitmBus);

	}

	@Override
	public void update(SITMBus sitmBus) {
		entityManager.merge(sitmBus);

	}

	@Override
	public void remove(SITMBus sitmBus) {
		entityManager.remove(entityManager.find(SITMBus.class, sitmBus.getBusId()));

	}

	@Override
	public SITMBus findById(long id) {
		return entityManager.find(SITMBus.class, id);
	}

	@Override
	public List<SITMBus> findAll() {
		String query = "SELECT a FROM BUSES a";
		return entityManager.createQuery(query).getResultList();
	}

}
