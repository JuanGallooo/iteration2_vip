package com.simulationFramework.GUI.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.json.JSONException;

import com.simulationFramework.GUI.Observer.Observer;
import com.simulationFramework.Simulation.SimController;
import com.simulationFramework.Simulation.SimState.Variable;
import com.simulationFramework.SimulationProject.Project;
import com.simulationFramework.SimulationProject.SPController;
import com.simulationFramework.SystemState.FactoryInerfaces.IBus;
import com.simulationFramework.SystemState.FactoryInerfaces.IPlanVersion;
import com.simulationFramework.SystemState.FactoryInerfaces.IStop;
import com.simulationFramework.SystemState.SITMFactory.SITMPlanVersion;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.Data;

@Data
public class GUIController implements Observer {

	public static final String VIEW_ADDRESS = "/com.simulationFramework/GUI/view/";

	private Stage stage;

	private SPController spController;

	private HashMap<String, Parent> views;

	private ProExplorerController proExplorerController;

	private MapController mapController;

	private VarStaController varStaController;

	private SimController simController;

	@FXML
	private SplitPane spViews;

	@FXML
	private MenuItem miNew;

	@FXML
	private MenuItem miProjectExplorer;

	@FXML
	private MenuItem miMap;

	@FXML
	private MenuItem miVariablesStatistics;

	@FXML
	private MenuItem miSave;

	@FXML
	private MenuItem miStart;

	@FXML
	private MenuItem miPause;

	@FXML
	private MenuItem miResume;

	@FXML
	private MenuItem miStop;

	@FXML
	private MenuItem miSlow;

	@FXML
	private MenuItem miNormal;

	@FXML
	private MenuItem miFast;

	@FXML
	private MenuItem mi1to1;

	@FXML
	private MenuItem mi1to5;

	@FXML
	private MenuItem mi1to10;

	@FXML
	private MenuItem mi1to30;

	@FXML
	private MenuItem mi1to60;

	@FXML
	private Button closeButton;

	@FXML
	private void initialize() {
		views = new HashMap<String, Parent>();
	}

	@FXML
	void miStartAction(ActionEvent event) {
		simController.start();
	}

	@FXML
	void miPauseAction(ActionEvent event) {
		simController.pause();
	}

	@FXML
	void miResumeAction(ActionEvent event) {
		simController.resume();
	}

	@FXML
	void miStopAction(ActionEvent event) {
		simController.stop();
	}

	@FXML
	void miSlowAction(ActionEvent event) {
		simController.setSlowSpeed();
	}

	@FXML
	void miNormalAction(ActionEvent event) {
		simController.setNormalSpeed();
	}

	@FXML
	void miFastAction(ActionEvent event) {
		simController.setFastSpeed();
	}

	@FXML
	void mi1to1Action(ActionEvent event) {
		simController.setOneToOneSpeed();
		;
	}

	@FXML
	void mi1to5Action(ActionEvent event) {
		simController.setOneToFiveSpeed();
	}

	@FXML
	void mi1to10Action(ActionEvent event) {
		simController.setOneToTenSpeed();
	}

	@FXML
	void mi1to30Action(ActionEvent event) {
		simController.setOneToThirtySpeed();
	}

	@FXML
	void mi1to60Action(ActionEvent event) {
		simController.setOneToSixtySpeed();
	}

