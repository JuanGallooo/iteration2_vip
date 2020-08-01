package com.simulationFramework.DataSource;

import java.io.File;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.simulationFramework.SystemState.SITMFactory.SITMCalendar;
import com.simulationFramework.SystemState.SITMFactory.SITMLine;
import com.simulationFramework.SystemState.SITMFactory.SITMPlanVersion;
import com.simulationFramework.SystemState.SITMFactory.SITMStop;

import lombok.Getter;
import lombok.Setter;


@Component
@Getter @Setter
public class DataSource2 implements IDateSource, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public final static String FILE_CSV = "FileCSV";
	public final static String DATA_BASE = "DataBase";

	private String type;
	
	@Autowired
	public Source_db source_db;
	public Source_csv source_csv;
	
	public DataSource2(File sourceFile, String split) {
		this.type = FILE_CSV;
		source_csv = new Source_csv(sourceFile, split);
	}

	public DataSource2() {
		this.type = DATA_BASE;
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
	public ArrayList<SITMPlanVersion> findAllPlanVersions() {

		switch (type) {
		case FILE_CSV:
			return source_csv.findAllPlanVersions();

		case DATA_BASE:
			return source_db.findAllPlanVersions();
		}
		
		return null;
	}

	@Override
	public ArrayList<SITMCalendar> findAllCalendarsByPlanVersion(long planVersionID) {

		switch (type) {
		case FILE_CSV:
			return source_csv.findAllCalendarsByPlanVersion(planVersionID);

		case DATA_BASE:
			return source_db.findAllCalendarsByPlanVersion(planVersionID);
		}
		
		return null;
	}

	@Override
	public ArrayList<SITMLine> findAllLinesByPlanVersion(long planVersionID) {

		switch (type) {
		case FILE_CSV:
			return source_csv.findAllLinesByPlanVersion(planVersionID);

		case DATA_BASE:
			return source_db.findAllLinesByPlanVersion(planVersionID);
		}
		
		return null;
	}

	@Override
	public ArrayList<SITMStop> findAllStopsByLine(long planVersionID, long lineID) {

		switch (type) {
		case FILE_CSV:
			System.out.println("=======> quantity of Stops ");
			return source_csv.findAllStopsByLine(planVersionID,lineID);

		case DATA_BASE:
			return source_db.findAllStopsByLine(planVersionID,lineID);
		}
		
		return null;
	}

	
}
