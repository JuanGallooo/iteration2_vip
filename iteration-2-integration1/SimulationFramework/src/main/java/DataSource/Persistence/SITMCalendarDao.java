package DataSource.Persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import SystemState.SITMFactory.SITMCalendar;

@Repository
public class SITMCalendarDao implements ISITMCalendarDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(SITMCalendar sitmCalendar) {
		entityManager.persist(sitmCalendar);		
	}

	@Override
	public void update(SITMCalendar sitmCalendar) {
		entityManager.merge(sitmCalendar);
		
	}

	@Override
	public void remove(SITMCalendar sitmCalendar) {
		entityManager.remove(entityManager.find(SITMCalendar.class, sitmCalendar.getCalendarID())); //id:2
		
	}

	@Override
	public SITMCalendar findById(long id) {
		return entityManager.find(SITMCalendar.class, id);
	}

	@Override
	public List<SITMCalendar> findAll() {
		String query = "SELECT a FROM CALENDAR a";
		return entityManager.createQuery(query).getResultList();
	}
	@Override
	public List<SITMCalendar> findDatesByPlanversion(long planVersionID){
		String query = "select * from calendar where planversionid = "+planVersionID;
		return entityManager.createQuery(query).getResultList();
	}
	
	
	
}
