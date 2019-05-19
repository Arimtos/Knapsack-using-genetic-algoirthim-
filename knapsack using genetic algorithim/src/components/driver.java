package components;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class driver extends Application{
    private Stage primaryStage;
	public void start(Stage primaryStage) throws IOException {
		this.primaryStage=primaryStage;
		mainWindow();
	}
	public void mainWindow() throws IOException {
		FXMLLoader loader=new FXMLLoader(driver.class.getResource("interface.fxml"));
		AnchorPane pane=loader.load();
		controller controller=loader.getController();
		controller.setDriver(this);
		Scene scene=new Scene(pane);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	public static void main(String[] args) {
		launch(args);
	}

}
