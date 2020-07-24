package com.simulationFramework.GUI;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.stereotype.Component;

import com.simulationFramework.VipFrameworkApplication;
import com.simulationFramework.GUI.controller.GUIController;
import com.simulationFramework.Simulation.SimController;
import com.simulationFramework.SimulationProject.SPController;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

@Component
@EnableMBeanExport(defaultDomain="first")
public class Main_GUI extends Application {

	static ConfigurableApplicationContext applicationContext;
	
	@Override
	public void start(Stage primaryStage) throws IOException {
//		applicationContext.getAutowireCapableBeanFactory().autowireBean(this);
		
		FXMLLoader fxmlLoader = new FXMLLoader(Main_GUI.class.getResource(GUIController.VIEW_ADDRESS+"GUIView.fxml"));
		Scene scene = new Scene(fxmlLoader.load());

		primaryStage.setTitle("Simulator");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		primaryStage.setOnCloseRequest(e->{
				Platform.exit();
				System.exit(0);
		});

		GUIController guiController = fxmlLoader.getController();
	}
	
//	 @Override
//	    public void init() {
//	        String[] args = getParameters().getRaw().toArray(new String[0]);
//
//	        this.applicationContext = new SpringApplicationBuilder()
//	                .sources(VipFrameworkApplication.class)
//	                .run(args);
//	    }
	
//	public void launchScreen(String[] args, ConfigurableApplicationContext context) {
//		applicationContext = context;
//		launch(args);
//	}
}
