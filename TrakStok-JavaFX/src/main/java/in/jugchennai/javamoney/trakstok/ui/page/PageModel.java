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
package in.jugchennai.javamoney.trakstok.ui.page;

import in.jugchennai.javamoney.trakstok.beans.Page;
import in.jugchennai.javamoney.trakstok.beans.User;
import in.jugchennai.javamoney.trakstok.ui.TSWaves;
import in.jugchennai.javamoney.trakstok.ui.member.MemberModel;
import org.jrebirth.core.ui.DefaultModel;
import org.jrebirth.core.wave.Wave;

/**
 *
 * @author gshenoy
 */
public final class PageModel extends DefaultModel<PageModel, PageView> {

    @Override
    protected void initModel() {
        listen(TSWaves.SHOW_PAGE);
    }

    public void doShowPage(final Page page, final Wave wave) {

        switch (page) {
            case MEMBER:
                getView().onLogin();

                break;

            case COMPANY:
                getView().showCompany();
                break;
        }

    }
}
