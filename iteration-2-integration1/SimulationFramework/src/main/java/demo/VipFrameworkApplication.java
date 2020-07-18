package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import DataSource.DataSource;
import DataSource.Persistence.ISITMPlanVersionDao;

@SpringBootApplication
@ComponentScan("DataSource")
public class VipFrameworkApplication {
	

	@Autowired
    public static DataSource dataSource;
	
	@Autowired
	private ISITMPlanVersionDao dao;
	
	public static void main(String[] args) {
		SpringApplication.run(VipFrameworkApplication.class, args);
		VipFrameworkApplication vfa = new VipFrameworkApplication();
		System.out.println(vfa.dao.findAll());
		System.out.println(dataSource);
		
		
	}
	
	@Bean
	public CommandLineRunner demo(ISITMPlanVersionDao dao) {
		return (args) -> {
			
	        System.out.println(dao.findAll().get(0));
			
		};
	}


}
