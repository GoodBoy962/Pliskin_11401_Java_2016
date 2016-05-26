package com.pliskin;

import com.pliskin.configs.AppConfiguration;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main extends Application {

    private static ApplicationContext context;

    public static Stage stage;

    public static String login;

    public static void signInScene() {
        Scene signInScene = (Scene) context.getBean("signInScene");
        stage.setTitle("Medical clinic");
        stage.setScene(signInScene);
        stage.show();
    }

    public static void mainScene() {
        Scene homeScene = (Scene) context.getBean("mainScene");
        stage.setTitle("Home");
        stage.setScene(homeScene);
    }

    public static void appointmentListScene() {
        Scene appointmentSceneList = (Scene) context.getBean("appointmentList");
        stage.setTitle("Appointment list");
        stage.setScene(appointmentSceneList);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        signInScene();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
