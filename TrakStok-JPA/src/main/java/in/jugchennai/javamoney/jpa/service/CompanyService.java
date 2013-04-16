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
package in.jugchennai.javamoney.jpa.service;

import in.jugchennai.javamoney.jpa.service.entity.TsCompany;
import java.util.Collection;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Rajmahendra Hegde <rajmahendra@gmail.com>
 */
public class CompanyService {

    private static final Logger log = Logger.getGlobal();
    private static EntityManagerFactory emFactory;
    private static EntityManager eManager;

    private CompanyService() {
    }

    static {
        emFactory = Persistence.createEntityManagerFactory("trakStokPU");
        eManager = emFactory.createEntityManager();
    }

    public static boolean addCompany(TsCompany company) {

        if (findBySymbol("SUNM").isEmpty()) {
            return false;
        }
        eManager.getTransaction().begin();
        eManager.persist(company);
        eManager.getTransaction().commit();
        return true;
    }

    public static Collection<TsCompany> findBySymbol(String symbol) {
        return (Collection<TsCompany>) eManager.createNamedQuery("TsCompany.findBySymbol")
                .setParameter("symbol", symbol)
                .getResultList();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        eManager.close();
        emFactory.close();
    }
}
