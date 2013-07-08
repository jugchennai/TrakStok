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
package in.jugchennai.javamoney.trakstok.ui;

import in.jugchennai.javamoney.trakstok.beans.Page;
import in.jugchennai.javamoney.trakstok.beans.User;
import org.jrebirth.core.wave.WaveItem;
import org.jrebirth.core.wave.WaveType;
import org.jrebirth.core.wave.WaveTypeBase;

/**
 *
 * @author gshenoy
 */
public interface TSWaves {

    /**
     * The page to display.
     */
    WaveItem<Page> PAGE = new WaveItem<Page>() {
    };
    WaveType SHOW_PAGE = WaveTypeBase.build("SHOW_PAGE", PAGE);
    WaveItem<User> USER = new WaveItem<User>() {
    };
}
