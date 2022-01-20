package Includes;

import components.TextLabel;
import main.LibrarySystemManage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class Welcome extends VBox{

	//VBox component to display in the dashboard
	
	public Welcome() throws ClassNotFoundException{
		// TODO Auto-generated constructor stub
		
		this.setStyle("-fx-background-color:#fff;-fx-background-radius:1px;-fx-border-color:#000;-fx-border-radius:1px;-fx-border-width:1px;");
		this.setPadding(new Insets(20));
		this.setAlignment(Pos.BASELINE_CENTER);
		this.setSpacing(10);
		
		ImageView img = new ImageView(new Image("images/planeb.png"));
		img.setFitWidth(50);
		img.setFitHeight(50);
		this.getChildren().add(img);
		
		
		TextLabel title1 = new TextLabel("\r <( WEL COME TO <b>"+ LibrarySystemManage.TITLE+"</b> )> \r\n","#f1f1f1", "#000",25);
		title1.setTranslateY(-80);
		title1.setAlignment(Pos.CENTER);
		title1.setMaxWidth(1500);
		this.getChildren().add(title1);
		
	}
	
	
}
