package SystemState.Persistence;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.annotations.BatchSize;
import org.springframework.stereotype.Repository;

import SystemState.SITMFactory.SITMOperationalTravels;

@Repository
public class SITMOperationalTravelsDao implements ISITMOperationalTravelsDao{

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(SITMOperationalTravels sitmOpt) {
		entityManager.persist(sitmOpt);
	}

	@Override
	public void update(SITMOperationalTravels sitmOpt) {
		entityManager.merge(sitmOpt);
	}

	@Override
	public void remove(SITMOperationalTravels sitmOpt) {
		entityManager.remove(entityManager.find(SITMOperationalTravels.class,sitmOpt.getOPERTRAVELID()));
	}

	@Override
	public SITMOperationalTravels findById(long id) {
		return entityManager.find(SITMOperationalTravels.class, id);
	}

	@Override
	public List<SITMOperationalTravels> findAll() {
		String query = "SELECT a FROM operationaltravels a";
		return entityManager.createQuery(query).getResultList();
	}
	@BatchSize(size=2)
	@Override
	public String[] getOperationalTravelBydateByLineByPlanVersion(Date initialDate, Date finalDate, long line,
			long planVerionID) {
		String query = "CONSULTA";
		List<SITMOperationalTravels> consulta= entityManager.createQuery(query).getResultList();
		return null;
	}

}
