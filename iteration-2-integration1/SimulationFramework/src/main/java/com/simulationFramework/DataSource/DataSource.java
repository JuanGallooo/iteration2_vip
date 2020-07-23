package com.simulationFramework.DataSource;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

import com.simulationFramework.SystemState.SITMFactory.SITMCalendar;
import com.simulationFramework.SystemState.SITMFactory.SITMPlanVersion;
import com.simulationFramework.SystemState.SITMFactory.SITMStop;

public class DataSource implements IDateSource, Serializable{
	
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
		source_db.jdbTestConnection();
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

	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	

	public Iterable<SITMPlanVersion> findAllPlanVersions() {
		return source_db.findAllPlanVersions();
	}

	public Iterable<SITMStop> findAllStopsByLine(long planVersionID,long lineID) {
		return source_db.findAllStopsByLine(planVersionID, lineID);
	}

	public Iterable<SITMCalendar> findAllCalendarsByPlanVersion(long planVersionID) {
		return source_db.findAllCalendarsByPlanVersion(planVersionID);
	}


}
