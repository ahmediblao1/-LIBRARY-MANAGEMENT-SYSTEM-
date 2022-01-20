package components;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class TextLabel extends Label{
	
	//component created for displaying the string labels... 
	
	public TextLabel(String text, String color, String txtColor, int font) {
	this.setFont(Font.font(font));
	this.setPadding(new Insets(5));
	this.setText(text);
	this.setStyle("-fx-background-color:"+color+";"
			+ "-fx-background-radius:5px;-fx-border-radius:5px;-fx-inner-color:"+txtColor+";"
					+ "-fx-text-fill:"+txtColor+";-fx-font-weight:bold;");

	}

}
