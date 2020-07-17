package DataSource;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.math3.geometry.euclidean.threed.Plane;

import SystemState.FactoryInterfaces.ICalendar;
import SystemState.FactoryInterfaces.IPlanVersion;
import SystemState.FactoryInterfaces.IStop;
import SystemState.SITMFactory.SITMPlanVersion;

import java.sql.*;

public class DataSource implements IDateSource, Serializable{

	private static final String dbURL = "jdbc:oracle:thin:@192.168.161.43:1521:liason";
	private static final String USER_NAME ="metrocali";
	private static final String USER_PASSWORD ="metrocali";
	
	private static final long serialVersionUID = 1L;
	

	public final static String FILE_CSV = "FileCSV";
	public final static String DATA_BASE = "DataBase";

	private String type;
	private Source_db source_db;
	private Source_csv source_csv;
	
	public DataSource(File sourceFile, String split) {
		this.type = FILE_CSV;
		source_csv = new Source_csv(sourceFile, split);
	}

	public DataSource() {
		this.type = DATA_BASE;
		source_db = new Source_db();
	}

	
	
	@Override 
	public String[] getHeaders() {

		switch (type) {
		case FILE_CSV:
			return source_csv.getHeaders();

		case DATA_BASE:
			return source_db.getHeaders();

		}
		return null;
	}

	@Override
	public HashMap<String, String> fetchNextRow(Date initialDate, Date finalDate, long line,long planVerionID)throws Exception {

		switch (type) {
		case FILE_CSV:
			return source_csv.fetchNextRow(null,null,0,0);

		case DATA_BASE:
			return source_db.fetchNextRow(initialDate,finalDate,line,planVerionID);

		}
		return null;
	}

	@Override
	public boolean jdbTestConnection() {
		try {
			Connection connection= DriverManager.getConnection(dbURL,USER_NAME,USER_PASSWORD);
			System.out.println("Connection Succesfull !!");
			connection.close();
			return true;
		} catch (Exception e) {
			System.out.println("Error connection DB!");
			e.printStackTrace();
			return false;
		}
	}
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	public ArrayList<IStop> getStopsBylineDB(long lineId, long planVersionId){
		ArrayList<IStop> paradas= new ArrayList<>();
		paradas= source_db.getStopsByLine(lineId,planVersionId);
		return paradas;
	}

	public ArrayList<IPlanVersion> getPlanVersions() {
		ArrayList<IPlanVersion> planVersions= new ArrayList<>();
		planVersions= source_db.getPlanVerions();
		return planVersions;
	}

	public ArrayList<ICalendar> getDateByPlanVersion(long planVersionID) {
		ArrayList<ICalendar> planVersions= new ArrayList<>();
		planVersions= source_db.getDatesByPlanVersion(planVersionID);
		return planVersions;
	}


}
