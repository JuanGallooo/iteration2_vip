package DataSource.Persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import SystemState.SITMFactory.SITMTask;

@Repository
public class SITMTaskDao implements ISITMTaskDao{

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(SITMTask sitmTask) {
		entityManager.persist(sitmTask);
		
	}

	@Override
	public void update(SITMTask sitmTask) {
		entityManager.merge(sitmTask);
		
	}

	@Override
	public void remove(SITMTask sitmTask) {
		entityManager.remove(entityManager.find(SITMTask.class, sitmTask.getTaskID()));
		
	}

	@Override
	public SITMTask findById(long id) {
		return entityManager.find(SITMTask.class, id);

	}

	@Override
	public List<SITMTask> findAll() {
		String query = "SELECT a FROM TASKS a";
		return entityManager.createQuery(query).getResultList();
	}
	
	
	
}
