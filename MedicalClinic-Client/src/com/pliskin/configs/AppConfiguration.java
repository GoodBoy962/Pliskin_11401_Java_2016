package com.pliskin.configs;

import com.pliskin.Main;
import com.pliskin.model.Patient;
import com.pliskin.web.service.ApiService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static com.pliskin.Main.login;

/**
 * Created by aleksandrpliskin on 22.05.16.
 */
@Configuration
@ComponentScan(basePackages = "com.pliskin.web")
public class AppConfiguration {

    @Autowired
    ApiService apiService;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Scene signInScene() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        Label emailLabel = new Label("Login");
        gridPane.add(emailLabel, 0, 1);
        TextField email = new TextField();
        gridPane.add(email, 1, 1);
        Label passLabel = new Label("Password");
        gridPane.add(passLabel, 0, 2);
        PasswordField pass = new PasswordField();
        gridPane.add(pass, 1, 2);
        final Text error = new Text();
        gridPane.add(error, 0, 6, 2, 1);
        Button signIn = new Button("Sign in");
        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().add(signIn);
        gridPane.add(hBox, 1, 4);
        signIn.setOnAction(e -> {
            ResponseEntity<Boolean> responseEntity = apiService.signIn(email.getText(), pass.getText());
            if (responseEntity.getBody()) {
                Main.login = email.getText();
                Main.mainScene();
            } else {
                error.setText("no such user/or bad password");
            }
        });

        return new Scene(gridPane, 350, 320);
    }

    private GridPane infoProfilePane() {
        GridPane editProfilePane = new GridPane();
        editProfilePane.setAlignment(Pos.CENTER);
        editProfilePane.setHgap(10);
        editProfilePane.setVgap(10);
        editProfilePane.setPadding(new Insets(15, 15, 15, 15));
        ResponseEntity<Patient> responseEntity = apiService.home();
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            Patient patient = responseEntity.getBody();
            TextArea name = new TextArea(login);
            editProfilePane.add(name, 0, 0, 2, 1);
            TextArea surname = new TextArea(patient.getFio());
            editProfilePane.add(surname, 0, 1, 2, 1);
            TextArea email = new TextArea(patient.getCredentials().getEmail());
            editProfilePane.add(email, 0, 2, 2, 1);
            Text role = new Text("Role: " + patient.getCredentials().getRole().name());
            editProfilePane.add(role, 0, 3, 2, 1);
            Text status = new Text("Id: " + patient.getId());
            editProfilePane.add(status, 0, 4, 2, 1);
            Button changeButton = new Button("change");
            editProfilePane.add(changeButton, 0, 5, 2, 1);
            changeButton.setOnAction(event -> {
                if (apiService.changeInfo(name.getText(), surname.getText(), email.getText()).getBody()) {
                    login = name.getText();
                    Text success = new Text("Successfully!");
                    editProfilePane.add(success, 0, 6, 2, 1);
                } else {
                    Text error = new Text("patient with such data already exists");
                    editProfilePane.add(error, 0, 6, 2, 1);
                }
            });
        }
        return editProfilePane;
    }

    private GridPane patientHistoriesPane() {
        GridPane editProfilePane = new GridPane();
        editProfilePane.setAlignment(Pos.CENTER);
        editProfilePane.setHgap(10);
        editProfilePane.setVgap(10);
        editProfilePane.setPadding(new Insets(15, 15, 15, 15));
        ResponseEntity<String[]> responseEntity = apiService.getPatientHistories();

        int i = 0;

        for (String s : responseEntity.getBody()) {
            TextArea textArea = new TextArea(s);
            editProfilePane.add(textArea, 1, i);
            i++;
        }
        return editProfilePane;
    }

    @Bean
    public GridPane appointmentCreationScene() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        Label cityLabel = new Label("City");
        gridPane.add(cityLabel, 0, 1);
        TextField city = new TextField();
        gridPane.add(city, 1, 1);
        Label specializationLabel = new Label("Specialization");
        gridPane.add(specializationLabel, 0, 2);
        TextField specialization = new TextField();
        gridPane.add(specialization, 1, 2);
        Button get = new Button("Get");
        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().add(get);
        gridPane.add(hBox, 1, 4);
        get.setOnAction(e -> {
            ResponseEntity<String[]> responseEntity
                    = apiService.getDates(city.getText(), specialization.getText());
            if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
                Main.stage.setScene(appointmentList(responseEntity.getBody()));
            }
        });
        return gridPane;
    }

    private Scene appointmentList(String[] list) {

        GridPane gridPane = new GridPane();

        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(15, 15, 15, 15));
        int i = 0;
        final ToggleGroup group = new ToggleGroup();
        for (String s : list) {
            RadioButton radioButton = new RadioButton(s);
            radioButton.setToggleGroup(group);
            radioButton.setUserData(s);
            gridPane.add(radioButton, 0, i);
            i++;
            if (i == 10) break;
        }

        Button createButton = new Button("create an appointment");
        createButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                apiService.createAnAppointment(group.getSelectedToggle().getUserData().toString());
                Main.mainScene();
            }
        });
        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.BOTTOM_LEFT);
        hBox.getChildren().add(createButton);
        Button back = new Button("back");
        back.setOnAction(event -> Main.mainScene());
        hBox.getChildren().addAll(back);
        gridPane.add(hBox, 1, i);
        return new Scene(gridPane);
    }

    @Bean
    public Scene mainScene() {
        BorderPane mainPane = new BorderPane();
        MenuBar menu = new MenuBar();
        menu.prefWidthProperty().bind(Main.stage.widthProperty());
        mainPane.setTop(menu);
        Menu profileMenu = new Menu("Profile");
        MenuItem infoMenuItem = new MenuItem("Info");
        MenuItem exitMenuItem = new MenuItem("Exit");
        Menu appointmentsMenu = new Menu("Appointments");
        MenuItem appointmentForm = new MenuItem("Appointments");
        MenuItem patientHistories = new MenuItem("Patient Histories");
        profileMenu.getItems().addAll(infoMenuItem, new SeparatorMenuItem(), exitMenuItem, new SeparatorMenuItem());
        appointmentsMenu.getItems().addAll(appointmentForm, patientHistories);
        infoMenuItem.setOnAction(e -> mainPane.setCenter(infoProfilePane()));
        exitMenuItem.setOnAction(e -> {
            mainPane.setCenter(new Label("Welcome"));
            Main.signInScene();
        });
        appointmentForm.setOnAction(e -> mainPane.setCenter(appointmentCreationScene()));
        patientHistories.setOnAction(event -> mainPane.setCenter(patientHistoriesPane()));
        menu.getMenus().add(profileMenu);
        menu.getMenus().addAll(appointmentsMenu);
        Label greetingLabel = new Label("Welcome! Medical Clinics are waiting!");
        mainPane.setCenter(greetingLabel);
        return new Scene(mainPane, 400, 350, Color.WHITE);
    }
}
