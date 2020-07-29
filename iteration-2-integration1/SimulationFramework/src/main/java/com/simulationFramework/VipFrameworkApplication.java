package com.simulationFramework;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.simulationFramework.DataSource.Persistence.CalendarRepository;
import com.simulationFramework.DataSource.Persistence.LineRepository;
import com.simulationFramework.DataSource.Persistence.PlanVersionRepository;
import com.simulationFramework.DataSource.Persistence.StopRepository;
import com.simulationFramework.GUI.controller.GUIController;
import com.simulationFramework.Simulation.SimController;
import com.simulationFramework.SimulationProject.SPController;
import com.simulationFramework.SystemState.SITMFactory.SITMPlanVersion;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

@SpringBootApplication
@EnableJpaRepositories
@ComponentScan("com.simulationFramework")
public class VipFrameworkApplication extends Application{
	
    private ConfigurableApplicationContext springContext;
    private Parent rootNode;
    private FXMLLoader fxmlLoader;
    
	private PlanVersionRepository plansVersionRepository;
	private LineRepository lineRepository;
	
	@Autowired
	public VipFrameworkApplication(PlanVersionRepository plansVersionRepository, LineRepository lineRepository) {
		this.plansVersionRepository = plansVersionRepository;
		this.lineRepository= lineRepository;
	}
	@Override
    public void init() throws Exception {
        springContext = SpringApplication.run(VipFrameworkApplication.class);
        fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(springContext::getBean);
    }
	
	public VipFrameworkApplication() {
		
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void test() {
		for (SITMPlanVersion planVersion : plansVersionRepository.findAll()) {
			System.out.println(planVersion);
		}
	}
	@Override
	public void start(Stage primaryStage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(GUIController.VIEW_ADDRESS+"GUIView.fxml"));
		rootNode = fxmlLoader.load();
		primaryStage.setTitle("Simulator");
		Scene scene = new Scene(rootNode);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		StopRepository sp = springContext.getBean(StopRepository.class);
		System.out.println(sp.findAllStopsbyLineID(180,150));
		
		primaryStage.setOnCloseRequest(e->{
				Platform.exit();
				System.exit(0);
		});
		GUIController guiController = fxmlLoader.getController();
		SPController spcontroller = new SPController();
		SimController simController = springContext.getBean(SimController.class);
		
		
		simController.subscribe(guiController);
		guiController.setStage(primaryStage);
		guiController.setSpController(spcontroller);
		guiController.setSimController(simController);

	}
	@Override
    public void stop() {
        springContext.stop();
    }
}
