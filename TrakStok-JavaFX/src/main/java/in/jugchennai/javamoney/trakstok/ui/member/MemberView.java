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
package in.jugchennai.javamoney.trakstok.ui.member;

import in.jugchennai.javamoney.trakstok.beans.Company;
import in.jugchennai.javamoney.trakstok.beans.User;
import in.jugchennai.javamoney.trakstok.service.TrakStokService;
import in.jugchennai.javamoney.trakstok.ui.TSWaves;
import java.util.Iterator;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBuilder;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.control.ListView;
import javafx.scene.control.ListViewBuilder;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TitledPaneBuilder;
import javafx.scene.control.ToolBar;
import javafx.scene.control.ToolBarBuilder;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.AnchorPaneBuilder;
import javafx.scene.text.Text;
import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.ui.DefaultView;
import org.jrebirth.core.ui.annotation.OnAction;
import org.jrebirth.core.wave.WaveData;

/**
 *
 * @author gshenoy
 */
public class MemberView extends DefaultView<MemberModel, AnchorPane, MemberController> {

    @OnAction(name = "Company")
    Button companyButton;
    @OnAction(name = "CurrencyExchange")
    Button currencyExchangeButton;
    @OnAction(name = "Logout")
    Button logoutButton;
    Label userNameLabel;
    Label userLabel;
    Label lastLoginTimeLabel;
    ListView listView;

    /**
     * Default Constructor.
     *
     * @param model the controls view model
     *
     * @throws CoreException if build fails
     */
    public MemberView(final MemberModel model) throws CoreException {
        super(model);
        //getModel().sendWave(TrakStokService.DO_GET_USER);
        getModel().returnData(TrakStokService.class, TrakStokService.DO_GET_USER);
        getModel().returnData(TrakStokService.class, TrakStokService.DO_FIND_COMPANIES, WaveData.build(TSWaves.USER, null));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initView() {

        AnchorPane contentPane = AnchorPaneBuilder.create().build();
        userNameLabel = LabelBuilder.create().layoutX(338.0).layoutY(63.0).prefWidth(227.0).styleClass("tsLabel").text("User name:").build();
        userLabel = LabelBuilder.create().layoutX(338.0).layoutY(90.0).prefWidth(227.0).styleClass("tsLabel").text("User").build();
        lastLoginTimeLabel = LabelBuilder.create().layoutX(338.0).layoutY(122.0).prefWidth(227.0).styleClass("tsLabel").text("Last Login").graphicTextGap(4.0).build();

        AnchorPane titlePaneContent = AnchorPaneBuilder.create().prefHeight(200.0).prefWidth(200.0).build();
        listView = ListViewBuilder.create().prefHeight(256.0).prefWidth(499.0).build();

        titlePaneContent.getChildren().add(listView);

        TitledPane titledPane = TitledPaneBuilder.create().prefHeight(163.0).prefWidth(503.0).layoutX(56.0).layoutY(186.0).style("-fx-base: #333333;").text("Companies").content(titlePaneContent).build();

        ToolBar buttons = ToolBarBuilder.create().prefHeight(52.0).prefWidth(600.0).style("-fx-base: #333333;").build();

        companyButton = ButtonBuilder.create().mnemonicParsing(false).styleClass("tsButton").text("Company").build();
        currencyExchangeButton = ButtonBuilder.create().mnemonicParsing(false).styleClass("tsButton").text("Currency Exchange").build();
        logoutButton = ButtonBuilder.create().mnemonicParsing(false).styleClass("tsButton").text("Logout").build();

        buttons.getItems().addAll(companyButton, currencyExchangeButton, logoutButton);

        contentPane.getChildren().addAll(userNameLabel, userLabel, lastLoginTimeLabel, titledPane, buttons);

        getRootNode().getChildren().add(contentPane);

    }

    public void updateUserDetails(User user) {

        userNameLabel.setText(userNameLabel.getText() + " " + user.getName());

    }

    public void updateCompanies(List<Company> companies) {

        Iterator<Company> itr = companies.iterator();
        ObservableList<String> list = FXCollections.observableArrayList();

        while (itr.hasNext()) {
            list.add(itr.next().getName());
        }
        listView.setItems(list);

    }
    
    
    
}
