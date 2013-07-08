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
package in.jugchennai.javamoney.trakstok.service;

import in.jugchennai.javamoney.trakstok.beans.User;
import in.jugchennai.javamoney.trakstok.ui.TSWaves;
import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.service.ServiceBase;
import org.jrebirth.core.wave.Wave;
import org.jrebirth.core.wave.WaveTypeBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>TrakStokService</strong>.
 *
 * @author
 */
public final class TrakStokService extends ServiceBase {

    /**
     * Perform something.
     */
    public static final WaveTypeBase DO_LOGIN = WaveTypeBase.build("LOGIN");
    /**
     * Wave type to return when something was done.
     */
    public static final WaveTypeBase RE_ON_LOGIN = WaveTypeBase.build("ON_LOGIN", TSWaves.USER);
    /**
     * The class logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TrakStokService.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void ready() throws CoreException {
        super.ready();

        LOGGER.info("TrakStokService.ready()");

        // Define the service method
        registerCallback(DO_LOGIN, RE_ON_LOGIN);



    }

    /**
     * Do something.
     *
     * @param wave the source wave
     */
    public User doLogin(final Wave wave) {

        LOGGER.info("doLogin()");
        return new User();
        // Put code to do it !
    }
}
