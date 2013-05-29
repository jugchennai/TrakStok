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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.money.MonetaryAmount;
import javax.money.Money;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Rajmahendra Hegde <rajmahendra@gmail.com>
 */
@Entity
@Table(name = "TS_COMPANY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TsCompany.findAll", query = "SELECT t FROM TsCompany t"),
    @NamedQuery(name = "TsCompany.findByCompanyid", query = "SELECT t FROM TsCompany t WHERE t.companyid = :companyid"),
    @NamedQuery(name = "TsCompany.findByDisplayname", query = "SELECT t FROM TsCompany t WHERE t.displayname = :displayname"),
    @NamedQuery(name = "TsCompany.findBySymbol", query = "SELECT t FROM TsCompany t WHERE t.symbol = :symbol"),
    @NamedQuery(name = "TsCompany.listBySymbol", query = "SELECT t FROM TsCompany t WHERE upper(t.symbol) like upper(:symbol)")})
public class TsCompany implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "COMPANYID")
    private Integer companyid;
    @Basic(optional = false)
    @Column(name = "DISPLAYNAME")
    private String displayname;
    @Basic(optional = false)
    @Column(name = "SYMBOL")
    private String symbol;
    @OneToMany(mappedBy = "companyid")
    private Collection<TsStockInflection> tsStockInflectionCollection;
    @OneToMany(mappedBy = "companyid")
    private Collection<TsUsersFavorite> tsUsersFavoriteCollection;
    
    private static final String DEFAULT_CURRENCY_CODE = "USD";
    
    public TsCompany() {
    }

    public TsCompany(Integer companyid) {
        this.companyid = companyid;
    }

    public TsCompany(Integer companyid, String displayname, String symbol) {
        this.companyid = companyid;
        this.displayname = displayname;
        this.symbol = symbol;
    }

    public Integer getCompanyid() {
        return companyid;
    }

    public void setCompanyid(Integer companyid) {
        this.companyid = companyid;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @XmlTransient
    public Collection<TsStockInflection> getTsStockInflectionCollection() {
        return tsStockInflectionCollection;
    }

    public void setTsStockInflectionCollection(Collection<TsStockInflection> tsStockInflectionCollection) {
        this.tsStockInflectionCollection = tsStockInflectionCollection;
    }

    @XmlTransient
    public Collection<TsUsersFavorite> getTsUsersFavoriteCollection() {
        return tsUsersFavoriteCollection;
    }

    public void setTsUsersFavoriteCollection(Collection<TsUsersFavorite> tsUsersFavoriteCollection) {
        this.tsUsersFavoriteCollection = tsUsersFavoriteCollection;
    }
    
    public MonetaryAmount getLatestShareValue(){
        MonetaryAmount amount = null;
        List<TsStockInflection> list = sortInflectionDesc();
        amount = Money.of(DEFAULT_CURRENCY_CODE,list.get(0).getAmount());
        return amount;       
    }
    
    public double getValueDiff(){
        List<TsStockInflection> list = sortInflectionDesc();
        return list.get(0).getAmount() - list.get(1).getAmount();
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (companyid != null ? companyid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TsCompany)) {
            return false;
        }
        TsCompany other = (TsCompany) object;
        if ((this.companyid == null && other.companyid != null) || (this.companyid != null && !this.companyid.equals(other.companyid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "in.jugchennai.javamoney.jpa.service.entity.TsCompany[ companyid=" + companyid + " ]";
    }

    private List<TsStockInflection> sortInflectionDesc() {
        List<TsStockInflection> list = new ArrayList<>();
        if(!tsStockInflectionCollection.isEmpty() && tsStockInflectionCollection instanceof List){
            list.addAll(tsStockInflectionCollection);
            Collections.sort(list,new ShareValueComparator());            
        }
        System.out.println(list);
        return list;
    }

    private class ShareValueComparator implements Comparator<TsStockInflection>{

        public ShareValueComparator() {
        }

        @Override
        public int compare(TsStockInflection o1, TsStockInflection o2) {
            //Decending order
            return (o2.getDatetime().compareTo(o1.getDatetime()));            
        }
    }
}
