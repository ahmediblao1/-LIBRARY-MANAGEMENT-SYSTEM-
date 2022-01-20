package Includes;

import components.TextLabel;
import main.LibrarySystemManage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class Header extends HBox{

	//HBox component to show the image and title on the top...
	
	public Header() {

		this.setStyle("-fx-background-color:#fff;-fx-background-radius:10px;-fx-border-color:#f1f1f1;-fx-border-radius:10px;-fx-border-width:5px;");
		this.setPadding(new Insets(5));
		this.setAlignment(Pos.BASELINE_CENTER);
		this.setSpacing(0);

		TextLabel title1 = new TextLabel(LibrarySystemManage.TITLE,"#6699ff", "#fff",15);
		title1.setTranslateY(-1);
		this.getChildren().add(title1);
		
		
		
	}
	
	
}
