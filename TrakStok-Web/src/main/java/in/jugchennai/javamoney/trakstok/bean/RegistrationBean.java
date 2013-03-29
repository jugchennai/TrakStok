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
package in.jugchennai.javamoney.trakstok.bean;

import javax.faces.bean.ManagedBean;
import org.apache.log4j.Logger;

/**
 *
 * @author Rajmahendra Hegde <rajmahendra@gmail.com>
 */
@ManagedBean
public class RegistrationBean extends TSBaseFormBean {

    private String fullName;
    private String userName;
    private String password;
    private String reenterPassword;
    private String displayName;
    private long updateValue = 0;

    public RegistrationBean() {
        logger = Logger.getLogger(RegistrationBean.class);
    }

    public RegistrationBean(String fullName, String userName, String password, String displayName) {
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
        this.displayName = displayName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getReenterPassword() {
        return reenterPassword;
    }

    public void setReenterPassword(String reenterPassword) {
        this.reenterPassword = reenterPassword;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String whenRegisteration() {
        return null;
    }
}
