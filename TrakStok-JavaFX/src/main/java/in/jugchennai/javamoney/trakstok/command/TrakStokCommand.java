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
package in.jugchennai.javamoney.trakstok.command;

import org.jrebirth.core.command.DefaultCommand;
import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.wave.Wave;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>TrakStokCommand</strong> used to process short action into
 * the JRebirth Internal Thread.
 *
 * @author
 */
public final class TrakStokCommand extends DefaultCommand {

    /**
     * The class logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TrakStokCommand.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void ready() throws CoreException {
        // You must put your initialization code here (if any)
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void execute(final Wave wave) {

    }
}
