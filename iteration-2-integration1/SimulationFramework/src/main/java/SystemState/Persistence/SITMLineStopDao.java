
package SystemState.Persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import SystemState.SITMFactory.SITMLineStop;

@Repository
public class SITMLineStopDao implements ISITMLineStopDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(SITMLineStop sitmLineStop) {
		entityManager.persist(sitmLineStop);
		
	}

	@Override
	public void update(SITMLineStop sitmLineStop) {
		entityManager.merge(sitmLineStop);
	}

	@Override
	public void remove(SITMLineStop sitmLineStop) {
		entityManager.remove(entityManager.find(SITMLineStop.class, sitmLineStop.getLineStopid()));
	}

	@Override
	public SITMLineStop findById(long id) {
		return entityManager.find(SITMLineStop.class, id);
	}

	@Override
	public List<SITMLineStop> findAll() {
		String query = "SELECT a FROM LINESTOPS a";
		return entityManager.createQuery(query).getResultList();
	}
	
	
	
	
}
