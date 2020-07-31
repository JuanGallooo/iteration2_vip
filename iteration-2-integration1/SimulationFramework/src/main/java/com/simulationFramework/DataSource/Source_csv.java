package com.simulationFramework.DataSource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

import com.simulationFramework.SystemState.SITMFactory.SITMCalendar;
import com.simulationFramework.SystemState.SITMFactory.SITMLine;
import com.simulationFramework.SystemState.SITMFactory.SITMLineStop;
import com.simulationFramework.SystemState.SITMFactory.SITMPlanVersion;
import com.simulationFramework.SystemState.SITMFactory.SITMStop;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Source_csv implements IDateSource {

	private File sourceFile;
	private String split;
	private int currentPosition;

	public Source_csv(File sourceFile, String split) {
		this.sourceFile = sourceFile;
		this.split = split;
		this.currentPosition = 1;
	}

	@Override
	public String[] getHeaders() {

		String[] columns = null;
		BufferedReader br;

		try {

			br = new BufferedReader(new FileReader(sourceFile));
			String line = br.readLine();
			columns = line.split(split);
			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return columns;
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
					Date activationDate = createDate(columns[1]);
					Date creationDate = createDate(columns[2]);

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

				if (!columns[0].isEmpty()) {
					long calendarID = Long.parseLong(columns[0]);

					Date operationDate = createDate(columns[1]);

					long scheduleTypeID = Long.parseLong(columns[2]);
					if (columns[3].equals(planVersionID + "")) {
						calendars.add(new SITMCalendar(calendarID, operationDate, scheduleTypeID, planVersionID));
					}
				}

				line = br.readLine();
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

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

				if (!columns[0].isEmpty()) {

					long lineid = Long.parseLong(columns[0]);
					String shortName = columns[2];
					String description = columns[3];

					if (columns[1].equals(planVersionID + "")) {
						lines.add(new SITMLine(lineid, shortName, description, planVersionID));
					}

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

				try {

					if (!columns[0].isEmpty()) {

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
						if (!columns[6].isEmpty() && !columns[6].equals("#�CAMPO!")) {
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
						if (columns[1].equals(planVersionID + "")) {
							stops.add(new SITMStop(stopId, shortName, longName, gPSX, gPSY, decimalLongitude,decimalLactitude, stopId));
						}
					}
				} catch (Exception e) {
					line = br.readLine();
					continue;
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
				
				if (!columns[0].isEmpty()) {
					long lineStopid = Long.parseLong(columns[0]);
					long stopsequence= Long.parseLong(columns[1]);
					long orientation= Long.parseLong(columns[2]);
					long lineid= Long.parseLong(columns[3]);
					long stopid= Long.parseLong(columns[4]);
					long planVersionid= Long.parseLong(columns[5]);
					long lineVariant= Long.parseLong(columns[6]);
					Date registerDate= createDate(columns[7]);
					long lineVariantType= Long.parseLong(columns[8]);
					if (columns[5].equals(planVersionID + "")) {
						lineStops.add(new SITMLineStop(lineStopid,stopsequence,orientation,lineid,stopid,planVersionid,lineVariant,registerDate,lineVariantType));
					}
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

	@SuppressWarnings("deprecation")
	private Date createDate(String input) {

		String[] fecha = input.split("-");
		int dia = Integer.parseInt(fecha[0]);
		int mes = 0;
		int ano = 2000 + Integer.parseInt(fecha[2]);
		String str = fecha[1];

		switch (str) {
		case "feb":
			mes = 1;
			break;
		case "mar":
			mes = 2;
			break;
		case "APR":
			mes = 3;
			break;
		case "may":
			mes = 4;
			break;
		case "jun":
			mes = 5;
			break;
		case "jul":
			mes = 6;
			break;
		case "AUG":
			mes = 7;
			break;
		case "sep":
			mes = 8;
			break;
		case "oct":
			mes = 9;
			break;
		case "nov":
			mes = 10;
			break;
		case "DEC":
			mes = 11;
			break;
		default:
			mes = 0;
		}
		return new Date(ano, mes, dia);
	}
}
