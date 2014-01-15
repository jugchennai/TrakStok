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
package in.jugchennai.javamoney.trakstok.ui.login;

import in.jugchennai.javamoney.trakstok.beans.Page;
import in.jugchennai.javamoney.trakstok.beans.User;
import in.jugchennai.javamoney.trakstok.service.TrakStokService;
import in.jugchennai.javamoney.trakstok.ui.TSWaves;
import org.jrebirth.core.ui.DefaultModel;
import org.jrebirth.core.wave.Wave;
import org.jrebirth.core.wave.WaveData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author gshenoy
 */
public class LoginModel extends DefaultModel<LoginModel, LoginView> {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginModel.class);

    @Override
    protected void initModel() {
        listen(TrakStokService.RE_ON_LOGIN);
    }

    public void doOnLogin(final User user, final Wave wave) {

        LOGGER.info("doOnLogin()");

        if (user != null) {

            sendWave(TSWaves.SHOW_PAGE, WaveData.build(TSWaves.PAGE, Page.MEMBER));
            
        } else {

            LOGGER.info("doOnLogin() - Login failed");
            getView().invalidLogin();

        }

    }

    @Override
    protected void showView() {
        super.showView(); //To change body of generated methods, choose Tools | Templates.
    }
}
