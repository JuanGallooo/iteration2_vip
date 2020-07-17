package SystemState.Persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import SystemState.SITMFactory.SITMTrip;

@Repository
public class SITMTripDao implements ISITMTripDao{

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(SITMTrip sitmTrip) {
		entityManager.persist(sitmTrip);
		
	}

	@Override
	public void update(SITMTrip sitmTrip) {
		entityManager.merge(sitmTrip);
		
	}

	@Override
	public void remove(SITMTrip sitmTrip) {
		entityManager.remove(entityManager.find(SITMTrip.class, sitmTrip.getTripID()));		
	}

	@Override
	public SITMTrip findById(long id) {
		return entityManager.find(SITMTrip.class, id);

	}

	@Override
	public List<SITMTrip> findAll() {
		String query = "SELECT a FROM TRIPS a";
		return entityManager.createQuery(query).getResultList();
	} 
	
	
	
}
