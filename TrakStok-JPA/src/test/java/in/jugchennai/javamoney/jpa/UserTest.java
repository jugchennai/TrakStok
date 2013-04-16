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
package in.jugchennai.javamoney.jpa;

import in.jugchennai.javamoney.jpa.service.CompanyService;
import in.jugchennai.javamoney.jpa.service.UserService;
import in.jugchennai.javamoney.jpa.service.entity.TsCompany;
import in.jugchennai.javamoney.jpa.service.entity.TsUsers;
import in.jugchennai.javamoney.jpa.service.entity.TsUsersFavorite;
import java.util.Collection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Rajmahendra Hegde <rajmahendra@gmail.com>
 */
public class UserTest {

    public UserTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testAddANewUser() {

        TsUsers raj = new TsUsers();
        raj.setDisplayname("Rajmahendra");
        raj.setUsername("rajonjava");
        raj.setPassword("raj");
        raj.setAdminrole(true);

        assertTrue("User is already exists.", UserService.addUser(raj));

    }

    @Test
    public void testAddNewUserFavorite() {
        TsUsersFavorite userFavorite = new TsUsersFavorite();

        TsUsers raj = ((Collection<TsUsers>) UserService.findByUsername("rajonjava")).iterator().next();
        assertNotNull("User Not found", raj);
        TsCompany sun = ((Collection<TsCompany>) CompanyService.findBySymbol("SUN")).iterator().next();
        assertNotNull("Company Not found", raj);
        userFavorite.setCompanyid(sun);
        userFavorite.setUserid(raj);
        assertTrue(UserService.addUserFavorite(userFavorite));

    }
}