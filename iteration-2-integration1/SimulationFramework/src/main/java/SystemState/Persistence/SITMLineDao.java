package SystemState.Persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import SystemState.SITMFactory.SITMLine;

public class SITMLineDao implements ISITMLineDao{

	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(SITMLine sitmLine) {
		entityManager.persist(sitmLine);
		
	}

	@Override
	public void update(SITMLine sitmLine) {
		entityManager.merge(sitmLine);
	
	}

	@Override
	public void remove(SITMLine sitmLine) {
		entityManager.remove(entityManager.find(SITMLine.class, sitmLine.getLineid()));
			
	}

	@Override
	public SITMLine findById(long id) {
		return entityManager.find(SITMLine.class, id);
	}

	@Override
	public List<SITMLine> findAll() {
		String query = "SELECT a FROM LINE a";
		return entityManager.createQuery(query).getResultList();
	}
	
	
	
}
