package SystemState.Persistence;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import SystemState.SITMFactory.SITMPlanVersion;

@Repository
public class SITMPlanVersionDao implements ISITMPlanVersionDao {

	@PersistenceContext
	private EntityManager entityManager = (EntityManager) Persistence.createEntityManagerFactory("SITMPlanVersion");

	@Override
	public void save(SITMPlanVersion sitmPlanVersion) {
		entityManager.persist(sitmPlanVersion);

	}

	@Override
	public void update(SITMPlanVersion sitmPlanVersion) {
		entityManager.merge(sitmPlanVersion);

	}

	@Override
	public void remove(SITMPlanVersion sitmPlanVersion) {
		entityManager.remove(entityManager.find(SITMPlanVersion.class, sitmPlanVersion.getPlanVersionid()));

	}

	@Override
	public SITMPlanVersion findById(long id) {
		return entityManager.find(SITMPlanVersion.class, id);
	}

	@Override
	public List<SITMPlanVersion> findAll() {
		String query = "SELECT a FROM PLANVERSIONS a";
		return entityManager.createQuery(query).getResultList();
	}
	public List<Date> findAllDatesByPlanVersion(long planVersionID){
		String query="select operationaltravels.REGISTERDATE, operationaltravels.BUSID, buses.busID from operationaltravels,buses\r\n" + 
				"where buses.BUSID=operationaltravels.BUSID and buses.planversionID= 185 and rownum<100000 group by operationaltravels.REGISTERDATE, operationaltravels.BUSID, buses.busID;";
		return entityManager.createQuery(query).getResultList();
	}

}
