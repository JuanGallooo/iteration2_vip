package com.simulationFramework.GUI.Marker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WriterJson {

	private MarkerInfo info;
	
	
	public WriterJson(MarkerInfo info) {
		this.info = info;
	}
	
	
	public void write(String type, String path,List<MarkerFactory> markers) throws JSONException {
		
		JSONArray buses =  new JSONArray();
		
		for (MarkerFactory marker : markers) {
			JSONObject myObject = new JSONObject();
			myObject.put("id", marker.getId());
			myObject.put("lat", marker.getLatitude());
			myObject.put("long", marker.getLongitude());	
			myObject.put("img", marker.getIcon());
			buses.put(myObject);
		}
		
		try {
			String msg = "";
			File fileN = new File(path);
			FileWriter file = new FileWriter(fileN);
			if(fileN.exists()) {
				fileN.delete();
			}
			if(type.equals("Stop")) {
				msg = '"'+type+'"'+":"+buses.toString();	
			}else {
				msg = '"'+type+'"'+":"+buses.toString();	
			}
			
			file.write(msg);
			file.flush();
			file.close();

		} catch (IOException e) {
			System.out.println("======> Can't save JSON file");
		}
    
	}
}
