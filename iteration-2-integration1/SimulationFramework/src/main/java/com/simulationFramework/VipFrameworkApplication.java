package com.simulationFramework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.simulationFramework.DataSource.Persistence.PlanVersionRepository;
import com.simulationFramework.GUI.Main_GUI;
import com.simulationFramework.SystemState.SITMFactory.SITMPlanVersion;

@SpringBootApplication
@EnableJpaRepositories
public class VipFrameworkApplication {
	
	
	private PlanVersionRepository plansVersionRepository;
	
	@Autowired
	public VipFrameworkApplication(PlanVersionRepository plansVersionRepository) {
		this.plansVersionRepository = plansVersionRepository;
	}
	
	public static void main(String[] args) {
		
		ConfigurableApplicationContext ctx = SpringApplication.run(VipFrameworkApplication.class, args);
		
		VipFrameworkApplication ep = ctx.getBean(VipFrameworkApplication.class);
		ep.test();
		ep.launchScreen(args);
	}

	public void test() {
		for (SITMPlanVersion planVersion : plansVersionRepository.findAll()) {
			System.out.println(planVersion);
		}
	}

	public void launchScreen(String[] args) {
		Main_GUI gui = new Main_GUI();
		gui.launchScreen(args);
	}
}
