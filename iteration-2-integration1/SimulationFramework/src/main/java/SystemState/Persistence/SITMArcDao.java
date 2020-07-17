package SystemState.Persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import SystemState.SITMFactory.SITMArc;

@Repository
public class SITMArcDao implements ISITMArcDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(SITMArc sitmArc) {
		entityManager.persist(sitmArc);

	}

	@Override
	public void update(SITMArc sitmArc) {
		entityManager.merge(sitmArc);

	}

	@Override
	public void remove(SITMArc sitmArc) {
		entityManager.remove(entityManager.find(SITMArc.class, sitmArc.getArcID()));

	}

	@Override
	public SITMArc findById(long id) {
		return entityManager.find(SITMArc.class, id);
	}

	@Override
	public List<SITMArc> findAll() {
		String query = "SELECT a FROM ARCS a";
		return entityManager.createQuery(query).getResultList();
	}

}
