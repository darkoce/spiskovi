package com.zepp.spiskovi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpiskoviApplication extends Application {

    private ConfigurableApplicationContext springContext;
    private Parent root;

    @Override
    public void init() throws Exception {
        springContext = SpringApplication.run(SpiskoviApplication.class);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/sample.fxml"));
        fxmlLoader.setControllerFactory(springContext::getBean);
        root = fxmlLoader.load();
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Spiskovi račun Dekel");
        primaryStage.getIcons().add(
                new Image(
                        SpiskoviApplication.class.getResourceAsStream( "/images/Books-icon.png" )));
        Scene scene = new Scene(root, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        springContext.stop();
    }



    public static void main(String[] args) {
        launch(SpiskoviApplication.class, args);
    }

}
