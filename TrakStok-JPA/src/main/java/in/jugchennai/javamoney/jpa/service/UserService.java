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

import in.jugchennai.javamoney.jpa.service.entity.TSUser;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Rajmahendra Hegde <rajmahendra@gmail.com>
 */
public class UserService {

    private EntityManagerFactory emFactory;
    private EntityManager eManager;

    public UserService() {
        emFactory = Persistence.createEntityManagerFactory("trakStokPU");
        eManager = emFactory.createEntityManager();
    }

    public Collection<TSUser> findAll() {
        return (Collection<TSUser>) eManager.createNamedQuery("TSUser.findAll")
                .getResultList();
    }

    public Collection<TSUser> findByUsername(String username) {
        return (Collection<TSUser>) eManager.createNamedQuery("TSUser.findByUsername")
                .setParameter("username", username)
                .getResultList();
    }

    public Collection<TSUser> findByPassword(String password) {
        return (Collection<TSUser>) eManager.createNamedQuery("TSUser.findByPassword")
                .setParameter("password", password)
                .getResultList();
    }

    public boolean addUser(TSUser newUser) {
        eManager.getTransaction().begin();
        eManager.persist(newUser);
        eManager.getTransaction().commit();
        return true;
    }

    public boolean validateUserName(String username) {
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
