package GUI.controller;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import lombok.Data;

@Data
public class ProExplorerController {
	
	private GUIController mainController;
	
    @FXML
    private TreeView<String> tvProjects;
    
    @FXML
    private void initialize() {
  
    }
	
	@FXML
    void butCloseAction(ActionEvent event) {
    	mainController.getMiProjectExplorer().fire();
    }
	
	public void loadProjects() {
		File[] listOfFileProjects = mainController.loadProjects();
		
		TreeItem<String> root = new TreeItem<String>();
		
		for (int i = 0; i < listOfFileProjects.length; i++) {
			File currentFile = listOfFileProjects[i];
			if(currentFile.isFile()) {
				root.getChildren().add(new TreeItem<String>(currentFile.getName()));
			}else {
				root.getChildren().add(loadProjects(new TreeItem<String>(listOfFileProjects[i].getName()),currentFile.listFiles()));
			}
		}
		
		tvProjects.setRoot(root);
		tvProjects.setShowRoot(false);
		
		
	}
	
	private TreeItem<String> loadProjects(TreeItem<String> tree, File[] listOfFile ){
		
		for (int i = 0; i < listOfFile.length; i++) {
			
			if(listOfFile[i].isFile()) {
				tree.getChildren().add(new TreeItem<String>(listOfFile[i].getName()));
				
			}else {
				tree.getChildren().add(loadProjects(new TreeItem<String>(listOfFile[i].getName()),listOfFile[i].listFiles()));
			}	
		}
		tree.setExpanded(false);

		return tree;
	}
}
