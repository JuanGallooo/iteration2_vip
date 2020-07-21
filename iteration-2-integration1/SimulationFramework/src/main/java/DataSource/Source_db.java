package DataSource;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import DataSource.Persistence.ISITMCalendarDao;
//import DataSource.Persistence.ISITMOperationalTravelsDao;
//import DataSource.Persistence.ISITMStopDao;
import DataSource.Persistence.PlanVersionRepository;
import SystemState.FactoryInterfaces.ICalendar;
import SystemState.FactoryInterfaces.IStop;
import SystemState.SITMFactory.SITMCalendar;
import SystemState.SITMFactory.SITMPlanVersion;
import SystemState.SITMFactory.SITMStop;


@Service
public class Source_db {
	
//	private ISITMStopDao stopsDao;
	
	@Autowired
	private PlanVersionRepository planVersionsDao;
//	private ISITMCalendarDao calendarDao;
//	private ISITMOperationalTravelsDao operationalTravelsDao;
	public String[] getHeaders() {
		String[] headers= new String[26];
		headers[0]="OPERTRAVELID";
		headers[1]="BUSID";
		headers[2]="LASTSTOPID";
		headers[3]="GPS_X";
		headers[4]="GPS_Y";
		headers[5]="DEVIATIONTIME";
		headers[6]="ODOMETERVALUE";
		headers[7]="LINEID";
		headers[8]="TASKID";
		headers[9]="TRIPID";
		headers[10]="RIGHTCOURSE";
		headers[11]="ORIENTATION";
		headers[12]="EVENTDATE";
		headers[13]="EVENTTIME";
		headers[14]="REGISTERDATE";
		headers[15]="EVENTTYPEID";
		headers[16]="NEARESTSTOPID";
		headers[17]="LASTUPDATEDATE";
		headers[18]="NEARESTSTOPMTS";
		headers[19]="UPDNEARESTFLAG";
		headers[20]="LOGFILEID";
		headers[21]="NEARESTPLANSTOPID";
		headers[22]="NEARESTPLANSTOPMTS";
		headers[23]="PLANSTOPAUTH";
		headers[24]="RADIUSTOLERANCEMTS";
		headers[25]="TIMEDIFF";
		return headers;
	}

	public HashMap<String, String> fetchNextRow(Date initialDate, Date finalDate, long line,long planVerionID) {
		HashMap<String, String> data = new HashMap<String, String>();
		String[] headers = getHeaders();
		String[] row= new String[26];
//		row= operationalTravelsDao.getOperationalTravelBydateByLineByPlanVersion(initialDate,finalDate,line,planVerionID);
		for (int i = 0; i < headers.length; i++) {
			data.put(headers[i], row[i]);
			
		}
		return data;
	}

	/**
	 * Show all stops from planversionID and LINEID
	 * @param lineId
	 * @return
	 */
	public ArrayList<IStop> getStopsByLine(long lineId, long planVersionId) {
//		SITMStop
//		List<SITMStop> lista= stopsDao.findAllStopsFromLine(planVersionId, lineId);
//		ArrayList<IStop> arr = new ArrayList<IStop>(lista);
//		return arr;
		return null;
	}

	public Iterable<SITMPlanVersion> getPlanVerions() {
		return planVersionsDao.findAll();
	}

	public ArrayList<ICalendar> getDatesByPlanVersion(long planVersionID) {
//		List<SITMCalendar> lista= calendarDao.findDatesByPlanversion(planVersionID);
//		ArrayList<ICalendar> arr = new ArrayList<ICalendar>(lista);
//		return arr;
		return null;
	}

}
