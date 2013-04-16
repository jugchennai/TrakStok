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

import in.jugchennai.javamoney.jpa.service.entity.TsUsers;
import in.jugchennai.javamoney.jpa.service.entity.TsUsersFavorite;
import java.util.Collection;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Rajmahendra Hegde <rajmahendra@gmail.com>
 */
public class UserService {

    private static final Logger log = Logger.getGlobal();
    private static EntityManagerFactory emFactory;
    private static EntityManager eManager;

    private UserService() {
    }

    static {
        emFactory = Persistence.createEntityManagerFactory("trakStokPU");
        eManager = emFactory.createEntityManager();
    }

    public Collection<TsUsers> findAll() {
        return (Collection<TsUsers>) eManager.createNamedQuery("TsUsers.findAll")
                .getResultList();
    }

    public static Collection<TsUsers> findByUsername(String username) {
        return (Collection<TsUsers>) eManager.createNamedQuery("TsUsers.findByUsername")
                .setParameter("username", username)
                .getResultList();
    }

    public static Collection<TsUsers> findByPassword(String password) {
        return (Collection<TsUsers>) eManager.createNamedQuery("TsUsers.findByPassword")
                .setParameter("password", password)
                .getResultList();
    }

    public static boolean addUser(TsUsers newUser) {

        if (!findByUsername(newUser.getUsername()).isEmpty()) {
            return false;
        }

        eManager.getTransaction().begin();
        eManager.persist(newUser);
        eManager.getTransaction().commit();
        return true;
    }

    public static boolean addUserFavorite(TsUsersFavorite userFavorite) {

        if (UserService.findByUsername(userFavorite.getUserid().getUsername()).isEmpty()) {
            return false;
        }
        if (CompanyService.findBySymbol(userFavorite.getCompanyid().getSymbol()).isEmpty()) {
            return false;
        }
        eManager.getTransaction().begin();
        eManager.persist(userFavorite);
        eManager.getTransaction().commit();
        return true;
    }

    public static boolean validateUserName(String username) {
        Query validate = eManager.createQuery("select count from TSUser where username=:" + username);
        long counter = 0;
        counter = (Long) validate.getSingleResult();
        if (counter > 0) {
            return true;
        }
        return false;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        eManager.close();
        emFactory.close();
    }
}
