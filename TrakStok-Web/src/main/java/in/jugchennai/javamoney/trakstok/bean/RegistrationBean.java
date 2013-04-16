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

import in.jugchennai.javamoney.jpa.service.UserService;
import in.jugchennai.javamoney.jpa.service.entity.TsUsers;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.apache.log4j.Logger;

/**
 *
 * @author Rajmahendra Hegde <rajmahendra@gmail.com>
 */
@ManagedBean
public class RegistrationBean extends TSBaseFormBean {
    
    TsUsers tsusers;

    @NotNull(message = "UserName must not be null")
    @Size(min = 4, max = 16, message = "Username must be atleast 4 characters and max of 16")
    private String userName;
    @NotNull(message = "Password must not be null")
    @Size(min = 4, max = 16, message = "Password must be atleast 4 characters and max of 16")
    private String password;
    private String reenterPassword;
    private String displayName;
    private Integer updateValue = 0;

    public RegistrationBean() {
        logger = Logger.getLogger(RegistrationBean.class);
    }

    public RegistrationBean(String userName, String password, String displayName) {
        this.userName = userName;
        this.password = password;
        this.displayName = displayName;
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
   UserService service;
     
        service=new UserService();
        tsusers= new TsUsers();
        tsusers.setUserid(updateValue);
        tsusers.setDisplayname(displayName);
        tsusers.setAdminrole(false);
        tsusers.setUsername(userName);
        tsusers.setPassword(password);
        tsusers.setLastlogin(new java.util.GregorianCalendar().getTime());

            if(service.addUser(tsusers)){
                addMessage(FacesMessage.SEVERITY_INFO, "User Registration Successful!!!", null);
                return "success";
            }

            addMessage(FacesMessage.SEVERITY_ERROR, "User Registration Failed!!!", null);
            return "failure";
    }
}
