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

import in.jugchennai.javamoney.trakstok.beans.User;
import in.jugchennai.javamoney.trakstok.service.TrakStokService;
import in.jugchennai.javamoney.trakstok.ui.TSWaves;
import javafx.event.ActionEvent;
import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.ui.DefaultController;
import org.jrebirth.core.wave.WaveData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author gshenoy
 */
public class LoginController extends DefaultController<LoginModel, LoginView> {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    /**
     * Default Constructor.
     *
     * @param view the view to control
     *
     * @throws CoreException if an error occurred while creating event handlers
     */
    public LoginController(final LoginView view) throws CoreException {
        super(view);
    }
    
    public void onActionLogin(final ActionEvent actionEvent) {
        
        LOGGER.info("onActionLogin()");
        
        User user = new User();
        user.setName(getView().getLogin());
        user.setPassword(getView().getPassword());
        
        getModel().returnData(TrakStokService.class, TrakStokService.DO_LOGIN, WaveData.build(TSWaves.USER, user));
        
    }
}
