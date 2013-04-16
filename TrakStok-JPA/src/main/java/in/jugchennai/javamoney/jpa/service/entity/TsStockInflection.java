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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Rajmahendra Hegde <rajmahendra@gmail.com>
 */
@Entity
@Table(name = "TS_STOCK_INFLECTION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TsStockInflection.findAll", query = "SELECT t FROM TsStockInflection t"),
    @NamedQuery(name = "TsStockInflection.findByInflectionid", query = "SELECT t FROM TsStockInflection t WHERE t.inflectionid = :inflectionid"),
    @NamedQuery(name = "TsStockInflection.findByDatetime", query = "SELECT t FROM TsStockInflection t WHERE t.datetime = :datetime"),
    @NamedQuery(name = "TsStockInflection.findByAmount", query = "SELECT t FROM TsStockInflection t WHERE t.amount = :amount")})
public class TsStockInflection implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "INFLECTIONID")
    private Integer inflectionid;
    @Column(name = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datetime;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "AMOUNT")
    private Double amount;
    @JoinColumn(name = "COMPANYID", referencedColumnName = "COMPANYID")
    @ManyToOne
    private TsCompany companyid;

    public TsStockInflection() {
    }

    public TsStockInflection(Integer inflectionid) {
        this.inflectionid = inflectionid;
    }

    public Integer getInflectionid() {
        return inflectionid;
    }

    public void setInflectionid(Integer inflectionid) {
        this.inflectionid = inflectionid;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
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
        hash += (inflectionid != null ? inflectionid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TsStockInflection)) {
            return false;
        }
        TsStockInflection other = (TsStockInflection) object;
        if ((this.inflectionid == null && other.inflectionid != null) || (this.inflectionid != null && !this.inflectionid.equals(other.inflectionid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "in.jugchennai.javamoney.jpa.service.entity.TsStockInflection[ inflectionid=" + inflectionid + " ]";
    }
}
