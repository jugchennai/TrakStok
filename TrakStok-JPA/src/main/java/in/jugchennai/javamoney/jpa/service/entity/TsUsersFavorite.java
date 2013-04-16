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
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Rajmahendra Hegde <rajmahendra@gmail.com>
 */
@Entity
@Table(name = "TS_USERS_FAVORITE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TsUsersFavorite.findAll", query = "SELECT t FROM TsUsersFavorite t"),
    @NamedQuery(name = "TsUsersFavorite.findByFavoriteid", query = "SELECT t FROM TsUsersFavorite t WHERE t.favoriteid = :favoriteid")})
public class TsUsersFavorite implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "FAVORITEID")
    private Integer favoriteid;
    @JoinColumn(name = "USERID", referencedColumnName = "USERID")
    @ManyToOne
    private TsUsers userid;
    @JoinColumn(name = "COMPANYID", referencedColumnName = "COMPANYID")
    @ManyToOne
    private TsCompany companyid;

    public TsUsersFavorite() {
    }

    public TsUsersFavorite(Integer favoriteid) {
        this.favoriteid = favoriteid;
    }

    public Integer getFavoriteid() {
        return favoriteid;
    }

    public void setFavoriteid(Integer favoriteid) {
        this.favoriteid = favoriteid;
    }

    public TsUsers getUserid() {
        return userid;
    }

    public void setUserid(TsUsers userid) {
        this.userid = userid;
    }

    public TsCompany getCompanyid() {
        return companyid;
    }

    public void setCompanyid(TsCompany companyid) {
        this.companyid = companyid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (favoriteid != null ? favoriteid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TsUsersFavorite)) {
            return false;
        }
        TsUsersFavorite other = (TsUsersFavorite) object;
        if ((this.favoriteid == null && other.favoriteid != null) || (this.favoriteid != null && !this.favoriteid.equals(other.favoriteid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "in.jugchennai.javamoney.jpa.service.entity.TsUsersFavorite[ favoriteid=" + favoriteid + " ]";
    }
}