	@FXML
	void mShowViews(ActionEvent event) throws IOException {
		Parent newParent = null;

		if (event.getSource().equals(miProjectExplorer)) {
			FXMLLoader fxmlLoader = loadFXML("ProExplorerView");

			newParent = fxmlLoader.load();
			String id = newParent.getId();
			newParent.setId("0");

			if (views.get(newParent.getId()) != null) {

				views.remove(newParent.getId());
				spViews.getItems().setAll(views.values());

			} else {

				proExplorerController = fxmlLoader.getController();
				proExplorerController.setMainController(this);
				proExplorerController.loadProjects();

				views.put(newParent.getId(), newParent);
				spViews.getItems().setAll(views.values());
			}

			newParent.setId(id);

		} else if (event.getSource().equals(miMap)) {
			FXMLLoader fxmlLoader = loadFXML("MapView");

			newParent = fxmlLoader.load();
			String id = newParent.getId();
			newParent.setId("1");

			if (views.get(newParent.getId()) != null) {
				views.remove(newParent.getId());
				spViews.getItems().setAll(views.values());
			} else {

				mapController = fxmlLoader.getController();
				mapController.setMainController(this);

				views.put(newParent.getId(), newParent);
				spViews.getItems().setAll(views.values());

			}

			newParent.setId(id);

		} else if (event.getSource().equals(miVariablesStatistics)) {
			FXMLLoader fxmlLoader = loadFXML("VarStaView");
			newParent = fxmlLoader.load();
			String id = newParent.getId();
			newParent.setId("2");

			if (views.get(newParent.getId()) != null) {
				views.remove(newParent.getId());
				spViews.getItems().setAll(views.values());
			} else {
				varStaController = fxmlLoader.getController();
				varStaController.setMainController(this);

				views.put(newParent.getId(), newParent);
				spViews.getItems().setAll(views.values());
			}

			newParent.setId(id);

		}

	}

	@FXML
	void miNewAction(ActionEvent event) throws IOException {

		FXMLLoader fxmlLoader = loadFXML("NewProView");
		Scene scene = new Scene(fxmlLoader.load());

		Stage dialogStage = new Stage();
		dialogStage.setTitle("New Project");
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initOwner(stage);
		dialogStage.setScene(scene);

		NewProController newController = fxmlLoader.getController();
		newController.setGuiController(this);
		newController.setStage(dialogStage);
		newController.start();

		dialogStage.showAndWait();

	}

	@FXML
	void miSaveAction(ActionEvent event) throws IOException {
		spController.saveNewProject();

		if (proExplorerController != null) {
			proExplorerController.loadProjects();
		}
	}

	public static FXMLLoader loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(GUIController.class.getResource(VIEW_ADDRESS + fxml + ".fxml"));
		return fxmlLoader;
	}

	public Iterable<SITMPlanVersion>  getPlanversion() {
		
		return simController.getPlanVersions();
		
	}
	
	public Date getStartDate() {
		//Change to good method
		return simController.getIniDate();
	}
	
	public Date getEdingDate() {
		//Change to good method
		return simController.getFinalDate();
	}
	

	public void finishNewProject(String[] pAttributes, ArrayList<CheckBox> selectedVariables) throws IOException {

		miMap.fire();
		miVariablesStatistics.fire();

		ArrayList<Variable> variables = new ArrayList<>();
		String[] headers = new String[selectedVariables.size()];

		for (int i = 0; i < selectedVariables.size(); i++) {
			variables.add(new Variable(selectedVariables.get(i).getText(), "-"));
			headers[i] = selectedVariables.get(i).getText();
		}

		if (varStaController != null) {
			simController.initVariables(headers);
			varStaController.loadVariables(variables);
			varStaController.loadLines(simController.getTargetSystem().getLines());
		}

		Long id = System.nanoTime();
		spController.setCurrentProject(new Project(pAttributes[0], id));
	}

	public void loadDataSource(String path, String separator) {
		File dataSource = new File(path);
		simController.initialize_SCV(dataSource, separator);
	}

	public File[] loadProjects() {
		return spController.getProjectsFiles();
	}

	public ObservableList<CheckBox> getVariables() {
		ObservableList<CheckBox> list = FXCollections.observableArrayList();
		String[] variables = simController.getDataSource().getHeaders();

		for (int i = 0; i < variables.length; i++) {
			list.add(new CheckBox(variables[i]));
		}

		return list;
	}

	public void reloadMap() {
		URL url = getClass().getResource(GUIController.VIEW_ADDRESS + "Map.html");
		mapController.getWvMap().getEngine().load(url.toExternalForm());
	}

	@Override
	public void updateVariables(ArrayList<Variable> variables) {

		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				varStaController.updateVariables(variables);
			}

		});

	}

	@Override
	public void updateStops(ArrayList<IStop> stops) {
		try {
			mapController.getMarkerInfor().saveStops(stops);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateBuses(ArrayList<IBus> bus) {
		if (mapController != null) {
			try {
				mapController.update(bus);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}

}
