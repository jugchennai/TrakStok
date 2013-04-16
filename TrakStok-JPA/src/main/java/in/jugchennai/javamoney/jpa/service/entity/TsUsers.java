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
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Rajmahendra Hegde <rajmahendra@gmail.com>
 */
@Entity
@Table(name = "TS_USERS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TsUsers.findAll", query = "SELECT t FROM TsUsers t"),
    @NamedQuery(name = "TsUsers.findByUserid", query = "SELECT t FROM TsUsers t WHERE t.userid = :userid"),
    @NamedQuery(name = "TsUsers.findByDisplayname", query = "SELECT t FROM TsUsers t WHERE t.displayname = :displayname"),
    @NamedQuery(name = "TsUsers.findByUsername", query = "SELECT t FROM TsUsers t WHERE t.username = :username"),
    @NamedQuery(name = "TsUsers.findByPassword", query = "SELECT t FROM TsUsers t WHERE t.password = :password"),
    @NamedQuery(name = "TsUsers.findByAdminrole", query = "SELECT t FROM TsUsers t WHERE t.adminrole = :adminrole"),
    @NamedQuery(name = "TsUsers.findByLastlogin", query = "SELECT t FROM TsUsers t WHERE t.lastlogin = :lastlogin")})
public class TsUsers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "USERID")
    private Integer userid;
    @Basic(optional = false)
    @Column(name = "DISPLAYNAME")
    private String displayname;
    @Basic(optional = false)
    @Column(name = "USERNAME")
    private String username;
    @Basic(optional = false)
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "ADMINROLE")
    private boolean adminrole;
    @Column(name = "LASTLOGIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastlogin;
    @OneToMany(mappedBy = "userid")
    private Collection<TsUsersFavorite> tsUsersFavoriteCollection;

    public TsUsers() {
    }

    public TsUsers(Integer userid) {
        this.userid = userid;
    }

    public TsUsers(Integer userid, String displayname, String username, String password) {
        this.userid = userid;
        this.displayname = displayname;
        this.username = username;
        this.password = password;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
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

    public boolean getAdminrole() {
        return adminrole;
    }

    public void setAdminrole(boolean adminrole) {
        this.adminrole = adminrole;
    }

    public Date getLastlogin() {
        return lastlogin;
    }

    public void setLastlogin(Date lastlogin) {
        this.lastlogin = lastlogin;
    }

    @XmlTransient
    public Collection<TsUsersFavorite> getTsUsersFavoriteCollection() {
        return tsUsersFavoriteCollection;
    }

    public void setTsUsersFavoriteCollection(Collection<TsUsersFavorite> tsUsersFavoriteCollection) {
        this.tsUsersFavoriteCollection = tsUsersFavoriteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userid != null ? userid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TsUsers)) {
            return false;
        }
        TsUsers other = (TsUsers) object;
        if ((this.userid == null && other.userid != null) || (this.userid != null && !this.userid.equals(other.userid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "in.jugchennai.javamoney.jpa.service.entity.TsUsers[ userid=" + userid + " ]";
    }
}
