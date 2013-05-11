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
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Balaji T
 */
@Entity
@Table(name = "TS_CURRENCY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TsCurrency.findAll", query = "SELECT t FROM TsCurrency t"),
    @NamedQuery(name = "TsCurrency.findByCurrencyCode", query = "SELECT t FROM TsCurrency t WHERE t.currencyCode = :currencyCode"),
    @NamedQuery(name = "TsCurrency.findByCurrencyName", query = "SELECT t FROM TsCurrency t WHERE t.currencyName = :currencyName"),
    @NamedQuery(name = "TsCurrency.searchByCurrencyCode", query = "SELECT t FROM TsCurrency t WHERE upper(t.currencyCode) like upper(:currencyCode)")
    })
public class TsCurrency implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Basic(optional = false)
    @Column(name = "CURRENCY_CODE")
    @Id
    private String currencyCode;
    
    @Basic(optional = false)
    @Column(name = "CURRENCY_NAME")
    private String currencyName;

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.currencyCode);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TsCurrency other = (TsCurrency) obj;
        if (!Objects.equals(this.currencyCode, other.currencyCode)) {
            return false;
        }
        return true;
    }
    
}
