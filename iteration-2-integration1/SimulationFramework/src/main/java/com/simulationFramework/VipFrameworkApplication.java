package com.simulationFramework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.simulationFramework.DataSource.Persistence.PlanVersionRepository;
import com.simulationFramework.SystemState.SITMFactory.SITMPlanVersion;

@SpringBootApplication
@ComponentScan("Persistence")
@EnableJpaRepositories
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
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
		
	}

	public void test() {
		for (SITMPlanVersion planVersion : plansVersionRepository.findAll()) {
			System.out.println(planVersion);
		}
	}


}