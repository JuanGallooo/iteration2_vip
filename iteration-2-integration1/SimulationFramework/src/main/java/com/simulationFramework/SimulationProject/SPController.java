package com.simulationFramework.SimulationProject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import lombok.Data;

@Data
public class SPController {
	public static final String FILE_EXTENSION = ".dat";
	
	private String projectsPath;
	
	private Project currentProject;

	public SPController(){
		projectsPath = System.getProperty("user.dir")+File.separator+"projects";
	}
	
	public void saveNewProject() throws IOException {
		
		String pName = currentProject.getName();
		
		File file = new File(projectsPath+File.separator+pName);
		
		if (!file.exists()) {
			file.mkdirs();
        }
		
		file = new File(projectsPath+File.separator+pName+File.separator+pName+FILE_EXTENSION);
		
    	ObjectOutputStream save = new ObjectOutputStream(new FileOutputStream(file) );
    	
    	save.writeObject(currentProject);
    	save.close();
	}
	
	public File[] getProjectsFiles(){
		File path = new File(projectsPath);
		File[] projects = path.listFiles();

		return projects;
	}

}
