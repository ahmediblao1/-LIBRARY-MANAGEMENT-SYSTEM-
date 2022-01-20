package components;

import javafx.geometry.Insets;
import javafx.scene.control.PasswordField;
import javafx.scene.text.Font;

public class PasswordBox extends PasswordField{

	
	//component created for password field 
	
	public PasswordBox(String placeholder, String color , String txtColor) {
		// TODO Auto-generated constructor stub
		this.setPromptText(placeholder);
		this.setPadding(new Insets(10));
		this.setFont(Font.font(14));
		this.setStyle("-fx-background-color:"+color+";"
				+ "-fx-background-radius:5px;-fx-border-radius:5px;-fx-inner-color:"+txtColor+";-fx-text-fill:"+txtColor+";");
	}
	
	
	
}
