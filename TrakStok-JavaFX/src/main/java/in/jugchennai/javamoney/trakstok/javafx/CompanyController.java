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

import in.jugchennai.javamoney.jpa.service.entity.TrendFrequency;
import in.jugchennai.javamoney.jpa.service.entity.TsCompany;
import in.jugchennai.javamoney.trakstok.boundary.AsyncService;
import in.jugchennai.javamoney.trakstok.boundary.ServiceFactory;
import static in.jugchennai.javamoney.trakstok.javafx.MemberController.NAME;
import in.jugchennai.javamoney.trakstok.navigation.ControlledScreen;
import in.jugchennai.javamoney.trakstok.navigation.ScreensController;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author gshenoy
 */
public class CompanyController implements Initializable, ControlledScreen {

    public static final String NAME = "CompanyController";
    public static final String PATH = "fxml/Company.fxml";
    ScreensController myController;
    @FXML
    TextField companyName;
    @FXML
    TextField currency;
    @FXML
    TextField compareWith;
    @FXML
    Button showButton;
    @FXML
    Button compareButton;
    @FXML
    CategoryAxis xAxis;
    @FXML
    NumberAxis yAxis;
    @FXML
    Label compareWithLabel;
    @FXML
    CheckBox compareWithCheckbox;
    @FXML
    LineChart<String, Number> trendChart;
    String companySeries;
    String companyToCompareWith;
    String currencyCode;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        TsCompany company = ServiceFactory.get().getCompany();
        if (company != null) {

            currencyCode = ServiceFactory.SOURCE_CURRENCY_CODE;
            companySeries = company.getSymbol();
            updateTrend();

        }

//        AsyncService<Collection<TsCompany>> companyService = ServiceFactory.get().companyService();
//        companyService.setOnSucceeded(getCompanies);
//        companyService.start();

    }

    @FXML
    public void onCompanyAction(ActionEvent event) {
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

    @FXML
    public void onCompareAction(ActionEvent event) {

        currencyCode = currency.getText();
        companySeries = companyName.getText();
        companyToCompareWith = compareWith.getText();

        compareTrend();

    }

    @FXML
    public void onShowCompanyAction(ActionEvent event) {

        currencyCode = currency.getText();
        companySeries = companyName.getText();

        updateTrend();

    }

    @FXML
    public void onCompareCheckBoxAction(ActionEvent event) {

        compareWith.setVisible(compareWithCheckbox.isSelected());
        compareButton.setVisible(compareWithCheckbox.isSelected());
        compareWithLabel.setVisible(compareWithCheckbox.isSelected());

    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    public void updateTrend() {

        trendChart.getData().clear();
        AsyncService<Map<Object, Number>> trendService = ServiceFactory.get().trendService(companySeries, TrendFrequency.DAILY, currencyCode);
        trendService.setOnSucceeded(getTrend);
        trendService.start();

    }

    public void compareTrend() {

        trendChart.getData().clear();
        AsyncService<Map<String, Map<Object, Number>>> compareTrendservice = ServiceFactory.get().compareTrendService(companySeries, companyToCompareWith, TrendFrequency.DAILY, currencyCode);
        compareTrendservice.setOnSucceeded(getCompareTrend);
        compareTrendservice.start();

    }
    private EventHandler<WorkerStateEvent> getTrend = new EventHandler<WorkerStateEvent>() {
        @Override
        public void handle(WorkerStateEvent event) {

            LinkedHashMap<Object, Number> result = (LinkedHashMap<Object, Number>) event.getSource().getValue();

            XYChart.Series series = getLineSeries(result, companySeries + " in " + currencyCode);
            trendChart.getData().addAll(series);

        }
    };
    private EventHandler<WorkerStateEvent> getCompareTrend = new EventHandler<WorkerStateEvent>() {
        @Override
        public void handle(WorkerStateEvent event) {

            Map<String, LinkedHashMap<Object, Number>> result = (Map<String, LinkedHashMap<Object, Number>>) event.getSource().getValue();

            XYChart.Series series1 = getLineSeries(result.get(companySeries), companySeries + " in " + currencyCode);
            XYChart.Series series2 = getLineSeries(result.get(companyToCompareWith), companyToCompareWith + " in " + currencyCode);

            trendChart.getData().addAll(series1, series2);

        }
    };

    private XYChart.Series getLineSeries(LinkedHashMap<Object, Number> chartData, String chartTitle) {

        XYChart.Series series = new XYChart.Series();

        if (!chartData.isEmpty()) {

            series.setName(chartTitle);


            Iterator<Object> keyItr = chartData.keySet().iterator();

            String date;
            Number amount;
            while (keyItr.hasNext()) {

                date = String.valueOf(keyItr.next());
                amount = chartData.get(date);

                series.getData().add(new XYChart.Data(date, amount));

            }

        }
        return series;
    }
//    private EventHandler<WorkerStateEvent> getCompanies = new EventHandler<WorkerStateEvent>() {
//        @Override
//        public void handle(WorkerStateEvent event) {
//
//            Collection<TsCompany> allCompanies = (Collection<TsCompany>) event.getSource().getValue();
//
//            Iterator<TsCompany> listItr = allCompanies.iterator();
//            TsCompany company;
//
//            ObservableList<String> companies = FXCollections.observableArrayList();
//
//            while (listItr.hasNext()) {
//                company = listItr.next();
//                companies.add(company.getSymbol());
//            }
//
//            companyName.setItems(companies);
//            compareWith.setItems(companies);
//        }
//    };
}
