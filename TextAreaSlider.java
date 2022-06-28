package shiv.ex;
import java.io.FileInputStream;
import java.util.Scanner;

import javafx.application.*; 
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
public class TextAreaSlider extends Application{

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		TextArea ta = new TextArea();
		ta.setPrefColumnCount(100);
		ta.setPrefRowCount(20);
		
		Slider s = new Slider(10,50,10);
		s.setMajorTickUnit(5);
		s.setShowTickMarks(true);
		
		s.valueProperty().addListener(e->ta.setFont(Font.font(s.getValue())));
		
		DatePicker dp = new DatePicker();
		//dp.setShowWeekNumbers(true);
		dp.setOnAction(e->ta.setText("Date :"+dp.getValue()+"\n"+ta.getText()));
		
		ColorPicker c = new ColorPicker();
		c.setOnAction(e->{
			ta.setStyle("-fx-text-fill:#"+c.getValue().toString().substring(2,8));
			ta.setText(c.getValue()+"\n"+ta.getText());
		});
		Button b = new Button("Open File");
		b.setOnAction(e->{
			FileChooser fc = new FileChooser();
			java.io.File file = fc.showOpenDialog(stage);
			try(Scanner sc = new Scanner(new FileInputStream(file)))
			{
				String str = " ";
				while(sc.hasNext())
					str = str+sc.nextLine()+"\n";
				ta.setText(str);
				
			}
			catch(Exception ex)
			{
				ta.setText("Select Valid File");
				
			}
			
			
		});
		
		VBox v = new VBox();
		
		v.setAlignment(Pos.TOP_CENTER);
		v.getChildren().addAll(ta,s,dp,c,b);
		
		
		Scene sc = new Scene(v,500,500);
		stage.setScene(sc);
		stage.show();
	
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
