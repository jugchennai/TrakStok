/*
 * Copyright 2013 JRebirth.
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
package in.jugchennai.javamoney.trakstok.ui.login;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonBuilder;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.control.PasswordField;
import javafx.scene.control.PasswordFieldBuilder;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFieldBuilder;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.PaneBuilder;
import javafx.scene.paint.Paint;
import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.ui.DefaultView;
import org.jrebirth.core.ui.annotation.OnAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author gshenoy
 */
public class LoginView extends DefaultView<LoginModel, AnchorPane, LoginController> {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginView.class);
    @OnAction(name = "Login")
    private Button loginButton;

    /**
     * Default Constructor.
     *
     * @param model the controls view model
     *
     * @throws CoreException if build fails
     */
    public LoginView(final LoginModel model) throws CoreException {
        super(model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initView() {

        LOGGER.info("LoginView.initView()");

        addLoginPanel();
        getRootNode().setFocusTraversable(true);

    }

    private void addLoginPanel() {

        Label loginLabel = LabelBuilder.create().text("Login:").textFill(Paint.valueOf("white")).layoutX(24.0).layoutY(49.0).style("tsLabel").build();
        Label passwordLabel = LabelBuilder.create().text("Password:").textFill(Paint.valueOf("white")).layoutX(24.0).layoutY(89.0).style("tsLabel").build();

        TextField loginText = TextFieldBuilder.create().layoutX(114.0).layoutY(48.0).style("tsTextInput").prefWidth(200.0).build();
        PasswordField pwdText = PasswordFieldBuilder.create().layoutX(114.0).layoutY(89.0).style("tsTextInput").prefWidth(200.0).build();

        this.loginButton = ButtonBuilder.create().layoutX(136.0).layoutY(129.0).styleClass("tsButton").text("Login").build();


        Pane vBox = PaneBuilder.create().layoutX(154.0).layoutY(199.0).prefHeight(200.0).prefWidth(336.0).build();


        vBox.getChildren().add(loginLabel);
        vBox.getChildren().add(loginText);
        vBox.getChildren().add(passwordLabel);
        vBox.getChildren().add(pwdText);
        vBox.getChildren().add(loginButton);

        //System.out.println("count= " + getRootNode().getChildren().size());
        //getRootNode().setTop(vBox);
        getRootNode().getChildren().add(vBox);

    }
}
