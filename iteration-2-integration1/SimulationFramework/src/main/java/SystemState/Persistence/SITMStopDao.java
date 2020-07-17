package SystemState.Persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.annotations.BatchSize;
import org.springframework.stereotype.Repository;

import SystemState.SITMFactory.SITMStop;

@Repository
public class SITMStopDao implements ISITMStopDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(SITMStop sitmStop) {
		entityManager.persist(sitmStop);

	}

	@Override
	public void update(SITMStop sitmStop) {
		entityManager.merge(sitmStop);

	}

	@Override
	public void remove(SITMStop sitmStop) {
		entityManager.remove(entityManager.find(SITMStop.class, sitmStop.getStopId())); // id:2

	}

	@Override
	public SITMStop findById(long id) {
		return entityManager.find(SITMStop.class, id);

	}

	@Override
	public List<SITMStop> findAll() {
		String query = "SELECT a FROM STOPS a";
		return entityManager.createQuery(query).getResultList();
	}
	@BatchSize(size=25)
	@Override
	public List<SITMStop> findAllStopsFromLine(long planversionID,long LINEID) {
		String query = "select stops.STOPID,stops.PLANVERSIONID,stops.SHORTNAME,stops.GPS_X,stops.GPS_Y, stops.DECIMALLONGITUDE,stops.DECIMALLATITUDE from operationaltravels,stops,planversions where operationaltravels.LASTSTOPID=stops.STOPID and stops.planversionID="+planversionID+" and operationaltravels.LINEID="+LINEID+" and rownum<100000 group by stops.STOPID,stops.PLANVERSIONID,stops.SHORTNAME,stops.GPS_X,stops.GPS_Y, stops.DECIMALLONGITUDE,stops.DECIMALLATITUDE";
		return entityManager.createQuery(query).getResultList();
	}

}
