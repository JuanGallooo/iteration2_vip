package com.simulationFramework.SystemState.SITMFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import com.simulationFramework.SystemState.FactoryInerfaces.AbstractModelFactory;
import com.simulationFramework.SystemState.FactoryInerfaces.IArc;
import com.simulationFramework.SystemState.FactoryInerfaces.IBus;
import com.simulationFramework.SystemState.FactoryInerfaces.ICalendar;
import com.simulationFramework.SystemState.FactoryInerfaces.ILine;
import com.simulationFramework.SystemState.FactoryInerfaces.ILineStop;
import com.simulationFramework.SystemState.FactoryInerfaces.IPlanVersion;
import com.simulationFramework.SystemState.FactoryInerfaces.IScheduleTypes;
import com.simulationFramework.SystemState.FactoryInerfaces.IStop;
import com.simulationFramework.SystemState.FactoryInerfaces.ITask;
import com.simulationFramework.SystemState.FactoryInerfaces.ITrip;

import java.sql.Date;

public class ConcreteSITMFactory implements AbstractModelFactory {

	public static final int planVersionID = 180;

	@Override
	public IPlanVersion createPlanVersion() {
		String path = new File("DataCSV/planversions.csv").getAbsolutePath();
		SITMPlanVersion plan = null;
		BufferedReader br;
		
		try {
			br = new BufferedReader(new FileReader(path));
			String[] columns = null;
			String line = br.readLine();
			line = br.readLine();
			
			while (line != null) {
				columns = line.split(";");

				// PLANVERSIONID	ACTIVATION	CREATION

				if (!columns[0].isEmpty()) {
					if(columns[0].equals(planVersionID+"")) {
						plan = new SITMPlanVersion(planVersionID,createDate(columns[1]), createDate(columns[1]));
						break;
					}
				}

				line = br.readLine();
			}
			
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return plan;
	}

	@Override
	public ArrayList<IStop> createStops() {
		String path = new File("DataCSV/stops.csv").getAbsolutePath();

		ArrayList<IStop> stops = new ArrayList<>();

		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(path));
			String[] columns = null;
			String line = br.readLine();
			line = br.readLine();

			while (line != null) {
				columns = line.split(";");

				// STOPID;PLANVERSIONID;SHORTNAME;LONGNAME;GPS_X;GPS_Y;DECIMALLONGITUDE;DECIMALLATITUDE
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
						gPSX = Double.parseDouble(columns[4])/10000000;
					}
					if (!columns[5].isEmpty()) {
						gPSY = Double.parseDouble(columns[5])/10000000;
					}
					if (!columns[6].isEmpty() && !columns[6].equals("#�CAMPO!")) {
						String origi=columns[6].replace(".", "");
						StringBuffer str=new StringBuffer(origi);
						str.insert(3, ".");
						decimalLongitude = Double.parseDouble(str.toString());
					}
					if (!columns[7].isEmpty()) {
						String origi=columns[7].replace(".", "");
						StringBuffer str=new StringBuffer(origi);
						str.insert(1, ".");
						decimalLactitude = Double.parseDouble(str.toString());
					}
					if (columns[1].equals(planVersionID + "")) {
						stops.add(new SITMStop(longName, gPSX, gPSY, decimalLongitude, decimalLactitude, planVersionID,
								stopId, shortName));
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

	@Override
	public ArrayList<ILine> createLines() {
		String path = new File("DataCSV/lines.csv").getAbsolutePath();
		ArrayList<ILine> lines = new ArrayList<>();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(path));
			String[] columns = null;
			String line = br.readLine();
			line = br.readLine();

			while (line != null) {
				columns = line.split(";");

				// LINEID;PLANVERSIONID;SHORTNAME;DESCRIPTION
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

	@Override
	public ArrayList<IBus> createBuses() {
		String path = new File("DataCSV/buses.csv").getAbsolutePath();
		ArrayList<IBus> buses = new ArrayList<>();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(path));
			String[] columns = null;
			String line = br.readLine();
			line = br.readLine();

			while (line != null) {
				columns = line.split(";");

				// busId,BusNumber, identification, busTypeid, planVersion

				if (!columns[0].isEmpty()) {

					long busId = Long.parseLong(columns[0]);
					long busNumber = 0;

					if (!columns[0].isEmpty())
						busNumber = Long.parseLong(columns[1]);
					String identification = columns[2];
					if (columns[4].equals(planVersionID + "")) {
						buses.add(new SITMBus(busId, busNumber, identification, planVersionID));
					}
				}

				line = br.readLine();
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return buses;
	}

	@Override
	public ArrayList<IArc> createArcs() {
		String path = new File("DataCSV/arcs.csv").getAbsolutePath();
		ArrayList<IArc> arcs = new ArrayList<>();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(path));
			String[] columns = null;
			String line = br.readLine();
			line = br.readLine();

			while (line != null) {
				columns = line.split(";");

				// ARCID;PLANVERSIONID;STOPS_STOPID_START;STOPS_STOPID_END;STARTPOINT;ENDPOINT;DESCRIPTION;ARCLENGTH
				if (!columns[0].isEmpty()) {

					String description = columns[6];
					long arcID = Long.parseLong(columns[0]);
					long starPoint = 0;
					long endPoint = 0;
					String arcLength = "";

					if (!columns[2].isEmpty()) {
						starPoint = Long.parseLong(columns[2]);
					}
					if (!columns[3].isEmpty()) {
						endPoint = Long.parseLong(columns[3]);
					}
					if (columns.length == 8) {
						arcLength = columns[7];
					}
					if (columns[1].equals(planVersionID + "")) {
						arcs.add(new SITMArc(arcID, starPoint, endPoint, description, arcLength, planVersionID));
					}
				}

				line = br.readLine();
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return arcs;
	}

	@Override
	public ArrayList<ITask> createTasks() {
		String path = new File("DataCSV/tasks.csv").getAbsolutePath();
		ArrayList<ITask> tasks = new ArrayList<>();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(path));
			String[] columns = null;
			String line = br.readLine();
			line = br.readLine();

			while (line != null) {
				columns = line.split(";");

				// TASKID;SCHEDULETYPEID;LINES_LINEID;PLANVERSIONID
				
				if (!columns[0].isEmpty()) {
					
					long taskID = Long.parseLong(columns[0]);
					long scheduleTypeID = 0;
					long lineID = 0;
					
					if (!columns[1].isEmpty()) {
						scheduleTypeID = Long.parseLong(columns[1]);
					}
					if (!columns[2].isEmpty()) {
						lineID = Long.parseLong(columns[2]);
					}
					if (columns[3].equals(planVersionID + "")) {
						tasks.add(new SITMTask(taskID, scheduleTypeID, lineID, planVersionID));
					}
				}

				line = br.readLine();
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return tasks;
	}

	@Override
	public ArrayList<ITrip> createTrips() {
		return null;
	}

	@Override
	public ArrayList<IScheduleTypes> createScheduleTypes() {
		String path = new File("DataCSV/scheduletypes.csv").getAbsolutePath();
		ArrayList<IScheduleTypes> stypes = new ArrayList<>();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(path));
			String[] columns = null;
			String line = br.readLine();
			line = br.readLine();

			while (line != null) {
				columns = line.split(";");

				// SCHEDULETYPEID;PLANVERSIONID;SHORTNAME;DESCRIPTION
				
				if (!columns[0].isEmpty()) {
					long scheduleTypesid = Long.parseLong(columns[0]);
					String shortName = columns[2];
					String description = columns[3];
					if (columns[1].equals(planVersionID + "")) {
						stypes.add(new SITMScheduleTypes(scheduleTypesid, shortName, description, planVersionID));
					}
				}
				
				line = br.readLine();
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return stypes;
	}

	@Override
	public ArrayList<ICalendar> createCalendars() {
		String path = new File("DataCSV/calendar.csv").getAbsolutePath();
		ArrayList<ICalendar> calendars = new ArrayList<>();

		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(path));
			String[] columns = null;
			String line = br.readLine();
			line = br.readLine();

			while (line != null) {
				columns = line.split(";");

				// CALENDARID;OPERATION;SCHEDULETYPEID;PLANVERSIONID
				
				if (!columns[0].isEmpty()) {
					long calendarID = Long.parseLong(columns[0]);
					
					Date operationDate = createDate(columns[1]);
					
					String scheduleTypeID = columns[2];
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
	public ArrayList<ILineStop> createLineStops() {
		String path = new File("DataCSV/linestops.csv").getAbsolutePath();
		ArrayList<ILineStop> lineStops = new ArrayList<>();

		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(path));
			String[] columns = null;
			String line = br.readLine();
			line = br.readLine();

			while (line != null) {
				columns = line.split(";");
				// LINESTOPID;STOPSEQUENCE;ORIENTATION;LINEID;STOPID;PLANVERSIONID;LINEVARIANT;REGISTERD;LINEVARIANTTYPE
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