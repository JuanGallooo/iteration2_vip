package com.simulationFramework.DataSource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Locale;

import com.simulationFramework.SystemState.SITMFactory.SITMCalendar;
import com.simulationFramework.SystemState.SITMFactory.SITMLine;
import com.simulationFramework.SystemState.SITMFactory.SITMLineStop;
import com.simulationFramework.SystemState.SITMFactory.SITMOperationalTravels;
import com.simulationFramework.SystemState.SITMFactory.SITMPlanVersion;
import com.simulationFramework.SystemState.SITMFactory.SITMStop;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Source_csv implements IDateSource {

	private File sourceFile;
	private String split;
	private int currentPosition = 1;

	public Source_csv(File sourceFile, String split) {
		this.sourceFile = sourceFile;
		this.split = split;
	}

	@Override
	public String[] getHeaders() {
		
		String[] headers = new String[10];
		headers[0] = "opertravelID";
		headers[1] = "busID";
		headers[2] = "laststopID";
		headers[3] = "GPS_X";
		headers[4] = "GPS_Y";
		headers[5] = "odometervalue";
		headers[6] = "lineID";
		headers[7] = "taskID";
		headers[8] = "tripID";
		headers[9] = "eventDate";
		
		return headers;
	}

	@Override
	public HashMap<String, String> fetchNextRow(Date initialDate, Date finalDate, long line, long planVerionID)
			throws Exception {

		BufferedReader br;
		String line2 = "";

		String[] row = null;
		String[] headers = getHeaders();
		HashMap<String, String> data = new HashMap<String, String>();

		try {

			br = new BufferedReader(new FileReader(sourceFile));

			for (int i = 0; i <= currentPosition; i++) {
				line2 = br.readLine();
			}

			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (line2 == null || line2.equals("")) {

			throw new Exception("CSV source is already empty");

		} else {

			row = line2.split(split);

			for (int i = 0; i < row.length; i++) {
				data.put(headers[i], row[i]);
			}

			currentPosition++;
			return data;
		}

	}

	@Override
	public ArrayList<SITMPlanVersion> findAllPlanVersions() {

		String path = new File("DataCSV/planversions.csv").getAbsolutePath();
		ArrayList<SITMPlanVersion> plans = new ArrayList<>();
		BufferedReader br;

		try {
			br = new BufferedReader(new FileReader(path));

			String[] columns = null;
			String line = br.readLine();
			line = br.readLine();

			while (line != null) {
				columns = line.split(";");

				if (!columns[0].isEmpty()) {

					long planVersionID = Long.parseLong(columns[0]);
					DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy", Locale.US);
					Date activationDate = new Date(dateFormat.parse(columns[1]).getTime());
					Date creationDate = new Date(dateFormat.parse(columns[2]).getTime());

					SITMPlanVersion plan = new SITMPlanVersion(planVersionID, activationDate, creationDate);
					plans.add(plan);
				}
				line = br.readLine();
			}

			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return plans;
	}

	@Override
	public ArrayList<SITMCalendar> findAllCalendarsByPlanVersion(long planVersionID) {

		String path = new File("DataCSV/calendar.csv").getAbsolutePath();
		ArrayList<SITMCalendar> calendars = new ArrayList<>();

		BufferedReader br;

		try {

			br = new BufferedReader(new FileReader(path));
			String[] columns = null;
			String line = br.readLine();
			line = br.readLine();

			while (line != null) {
				columns = line.split(";");

				if (!columns[0].isEmpty() && columns[3].equals(planVersionID + "")) {

					long calendarID = Long.parseLong(columns[0]);
					DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy", Locale.US);
					Date operationDate = new Date(dateFormat.parse(columns[1]).getTime());
					long scheduleTypeID = Long.parseLong(columns[2]);
					calendars.add(new SITMCalendar(calendarID, operationDate, scheduleTypeID, planVersionID));
				}

				line = br.readLine();
			}

			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		Collections.sort(calendars,new Comparator<SITMCalendar>() {
			public int compare(SITMCalendar o1, SITMCalendar o2) {
		         return o1.getOperationDay().compareTo(o2.getOperationDay());
		     }
		});
		
		return calendars;
	}

	@Override
	public ArrayList<SITMLine> findAllLinesByPlanVersion(long planVersionID) {

		String path = new File("DataCSV/lines.csv").getAbsolutePath();
		ArrayList<SITMLine> lines = new ArrayList<>();

		BufferedReader br;

		try {

			br = new BufferedReader(new FileReader(path));
			String[] columns = null;
			String line = br.readLine();
			line = br.readLine();

			while (line != null) {
				columns = line.split(";");

				if (!columns[0].isEmpty() && columns[1].equals(planVersionID + "")) {

					long lineid = Long.parseLong(columns[0]);
					String shortName = columns[2];
					String description = columns[3];

					lines.add(new SITMLine(lineid, shortName, description, planVersionID));

				}

				line = br.readLine();
			}

			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return lines;
	}

	public ArrayList<SITMStop> findAllStopsByPlanVersion(long planVersionID) {

		ArrayList<SITMStop> stops = new ArrayList<>();
		String path = new File("DataCSV/stops.csv").getAbsolutePath();

		BufferedReader br;

		try {
			br = new BufferedReader(new FileReader(path));
			String[] columns = null;
			String line = br.readLine();
			line = br.readLine();

			while (line != null) {
				columns = line.split(";");

				if (!columns[0].isEmpty() && columns[1].equals(planVersionID + "")
						&& !columns[6].contains("#") && !columns[6].equals("0")
						&& !columns[7].contains("#") && !columns[7].equals("0")) {

					String longName = columns[3];
					String shortName = columns[2];
					long stopId = Long.parseLong(columns[0]);

					double gPSX = 0;
					double gPSY = 0;
					double decimalLongitude = 0;
					double decimalLactitude = 0;

					if (!columns[4].isEmpty()) {
						gPSX = Double.parseDouble(columns[4]) / 10000000;
					}
					if (!columns[5].isEmpty()) {
						gPSY = Double.parseDouble(columns[5]) / 10000000;
					}
					if (!columns[6].isEmpty()) {
						String origi = columns[6].replace(".", "");
						StringBuffer str = new StringBuffer(origi);
						str.insert(3, ".");
						decimalLongitude = Double.parseDouble(str.toString());
					}
					if (!columns[7].isEmpty()) {
						String origi = columns[7].replace(".", "");
						StringBuffer str = new StringBuffer(origi);
						str.insert(1, ".");
						decimalLactitude = Double.parseDouble(str.toString());
					}

					stops.add(new SITMStop(stopId, shortName, longName, gPSX, gPSY, decimalLongitude,decimalLactitude, planVersionID));
				}

				line = br.readLine();
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return stops;
	}

	public ArrayList<SITMLineStop> findAllLineStopByPlanVersion(long planVersionID) {

		String path = new File("DataCSV/linestops.csv").getAbsolutePath();
		ArrayList<SITMLineStop> lineStops = new ArrayList<>();

		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(path));
			String[] columns = null;
			String line = br.readLine();
			line = br.readLine();

			while (line != null) {
				columns = line.split(";");

				if (!columns[0].isEmpty() && columns[5].equals(planVersionID + "")) {
					
					long lineStopid = Long.parseLong(columns[0]);
					long stopsequence = Long.parseLong(columns[1]);
					long orientation = Long.parseLong(columns[2]);
					long lineid = Long.parseLong(columns[3]);
					long stopid = Long.parseLong(columns[4]);
					long planVersionid = Long.parseLong(columns[5]);
					long lineVariant = Long.parseLong(columns[6]);
					DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy", Locale.US);
					Date registerDate = new Date(dateFormat.parse(columns[7]).getTime());
					long lineVariantType = Long.parseLong(columns[8]);
					
					lineStops.add(new SITMLineStop(lineStopid, stopsequence, orientation, lineid, stopid,planVersionid, lineVariant, registerDate, lineVariantType));
				}
				
				line = br.readLine();
			}
			
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lineStops;
	}

	@Override
	public ArrayList<SITMStop> findAllStopsByLine(long planVersionID, long lineID) {

		ArrayList<SITMStop> stopsByLine = new ArrayList<>();
		ArrayList<SITMStop> stops = findAllStopsByPlanVersion(planVersionID);
		ArrayList<SITMLineStop> lineStops = findAllLineStopByPlanVersion(planVersionID);

		for (int i = 0; i < lineStops.size(); i++) {
			SITMLineStop lineStop = (SITMLineStop) lineStops.get(i);

			if (lineStop.getLineID() == lineID) {

				for (int j = 0; j < stops.size(); j++) {
					SITMStop stop = (SITMStop) stops.get(j);
					if (stop.getStopID() == lineStop.getStopID()) {
						stopsByLine.add(stop);
					}
				}
			}
		}

		return stopsByLine;
	}
	
	@Override
	public ArrayList<SITMOperationalTravels> findAllOperationalTravelsByRange(Date initialDate, Date lastDate, long lineID){
		
		ArrayList<SITMOperationalTravels> operationaTravels = new ArrayList<SITMOperationalTravels>();
		
		BufferedReader br;

		try {

			br = new BufferedReader(new FileReader(sourceFile));
			String text = br.readLine();
			
			for (int i = 0; i < currentPosition; i++) {
				text = br.readLine();
			}

			String[]data = text.split(this.split);
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
			Long date = dateFormat.parse(data[0]).getTime();
			
			if (text != null && !text.equals("")) {
				
				while (initialDate.getTime()<= date && date <=lastDate.getTime()) {
					
					Long opertravelID = System.currentTimeMillis();
					Long busID = Long.parseLong(data[1]);
					Long laststopID = Long.parseLong(data[2]);
					String gPS_X = data[4];
					String gPS_Y = data[5];
					Long odometervalue = Long.parseLong(data[3]);
					Long taskID = Long.parseLong(data[6]);
					Long tripID = Long.parseLong(data[8]);
					Date eventDate = new Date(date);
					
					if(data[7].equals(lineID+"")) {
						SITMOperationalTravels op = new SITMOperationalTravels(opertravelID, busID, laststopID, gPS_X, gPS_Y, odometervalue, lineID, taskID, tripID,eventDate);
						operationaTravels.add(op);
					}
					
					text = br.readLine();
					currentPosition++;
					
					if(text!=null && text!="") {
						data = text.split(this.split);
						date = dateFormat.parse(data[0]).getTime();
					}else {
						break;
					}
					
				}
			}

			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return operationaTravels;
	}
}
