package GUI.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONException;

import GUI.Marker.MarkerInfo;
import SystemState.FactoryInterfaces.IBus;
import SystemState.FactoryInterfaces.IStop;
import SystemState.SITMFactory.SITMBus;
import SystemState.SITMFactory.SITMStop;
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
    
    public void update(ArrayList<IBus> bus) throws JSONException {
    	markerInfor.saveBus(bus);
    }
}
