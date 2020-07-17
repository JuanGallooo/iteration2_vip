package SystemState.Persistence;

import java.util.Date;
import java.util.List;

import SystemState.SITMFactory.SITMOperationalTravels;

public interface ISITMOperationalTravelsDao {
	public void save(SITMOperationalTravels sitmOpt);
	public void update(SITMOperationalTravels sitmOpt);
	public void remove(SITMOperationalTravels sitmOpt);
	public SITMOperationalTravels findById(long id);
	public List<SITMOperationalTravels> findAll();
	public String[] getOperationalTravelBydateByLineByPlanVersion(Date initialDate, Date finalDate, long line,
			long planVerionID);
}
