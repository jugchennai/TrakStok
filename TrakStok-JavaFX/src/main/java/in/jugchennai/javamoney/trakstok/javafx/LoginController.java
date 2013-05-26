/*
 * Copyright 2013 JUGChennai.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package in.jugchennai.javamoney.trakstok.javafx;

import in.jugchennai.javamoney.jpa.service.entity.TsUsers;
import in.jugchennai.javamoney.trakstok.boundary.AsyncService;
import in.jugchennai.javamoney.trakstok.boundary.ServiceFactory;
import in.jugchennai.javamoney.trakstok.navigation.ControlledScreen;
import in.jugchennai.javamoney.trakstok.navigation.ScreensController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author gshenoy
 */
public class LoginController implements Initializable, ControlledScreen {

    public static final String NAME = "LoginController";
    public static final String PATH = "fxml/Login.fxml";
    @FXML
    TextField userName;
    @FXML
    PasswordField password;
    @FXML
    Label errorMessage;
    @FXML
    Button loginButton;
    @FXML
    Text appNameText;
    @FXML
    ImageView loadingImg;
    ScreensController myController;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        appNameText.setText("TrakStok");
        final DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(6.25);
        dropShadow.setOffsetX(2.75);
        dropShadow.setOffsetY(0.44);
        appNameText.setEffect(dropShadow);
        appNameText.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.BOLD, 36));
    }

    @FXML
    public void whenLogin(ActionEvent event) {

        errorMessage.setText("");

        AsyncService<TsUsers> loginService = ServiceFactory.get().loginService(userName.getText(), password.getText());

        loginService.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            public void handle(WorkerStateEvent event) {

                TsUsers loggedInUser = (TsUsers) event.getSource().getValue();

                loginButton.setDisable(false);
                loadingImg.setVisible(false);

                if (loggedInUser != null) {

                    ServiceFactory.get().setLoggedInUser(loggedInUser);
                    myController.unloadScreen(NAME);
                    myController.setScreen(MemberController.NAME, MemberController.PATH);

                } else {

                    errorMessage.setText("Invalid username/password");
                }

            }
        });
        loginService.start();
        loadingImg.setVisible(true);
        loginButton.setDisable(true);

    }

    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }
}
