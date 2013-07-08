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
package in.jugchennai.javamoney.trakstok.ui.page;

import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.PaneBuilder;
import javafx.scene.paint.Paint;
import javafx.scene.text.FontBuilder;
import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.ui.DefaultView;

/**
 *
 * @author gshenoy
 */
public class PageView extends DefaultView<PageModel, BorderPane, PageController> {

    /**
     * Instantiates a new page view.
     *
     * @param model the model
     * @throws CoreException the core exception
     */
    public PageView(final PageModel model) throws CoreException {
        super(model);
    }

    @Override
    protected void initView() {

        getRootNode().getStyleClass().add("tsbase");

        getRootNode().setPrefSize(800, 600);

        Pane header = PaneBuilder.create().prefHeight(55.0).prefWidth(600.0).build();
        Pane footer = PaneBuilder.create().prefHeight(55.0).prefWidth(600.0).build();

        Label footerLabel = LabelBuilder.create().layoutX(209.0).layoutY(21.0).text("(c) JUGChennai Adopt-a-JSR Program").styleClass("tsLabel").build();
        Label headerLabel = LabelBuilder.create().layoutX(429.0).layoutY(6.0).text("TrakStok").textFill(Paint.valueOf("blue")).font(FontBuilder.create().name("Arial Black").size(30.0).build()).build();

        header.getChildren().add(headerLabel);
        footer.getChildren().add(footerLabel);

        getRootNode().setTop(headerLabel);

        getRootNode().setCenter(getModel().getInnerModel(PageInnerModels.LOGIN).getRootNode());

        getRootNode().setBottom(footerLabel);

    }

    public void onLogin() {

        getRootNode().setCenter(getModel().getInnerModel(PageInnerModels.MEMBER).getRootNode());

    }

    public void showCompany() {

        getRootNode().setCenter(getModel().getInnerModel(PageInnerModels.COMPANY).getRootNode());

    }
}
