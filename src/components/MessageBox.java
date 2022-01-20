package components;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;

public class MessageBox extends HBox{
	
	//component created for displaying messages 
	
	TextLabel msg;
	
	public MessageBox(String msg1) {
		
		this.setPrefWidth(100);
		this.maxHeight(400);
		//this.setStyle("-fx-background-color:#fff;-fx-border-color: grey;-fx-border-width:1px;-fx-background-radius:5px;-fx-border-radius:5px;");
		this.setPadding(new Insets(10));
		this.setSpacing(10);
		
		msg = new TextLabel(""+msg1,"#fff", "#000",15);
		msg.setPadding(new Insets(20));
		msg.setPrefWidth(600);
		msg.prefHeight(600);
		msg.setMaxHeight(400);
		this.getChildren().add(msg);
		
		
		
	}

	public TextLabel getMsg() {
		return msg;
	}

	public void setMsg(TextLabel msg) {
		this.msg = msg;
	}
	
	
	

}
