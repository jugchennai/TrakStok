/*
 * Copyright 2013 Your Organisation.
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

import in.jugchennai.javamoney.jpa.service.entity.TsCompany;
import in.jugchennai.javamoney.jpa.service.entity.TsUsers;
import in.jugchennai.javamoney.trakstok.boundary.AsyncService;
import in.jugchennai.javamoney.trakstok.boundary.ServiceFactory;
import static in.jugchennai.javamoney.trakstok.javafx.LoginController.NAME;
import in.jugchennai.javamoney.trakstok.navigation.ControlledScreen;
import in.jugchennai.javamoney.trakstok.navigation.ScreensController;
import java.net.URL;
import java.util.Collection;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;

/**
 *
 * @author gshenoy
 */
public class MemberController implements Initializable, ControlledScreen {

    public static final String NAME = "MemberController";
    public static final String PATH = "fxml/Member.fxml";
    @FXML
    Label userName;
    @FXML
    Label user;
    @FXML
    Label lastLoginTime;
    @FXML
    Button companyButton;
    Button currencyExchangeButton;
    @FXML
    Button logoutButton;
    @FXML
    ListView<String> listView;
    ObservableList<String> companies = FXCollections.observableArrayList();
    ScreensController myController;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        TsUsers loggedInUser = ServiceFactory.get().getLoggedInUser();
        userName.setText(loggedInUser.getUsername());
        user.setText(loggedInUser.getDisplayname());
        lastLoginTime.setText(loggedInUser.getLastlogin().toString());

        AsyncService<Collection<TsCompany>> companyService = ServiceFactory.get().companyService();
        companyService.setOnSucceeded(getCompanies);
        companyService.start();

        listView.setItems(companies);

    }

    @FXML
    public void onCompanyAction(ActionEvent event) {
        myController.unloadScreen(NAME);
        myController.setScreen(CompanyController.NAME, CompanyController.PATH);
    }

    @FXML
    public void onCurrencyExchangeAction(ActionEvent event) {
    }

    @FXML
    public void onLogoutAction(ActionEvent event) {
        ServiceFactory.get().setLoggedInUser(null);
        myController.unloadScreen(NAME);
        myController.setScreen(LoginController.NAME, LoginController.PATH);
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }
    private EventHandler<WorkerStateEvent> getCompanies = new EventHandler<WorkerStateEvent>() {
        @Override
        public void handle(WorkerStateEvent event) {

            Collection<TsCompany> allCompanies = (Collection<TsCompany>) event.getSource().getValue();

            Iterator<TsCompany> listItr = allCompanies.iterator();
            TsCompany company;
            while (listItr.hasNext()) {
                company = listItr.next();
                if (ServiceFactory.get().getCompany() == null) {
                    ServiceFactory.get().setCompany(company);
                }
                companies.add(company.getDisplayname() + " " + company.getSymbol());
            }

        }
    };
}
