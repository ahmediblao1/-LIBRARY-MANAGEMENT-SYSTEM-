package components;

import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

public class InputBox extends TextField{

	//component created for textbox 
	
	public InputBox(String placeholder, String color, String txtColor) {
		// TODO Auto-generated constructor stub
		this.setPromptText(placeholder);
		this.setFont(Font.font(14));
		this.setPadding(new Insets(10));
		this.setStyle("-fx-background-color:"+color+";"
				+ "-fx-background-radius:5px;-fx-border-radius:5px;-fx-inner-color:"+txtColor+";-fx-text-fill:"+txtColor+";");
	}
	
	
}
