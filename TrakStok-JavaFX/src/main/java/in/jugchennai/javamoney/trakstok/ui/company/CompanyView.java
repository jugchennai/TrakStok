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
package in.jugchennai.javamoney.trakstok.ui.company;

import javafx.geometry.Side;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.CategoryAxisBuilder;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.LineChartBuilder;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.NumberAxisBuilder;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBuilder;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CheckBoxBuilder;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFieldBuilder;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TitledPaneBuilder;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.AnchorPaneBuilder;
import javafx.scene.paint.Paint;
import org.jrebirth.core.ui.DefaultView;
import org.jrebirth.core.ui.annotation.OnAction;

/**
 *
 * @author gshenoy
 */
public class CompanyView extends DefaultView<CompanyModel, AnchorPane, CompanyController> {

    @OnAction(name = "ShowCompany")
    Button showButton;
    @OnAction(name = "CompareCompany")
    Button compareButton;
    @OnAction(name = "CompareWith")
    CheckBox compareWithCheck;

    public CompanyView(final CompanyModel model) {
        super(model);
    }

    @Override
    protected void initView() {

        AnchorPane contentPane = AnchorPaneBuilder.create().build();

        Label companyLabel = LabelBuilder.create().layoutX(32.0).layoutY(65.0).styleClass("tsLabel").text("Company").build();
        Label currencyLabel = LabelBuilder.create().layoutX(293.0).layoutY(65.0).styleClass("tsLabel").text("Currency").build();
        Label compareWithLabel = LabelBuilder.create().layoutX(31.0).layoutY(103.0).styleClass("tsLabel").text("Compare with").build();

        TextField companyText = TextFieldBuilder.create().layoutX(118.0).layoutY(62.0).style("tsTextInput").prefWidth(150.0).build();
        TextField currencyText = TextFieldBuilder.create().layoutX(352.0).layoutY(62.0).style("tsTextInput").prefWidth(92.0).build();
        TextField compareWithText = TextFieldBuilder.create().layoutX(118.0).layoutY(100.0).style("tsTextInput").prefWidth(150.0).build();

        showButton = ButtonBuilder.create().mnemonicParsing(false).styleClass("tsButton").text("Show").layoutX(500.0).layoutY(57.0).build();
        compareButton = ButtonBuilder.create().mnemonicParsing(false).styleClass("tsButton").text("Compare").layoutX(500.0).layoutY(98.0).build();

        AnchorPane titlePaneContent = AnchorPaneBuilder.create().prefHeight(343.0).prefWidth(571.0).build();

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Date");
        xAxis.setSide(Side.BOTTOM);
        xAxis.setStyle("tsLabel");
        xAxis.setTickLabelFill(Paint.valueOf("white"));

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Share Value");
        yAxis.setSide(Side.LEFT);
        yAxis.setStyle("tsLabel");
        yAxis.setTickLabelFill(Paint.valueOf("white"));

        LineChart<String, Number> trendChart = new LineChart<String, Number>(xAxis, yAxis);
        trendChart.setLayoutX(1.0);
        trendChart.setPrefWidth(570.0);
        trendChart.setPrefHeight(331.0);

        titlePaneContent.getChildren().add(trendChart);

        TitledPane titledPane = TitledPaneBuilder.create().prefHeight(369.0).prefWidth(563.0).layoutX(11.0).layoutY(169.0).style("-fx-base: #333333;").text("Company Profile").content(titlePaneContent).build();

        compareWithCheck = CheckBoxBuilder.create().layoutX(32.0).layoutY(130.0).mnemonicParsing(false).styleClass("tsLabel").text("Compare").build();

        contentPane.getChildren().addAll(companyLabel, currencyLabel, compareWithLabel, companyText, currencyText, compareWithText, showButton, compareButton, titledPane, compareWithCheck);

        getRootNode().getChildren().add(contentPane);

    }
}
