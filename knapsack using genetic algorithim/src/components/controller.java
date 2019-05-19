package components;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class controller {
	static driver driver;
	@FXML
    private AnchorPane run;

    @FXML
    private TextArea report;

    @FXML
    private TextField Iterations;

    @FXML
    private TextField weight;
    @FXML
    private Button button;
    
   public static void setDriver(driver driver) {
		driver=driver;
   

	}
   public void run() {
	   int iteration=Integer.valueOf(Iterations.getText());
	   int maxWeight=Integer.valueOf(weight.getText());
	   Genetic_Algorithim run =new Genetic_Algorithim(15,8,maxWeight,10,iteration);
	      run.crossOver();
      report.appendText(run.report());
	   
   }
    
    
    
}
