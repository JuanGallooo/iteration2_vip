package com.simulationFramework.GUI;

import java.io.IOException;

import com.simulationFramework.GUI.controller.GUIController;
import com.simulationFramework.Simulation.SimController;
import com.simulationFramework.SimulationProject.SPController;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(GUIController.VIEW_ADDRESS + "GUIView.fxml"));
		Scene scene = new Scene(fxmlLoader.load());

		primaryStage.setTitle("Simulator");
		primaryStage.setScene(scene);
		primaryStage.show();

		GUIController guiController = fxmlLoader.getController();
		SPController spcontroller = new SPController();
		SimController simController = new SimController(null);

		simController.subscribe(guiController);
		guiController.setStage(primaryStage);
		guiController.setSpController(spcontroller);
		guiController.setSimController(simController);

		primaryStage.setOnCloseRequest(e -> {
			Platform.exit();
			System.exit(0);
		});

	}

	public static void main(String[] args) {
		launch(args);
	}
}