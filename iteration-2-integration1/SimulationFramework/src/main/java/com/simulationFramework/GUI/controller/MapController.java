package com.simulationFramework.GUI.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONException;

import com.simulationFramework.GUI.Marker.MarkerInfo;
import com.simulationFramework.SystemState.FactoryInerfaces.IBus;
import com.simulationFramework.SystemState.FactoryInerfaces.IStop;
import com.simulationFramework.SystemState.SITMFactory.SITMBus;
import com.simulationFramework.SystemState.SITMFactory.SITMStop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import lombok.Data;

@Data
public class MapController {
	
	private GUIController mainController;
	
	private MarkerInfo markerInfor;
	
	private WebEngine webEngine;
	
    @FXML
    private WebView wvMap;
    
    @FXML
    private void initialize() throws IOException {
    	 webEngine = wvMap.getEngine();
    	 URL url = getClass().getResource(GUIController.VIEW_ADDRESS+"Map.html");
         webEngine.load(url.toExternalForm());
         
         markerInfor = new MarkerInfo();
    }
    
    @FXML
    void butCloseAction(ActionEvent event) {
    	mainController.getMiMap().fire();
    }
    
    public void update(ArrayList<SITMBus> bus) throws JSONException {
    	markerInfor.saveBus(bus);
    }
}
