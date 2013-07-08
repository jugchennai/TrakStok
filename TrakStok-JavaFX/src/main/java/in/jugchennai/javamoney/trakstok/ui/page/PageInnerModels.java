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

import in.jugchennai.javamoney.trakstok.ui.company.CompanyModel;
import in.jugchennai.javamoney.trakstok.ui.login.LoginModel;
import in.jugchennai.javamoney.trakstok.ui.member.MemberModel;
import org.jrebirth.core.key.UniqueKey;
import org.jrebirth.core.ui.InnerModels;
import org.jrebirth.core.ui.Model;

/**
 *
 * @author gshenoy
 */
public enum PageInnerModels implements InnerModels {

    LOGIN(LoginModel.class),
    
    MEMBER(MemberModel.class),
    
    COMPANY(CompanyModel.class);
                        
    private final Class<? extends Model> modelClass;
    
    PageInnerModels(final Class<? extends Model> modelClass) {
        this.modelClass = modelClass;
    }
    
    @Override
    public Class<? extends Model> getModelClass() {
        return this.modelClass;
    }

    @Override
    public UniqueKey getKey() {
        return null;
    }
}
