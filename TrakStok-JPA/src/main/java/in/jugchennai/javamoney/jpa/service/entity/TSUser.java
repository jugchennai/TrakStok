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
package in.jugchennai.javamoney.jpa.service.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Rajmahendra Hegde <rajmahendra@gmail.com>
 */
@Entity
@Table(name = "TS_USERS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TSUser.findAll", query = "SELECT u FROM TSUser u"),
    @NamedQuery(name = "TSUser.findByUserid", query = "SELECT u FROM TSUser u WHERE u.userid = :userid"),
    @NamedQuery(name = "TSUser.findByUsername", query = "SELECT u FROM TSUser u WHERE u.username = :username"),
    @NamedQuery(name = "TSUser.findByPassword", query = "SELECT u FROM TSUser u WHERE u.password = :password"),
    @NamedQuery(name = "TSUser.findByUserNameAndPassword", query = "SELECT u FROM TSUser u WHERE u.username = :username AND u.password = :password"),
    @NamedQuery(name = "TSUser.findByLastlogin", query = "SELECT u FROM TSUser u WHERE u.lastlogin = :lastlogin")})
public class TSUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long userid;
    private String displayName;
    private String username;
    private String password;
    private boolean adminrole = false;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastlogin;

    public TSUser() {
    }

    public TSUser(Long userid) {
        this.userid = userid;
    }

    public TSUser(String username, String password) {
        this.username = username;
        this.password = password;

    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastlogin() {
        return lastlogin;
    }

    public void setLastlogin(Date lastlogin) {
        this.lastlogin = lastlogin;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }   

    public boolean isAdminrole() {
        return adminrole;
    }

    public void setAdminrole(boolean adminrole) {
        this.adminrole = adminrole;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userid != null ? userid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TSUser)) {
            return false;
        }
        TSUser other = (TSUser) object;
        if ((this.userid == null && other.userid != null) || (this.userid != null && !this.userid.equals(other.userid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TSUser { userid :" + userid + " username : " + username + " password : " + password + "}";
    }
}
