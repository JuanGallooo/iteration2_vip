package com.vip.simulation.simulation.Rest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vip.simulation.simulation.Service.RestService;


@CrossOrigin(origins = { "*" }, 
			 methods = { RequestMethod.OPTIONS, 
					 	 RequestMethod.GET, 
					 	 RequestMethod.POST, 		
					 	 RequestMethod.PUT,
					 	 RequestMethod.PATCH, 
					 	 RequestMethod.DELETE })
@RestController 
public class RestControllerImp implements Rest {
	
	@Autowired
	private RestService RestService;
	
	@GetMapping("/api/positions")
	public String getNewPositions() throws IOException{
		
		File file = RestService.getBus();
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = "{"+br.readLine();
		br.close();
		
		file = RestService.getStop();
		br = new BufferedReader(new FileReader(file));
		line += ","+br.readLine()+"}";
		br.close();
		
		return line;
	}

}
