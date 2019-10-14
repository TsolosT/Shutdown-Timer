package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class Controller implements Initializable {
	
	
	@FXML
	private Button btnCancel,btnStart,btnClear;
	@FXML 
	private TextField tbMin,tbHour,tbSec;
	@FXML
	private ChoiceBox choiceboxType;
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub	
		
		
		btnCancel.setVisible(false);
		//init choicebox
		choiceboxType.setItems(FXCollections.observableArrayList("Shutdown","Reboot"));
		choiceboxType.setValue("Shutdown");
	}
	
	
	//Get time&type and start timer
	public void StartTimer(ActionEvent event) {
		
	if(preventStartWithoutTime()) {
			//do nothing
			  Alert alert = new Alert(AlertType.INFORMATION);
			  alert.setTitle("Error");
			  alert.setHeaderText(null);
			  alert.setContentText("Set time first...");
			  alert.showAndWait();
		}
		else {
			setEmptyInputs();
			String time=convertTimeToSeconds(tbHour.getText(),tbMin.getText(),tbSec.getText()); 
			try {
					if(choiceboxType.getValue()=="Shutdown")
					{
						Runtime.getRuntime().exec("shutdown /s /t "+time);
					}
					else if (choiceboxType.getValue()=="Reboot"){
						Runtime.getRuntime().exec("shutdown /r /t "+time);
					}
					setDeactiveInputs();
					btnCancel.setVisible(true);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
	}
	
	//Clear inputs
	public void ClearInputs(ActionEvent event) {
		tbMin.setText("");
		tbHour.setText("");
		tbSec.setText("");
	}
	//Cancel timer
	public void CancelTimer(ActionEvent event) {
		try {
			Runtime.getRuntime().exec("shutdown /a");
			setActiveInputs();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//deactive set inputs
	public void setDeactiveInputs()
	{
		btnStart.setVisible(false);
		btnClear.setVisible(false);
		choiceboxType.setDisable(true);
		tbMin.setDisable(true);
		tbSec.setDisable(true);
		tbHour.setDisable(true);
	}
	//active set inputs
	public void setActiveInputs()
	{
		btnStart.setVisible(true);
		btnClear.setVisible(true);
		choiceboxType.setDisable(false);
		tbMin.setDisable(false);
		tbSec.setDisable(false);
		tbHour.setDisable(false);
	}
	//convert textboxs strings to seconds
	public String convertTimeToSeconds(String hour,String min,String sec) {
		String timeInSec="";

		int total=Integer.parseInt(sec)+(Integer.parseInt(min)*60)+(Integer.parseInt(hour)*3600);
		timeInSec=Integer.toString(total);
		
		return timeInSec;
	}
	//Sets empty inputs to 0 sec
	 public void setEmptyInputs() {
		 if(tbHour.getText().isEmpty())
		 {
			 tbHour.setText("0");
		 }
		 if(tbMin.getText().isEmpty())
		 {
			 tbMin.setText("0");
		 }
		 if(tbSec.getText().isEmpty())
		 {
			 tbSec.setText("0");
		 }
	 }
	//Prevent user to start timer without time
	public Boolean preventStartWithoutTime() {
	  if(tbHour.getText().isEmpty() && tbMin.getText().isEmpty() && tbSec.getText().isEmpty()) 
	  {
		  return true;
	  }
	  else {
		  return false;
	  }
	}
}
