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

import in.jugchennai.javamoney.trakstok.beans.Page;
import in.jugchennai.javamoney.trakstok.ui.TSWaves;
import javafx.event.ActionEvent;
import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.ui.DefaultController;
import org.jrebirth.core.wave.WaveData;

/**
 *
 * @author gshenoy
 */
public class MemberController extends DefaultController<MemberModel, MemberView> {

    /**
     * Default Constructor.
     *
     * @param view the view to control
     *
     * @throws CoreException if an error occurred while creating event handlers
     */
    public MemberController(final MemberView view) throws CoreException {
        super(view);
    }

    public void onActionCompany(final ActionEvent actionEvent) {
        getModel().sendWave(TSWaves.SHOW_PAGE, WaveData.build(TSWaves.PAGE, Page.COMPANY));
    }

    public void onActionCurrencyExchange(final ActionEvent actionEvent) {
    }

    public void onActionLogout(final ActionEvent actionEvent) {
    }
}
