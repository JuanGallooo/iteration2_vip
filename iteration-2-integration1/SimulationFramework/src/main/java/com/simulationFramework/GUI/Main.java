package com.simulationFramework.GUI;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import com.simulationFramework.GUI.controller.GUIController;
import com.simulationFramework.Simulation.SimController;
import com.simulationFramework.SimulationProject.SPController;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

@SpringBootApplication
@ComponentScan("DataSource")
public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(GUIController.VIEW_ADDRESS+"GUIView.fxml"));
		Scene scene = new Scene(fxmlLoader.load());

		primaryStage.setTitle("Simulator");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		primaryStage.setOnCloseRequest(e->{
				Platform.exit();
				System.exit(0);
		});

		GUIController guiController = fxmlLoader.getController();
		SPController spcontroller = new SPController();
		SimController simController = new SimController();
		
		
		simController.subscribe(guiController);
		guiController.setStage(primaryStage);
		guiController.setSpController(spcontroller);
		
		
	}

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
		launch(args);
	}
}
