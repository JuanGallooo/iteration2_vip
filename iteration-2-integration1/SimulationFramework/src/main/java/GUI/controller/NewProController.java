package GUI.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.math3.geometry.euclidean.threed.Plane;

import Simulation.SimController;
import SystemState.FactoryInterfaces.IPlanVersion;
import SystemState.SITMFactory.SITMPlanVersion;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lombok.Data;

@Data
public class NewProController {

	private Stage stage;

	private GUIController guiController;

	private String nameProject;

	private BorderPane containerView;

	private AnchorPane formView;

	private AnchorPane dataView;

	private AnchorPane variablesView;

	private AnchorPane clockView;

	private AnchorPane oracleView;
	
	private AnchorPane oracleDateView;

	@FXML
	private Button butCancel;

	@FXML
	private Button butBack;

	@FXML
	private Button butNext;

	@FXML
	private Button butFinish;

	// ---------Form View---------

	@FXML
	private TextField txtName;

	@FXML
	private CheckBox chCSV;

	@FXML
	private CheckBox chOracle;
	// ---------Data View CSV---------
	@FXML
	private TextField txtDataResourcePath;

	@FXML
	private Button butSelectDataResourcePath;

	@FXML
	private TextField txtSeparator;

	// ----------Data View Oracle---------

	@FXML
	private DatePicker dtStartDate;

	@FXML
	private DatePicker dtEndingDate;
	
	
    @FXML
    private ListView<CheckBox> lvPlanversionIds;

	@FXML
	private ListView<CheckBox> lvVariablesList;

	@FXML
	void butSelectDataResourcePathAction(ActionEvent event) {

		FileChooser locationChooser = new FileChooser();
		locationChooser.setTitle("Select Location");
		File file = locationChooser.showOpenDialog(stage);

		if (file != null) {
			txtDataResourcePath.setText(file.getAbsolutePath());
		}

	}

	@FXML
	void butBackAction(ActionEvent event) throws IOException {

		if (containerView.getCenter() == dataView || containerView.getCenter() == oracleView) {

			butBack.setDisable(true);
			containerView.setCenter(formView);

		} else if (containerView.getCenter() == variablesView) {

			containerView.setCenter(formView);

		} else if (containerView.getCenter() == clockView) {

			butNext.setDisable(false);
			butFinish.setDisable(true);
			containerView.setCenter(variablesView);

		} else if(containerView.getCenter() == oracleDateView) {
			
			containerView.setCenter(oracleView);
			
		}

	}

	@FXML
	void butCancelAction(ActionEvent event) {

		stage.close();

	}

	@FXML
	void butNextAction(ActionEvent event) throws IOException {

		if (containerView.getCenter() == formView) {

			butBack.setDisable(false);

			SimController sc = null;
			
			if (chCSV.isSelected()) {
				containerView.setCenter(dataView);
				sc = new SimController();
				guiController.setSimController(sc);
				sc.subscribe(guiController);
				
			} else if(chOracle.isSelected()){
				containerView.setCenter(oracleView);
				sc = new SimController();
				sc.initializeDB();
				guiController.setSimController(sc);
				sc.subscribe(guiController);
				loadPlanVersionIds(guiController.getPlanversion());
			}
			

		} else if (containerView.getCenter() == dataView) {

			guiController.loadDataSource(txtDataResourcePath.getText(), txtSeparator.getText());
			lvVariablesList.setItems(guiController.getVariables());
			containerView.setCenter(variablesView);

		} else if (containerView.getCenter() == variablesView) {

			butNext.setDisable(true);
			butFinish.setDisable(false);
			containerView.setCenter(clockView);

		} else if (containerView.getCenter() == oracleView) {
			//Query Start and Ending Date
			dtStartDate.setValue(convertToLocalDateViaSqlDate(guiController.getStartDate()));
			dtEndingDate.setValue(convertToLocalDateViaSqlDate(guiController.getEdingDate()));
			containerView.setCenter(oracleDateView);
		
		} else if (containerView.getCenter() == oracleDateView) {
			 
			lvVariablesList.setItems(guiController.getVariables());
			containerView.setCenter(variablesView);
			
		}
		

	}
	
	
	public LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
	    return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
	}

	@FXML
	void butFinishAction(ActionEvent event) throws IOException {
		String[] pAttributes = { txtName.getText() };

		ArrayList<CheckBox> list = new ArrayList<CheckBox>();
		ObservableList<CheckBox> selectedVariables = lvVariablesList.getItems();
		for (CheckBox i : selectedVariables) {
			if (i.isSelected()) {
				list.add(i);
			}
		}

		guiController.finishNewProject(pAttributes, list);
		stage.close();
	}
	
	public void loadPlanVersionIds(Iterable<SITMPlanVersion>  planversionIds) {
		
		for (SITMPlanVersion plan :  planversionIds) {
			
			CheckBox check = new CheckBox();
			check.setUserData(plan);
			check.setText(plan.getPlanVersionid()+"");
			lvPlanversionIds.getItems().add(check);

		}
	}

	public void start() throws IOException {
		containerView = (BorderPane) stage.getScene().getRoot();
		butBack.setDisable(true);
		butFinish.setDisable(true);

		FXMLLoader fmxlLoader = new FXMLLoader();

		NewProController newProController = null;

		fmxlLoader = GUIController.loadFXML("NewProView-Form");
		formView = fmxlLoader.load();
		newProController = fmxlLoader.getController();
		txtName = newProController.getTxtName();
		chCSV = newProController.getChCSV();
		chOracle = newProController.getChOracle();
		
		fmxlLoader = GUIController.loadFXML("NewProView-Data");
		dataView = fmxlLoader.load();
		newProController = fmxlLoader.getController();
		txtDataResourcePath = newProController.getTxtDataResourcePath();
		txtSeparator = newProController.getTxtSeparator();

		fmxlLoader = GUIController.loadFXML("NewProView-Variables");
		variablesView = fmxlLoader.load();
		newProController = fmxlLoader.getController();
		lvVariablesList = newProController.getLvVariablesList();

		fmxlLoader = GUIController.loadFXML("NewProView-Clock");
		clockView = fmxlLoader.load();
		newProController = fmxlLoader.getController();

		fmxlLoader = GUIController.loadFXML("NewProView-Oracle-Date");
		oracleDateView = fmxlLoader.load();
		newProController = fmxlLoader.getController();
		dtStartDate = newProController.getDtStartDate();
		dtEndingDate = newProController.getDtEndingDate();
		
		
		fmxlLoader = GUIController.loadFXML("NewProView-Oracle");
		oracleView = fmxlLoader.load();
		newProController = fmxlLoader.getController();
		lvPlanversionIds = newProController.getLvPlanversionIds();
		

		containerView.setCenter(formView);
	}
	

	
}
