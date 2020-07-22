package com.simulationFramework.GUI.controller;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.errorprone.annotations.Var;
import com.simulationFramework.Simulation.SimState.Variable;
import com.simulationFramework.SystemState.FactoryInerfaces.ILine;
import com.simulationFramework.SystemState.SITMFactory.SITMLine;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Data;

@Data
public class VarStaController {

	private GUIController mainController;

	@FXML
	private TableView<Variable> tvVariables;

	@FXML
	private TableColumn<Variable, String> tcVariable;

	@FXML
	private TableColumn<Variable, String> tcValue;

	@FXML
	private ListView<CheckBox> lvLineIds;

	@FXML
	private Button butFilterLine;

	@FXML
	private void initialize() {

	}

	@FXML
	void butCloseAction(ActionEvent event) {
		mainController.getMiVariablesStatistics().fire();

	}

	@FXML
	void butFilterLineAction(ActionEvent event) {

		ArrayList<SITMLine> list = new ArrayList<SITMLine>();

		ObservableList<CheckBox> selectedVariables = lvLineIds.getItems();

		for (CheckBox i : selectedVariables) {
			if (i.isSelected()) {

				list.add((SITMLine) i.getUserData());
				
			}
		}
		
		mainController.getSimController().setLineId(list.get(0).getLineid());
		mainController.reloadMap();
		
	}

	public void loadLines(ArrayList<ILine> lines) {

		for (int i = 0; i < lines.size(); i++) {

			CheckBox check = new CheckBox();
			check.setUserData(lines.get(i));
			check.setText(lines.get(i).toString());
			lvLineIds.getItems().add(check);

		}

	}

	public void loadVariables(ArrayList<Variable> list) {

		tcVariable.setCellValueFactory(new PropertyValueFactory<Variable, String>("header"));
		tcValue.setCellValueFactory(new PropertyValueFactory<Variable, String>("value"));

		tvVariables.getItems().addAll(list);

	}

	public void updateVariables(ArrayList<Variable> list) {

		tvVariables.getItems().clear();
		tvVariables.getItems().addAll(list);
	}

}
