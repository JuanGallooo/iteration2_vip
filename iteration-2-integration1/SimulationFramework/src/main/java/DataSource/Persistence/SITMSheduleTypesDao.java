package DataSource.Persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import SystemState.SITMFactory.SITMScheduleTypes;

@Repository
public class SITMSheduleTypesDao implements ISITMScheludeTypesDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(SITMScheduleTypes sitmScheduleType) {
		entityManager.persist(sitmScheduleType);
		
	}

	@Override
	public void update(SITMScheduleTypes sitmScheduleType) {
		entityManager.merge(sitmScheduleType);

	}

	@Override
	public void remove(SITMScheduleTypes sitmScheduleType) {
		entityManager.remove(entityManager.find(SITMScheduleTypes.class, sitmScheduleType.getScheduleTypesid()));
		
	}

	@Override
	public SITMScheduleTypes findById(long id) {
		return entityManager.find(SITMScheduleTypes.class, id);

	}

	@Override
	public List<SITMScheduleTypes> findAll() {
		String query = "SELECT a FROM BUS a";
		return entityManager.createQuery(query).getResultList();
	}
	
	
	
}
