package com.simulationFramework.DataSource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.simulationFramework.SystemState.SITMFactory.SITMCalendar;
import com.simulationFramework.SystemState.SITMFactory.SITMLine;
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
	public HashMap<String, String> fetchNextRow(Date initialDate, Date finalDate, long line,long planVerionID) throws Exception {

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<SITMCalendar> findAllCalendarsByPlanVersion(long planVersionID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<SITMLine> findAllLinesByPlanVersion(long planVersionID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<SITMStop> findAllStopsByLine(long planVersionID, long lineID) {
		// TODO Auto-generated method stub
		return null;
	}

}
