package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
    	AppController appController = new AppController();
		primaryStage.setTitle("Procesare de imagine");
		primaryStage.setScene(new Scene(appController.buildScene()));
		primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
