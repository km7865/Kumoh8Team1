package GUI;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.awt.Point;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.collections.*;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Test3 extends Application {
	private Label label1, label2;
	private Button button1;
	private Scene scene;
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("JavaFX");
		try {
			Pane root = new Pane();
			
			label1 = new Label("Click Here");
			label1.setLayoutX(0);
			label1.setLayoutY(0);
			label1.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
			label1.setPrefSize(150, 50); 
			label2 = new Label("Click Here");
			label2.setLayoutX(0);
			label2.setLayoutY(200);
			label2.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
			label2.setPrefSize(150, 50);
			
			button1 = new Button("Me");
			button1.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
			button1.setPrefSize(50, 50);
			button1.setLayoutX(100);
			button1.setLayoutY(100);
			root.getChildren().addAll(label1,label2,button1);
			
			MouseEventHandler handlerObj = new MouseEventHandler();
		 	scene = new Scene(root, 300, 250);
				//마우스 이벤트 등록
				scene.setOnMouseMoved(handlerObj);
				scene.setOnMouseDragged(handlerObj);
				label2.setOnMouseClicked(handlerObj);
				label2.setOnMouseMoved(handlerObj);
				button1.setOnMouseDragged(handlerObj);
				primaryStage.setScene(scene);
				primaryStage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}

		public static void main(String[] args) {
			launch(args);
		}

		
		class MouseEventHandler implements EventHandler<MouseEvent>{
			@Override
			public void handle(MouseEvent e) {
				if(e.getSource() == scene) {
					if(e.getEventType() == MouseEvent.MOUSE_MOVED) label1.setText(e.getX() + " , " + e.getY());
					else if(e.getEventType() == MouseEvent.MOUSE_DRAGGED) label1.setText( "dragged " + e.getX() + " , " +  e.getY());
				}
				else if(e.getSource() == label2) {
					 if(e.getEventType() == MouseEvent.MOUSE_MOVED) {
			label1.setText( e.getX() + " , " + e.getY()); e.consume();}
					else if(e.getEventType() == MouseEvent.MOUSE_CLICKED) label1.setText("Clicked : " + e.getX() + ", " + e.getY() );			}
				else if(e.getSource() == button1) {
					Point bx = new Point((int)button1.getLayoutX() , (int)button1.getLayoutY());
					button1.setLayoutX((int)button1.getLayoutX()+ e.getX());
					button1.setLayoutY((int)button1.getLayoutY()+ e.getY());
					label1.setText("Button Dragged : " + e.getX() + ", " + e.getY() );
				}
			}
		}
	}
