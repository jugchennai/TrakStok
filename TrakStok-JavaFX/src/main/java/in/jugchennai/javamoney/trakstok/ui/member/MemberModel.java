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

import in.jugchennai.javamoney.trakstok.beans.Company;
import in.jugchennai.javamoney.trakstok.beans.User;
import in.jugchennai.javamoney.trakstok.service.TrakStokService;
import java.util.List;
import org.jrebirth.core.ui.DefaultModel;
import org.jrebirth.core.wave.Wave;

/**
 *
 * @author gshenoy
 */
public class MemberModel extends DefaultModel<MemberModel, MemberView> {

    @Override
    protected void initModel() {

        super.initModel();

        listen(TrakStokService.RE_ON_GET_USER);
        listen(TrakStokService.RE_ON_FIND_COMPANIES);
        System.out.println("member.initModel() Listening to re_on_get_user");
    }

    public void doOnFindUser(final User user, final Wave wave) {
        System.out.println("[doOnFindUser] " + user.getName());
        getView().updateUserDetails(user);
    }

    public void doOnFindCompanies(final List<Company> companies, final Wave wave) {
        System.out.println("[doOnFindCompanies] " + companies.size());
        getView().updateCompanies(companies);
    }
}
