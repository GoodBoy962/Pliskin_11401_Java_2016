package com.pliskin.configs;

import com.pliskin.Main;
import com.pliskin.model.Doctor;
import com.pliskin.model.DoctorSchedule;
import com.pliskin.model.Patient;
import com.pliskin.web.service.ApiService;
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

import java.util.Date;
import java.util.List;
import java.util.Map;

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
                error.setText("error");
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
            Text name = new Text("Login: " + login);
            editProfilePane.add(name, 0, 0, 2, 1);

            Text surname = new Text("Fio: " + patient.getFio());
            editProfilePane.add(surname, 0, 1, 2, 1);

            Text email = new Text("Email: " + patient.getCredentials().getEmail());
            editProfilePane.add(email, 0, 2, 2, 1);

            Text role = new Text("Role: " + patient.getCredentials().getRole().name());
            editProfilePane.add(role, 0, 3, 2, 1);

            Text status = new Text("Id: " + patient.getId());
            editProfilePane.add(status, 0, 4, 2, 1);
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
            ResponseEntity<Map<Doctor, Map<Date, List<DoctorSchedule>>>> responseEntity
                    = apiService.getDates(city.getText(), specialization.getText());
            if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            }
        });
        return gridPane;

    }


    @Bean
    public Scene mainScene() {
        BorderPane mainPane = new BorderPane();

        MenuBar menu = new MenuBar();
        menu.prefWidthProperty().bind(Main.stage.widthProperty());
        mainPane.setTop(menu);

        // Profile
        Menu profileMenu = new Menu("Profile");
        MenuItem infoMenuItem = new MenuItem("Info");
        MenuItem exitMenuItem = new MenuItem("Exit");
        MenuItem appotntmentForm = new MenuItem("Appointments");
        profileMenu.getItems().addAll(infoMenuItem, new SeparatorMenuItem(), exitMenuItem, new SeparatorMenuItem(), appotntmentForm);
        infoMenuItem.setOnAction(e -> mainPane.setCenter(infoProfilePane()));
        exitMenuItem.setOnAction(e -> Main.signInScene());
        appotntmentForm.setOnAction(e -> mainPane.setCenter(appointmentCreationScene()));

        // Home
        Menu homeMenu = new Menu("Home");
//        homeMenu.setOnAction(e -> mainPane.setCenter());

        menu.getMenus().add(profileMenu);

        return new Scene(mainPane, 400, 350, Color.WHITE);
    }
}
