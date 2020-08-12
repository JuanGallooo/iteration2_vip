package com.simulationFramework.GUI.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import com.simulationFramework.SystemState.SITMFactory.SITMCalendar;
import com.simulationFramework.SystemState.SITMFactory.SITMPlanVersion;

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

	private AnchorPane csvDataView;

	private AnchorPane variablesView;

	private AnchorPane clockView;

	private AnchorPane planversionsView;
	
	private AnchorPane dateView;

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

		if (containerView.getCenter() == csvDataView || containerView.getCenter() == planversionsView) {

			butBack.setDisable(true);
			containerView.setCenter(formView);

		} else if (containerView.getCenter() == variablesView) {
			
			butNext.setDisable(false);
			butFinish.setDisable(true);
			
			containerView.setCenter(dateView);

		} else if(containerView.getCenter() == dateView) {
			
			containerView.setCenter(planversionsView);
			
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
			
			if (chCSV.isSelected()) {
				containerView.setCenter(csvDataView);
				
			} else if(chOracle.isSelected()){
				containerView.setCenter(planversionsView);
				loadPlanVersionIds(guiController.getPlanversion());
			}
			

		} else if (containerView.getCenter() == csvDataView) {
			
			guiController.loadDataSource(txtDataResourcePath.getText(), txtSeparator.getText());
			containerView.setCenter(planversionsView);
			loadPlanVersionIds(guiController.getPlanversion());
			
			//lvVariablesList.setItems(guiController.getVariables());
			//containerView.setCenter(variablesView);

		} else if (containerView.getCenter() == planversionsView) {
			
			ObservableList<CheckBox> selectedVariables = lvPlanversionIds.getItems();
			long planVersioId = -1;
			
			for (CheckBox i : selectedVariables) {
				if (i.isSelected()) {
					planVersioId=Long.parseLong(i.getText());
				}
			}
			guiController.getSimController().setPlanVersionID(planVersioId);
			ArrayList<SITMCalendar> calendar = guiController.getSimController().getDateByPlanVersion();
			
			SITMCalendar initialDate = calendar.get(0);
			SITMCalendar lastDate = calendar.get(calendar.size()-1);
			
			dtStartDate.setValue(convertToLocalDateViaSqlDate(initialDate.getOperationDay()));
			dtEndingDate.setValue(convertToLocalDateViaSqlDate(lastDate.getOperationDay()));
			containerView.setCenter(dateView);
		
		} else if (containerView.getCenter() == dateView) {
			
			butNext.setDisable(true);
			butFinish.setDisable(false);
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
			check.setText(plan.getPlanVersionID()+"");
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
		csvDataView = fmxlLoader.load();
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

		fmxlLoader = GUIController.loadFXML("NewProView-Date");
		dateView = fmxlLoader.load();
		newProController = fmxlLoader.getController();
		dtStartDate = newProController.getDtStartDate();
		dtEndingDate = newProController.getDtEndingDate();
		
		
		fmxlLoader = GUIController.loadFXML("NewProView-Planversions");
		planversionsView = fmxlLoader.load();
		newProController = fmxlLoader.getController();
		lvPlanversionIds = newProController.getLvPlanversionIds();
		

		containerView.setCenter(formView);
	}
	

	
}
