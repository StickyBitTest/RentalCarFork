package com.rentalcar.dao.mysql;

import com.rentalcar.dao.DAOFactory;
import com.rentalcar.dao.abstracted.AccountDAO;
import com.rentalcar.models.builders.EntityBuilderException;
import com.rentalcar.models.builders.UserBuilder;
import com.rentalcar.models.user.Account;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Test AccountDAO only for empty database
 */
public class AccountDAOTest {

    @Test
    public void testAddAdmin(){
        AccountDAO accountDAO = new DAOFactory().getAccountDAO();
        Account account = getAdminAccount();
        int id = accountDAO.add(account);
        assertTrue(id>0);
    }

    @Test
    public void testAddTestUser(){
        AccountDAO accountDAO = new DAOFactory().getAccountDAO();
        Account testAccount = getTestAccount();
        assertTrue(accountDAO.add(testAccount)>0);
    }

    @Test
    public void testGet(){
        Account account = getTestAccount();
        AccountDAO accountDAO = DAOFactory.getAccountDAO();
        Account newAccount = accountDAO.get(account);
        assertNotNull(newAccount);

    }

    private Account getAdminAccount(){
        Account account = null;
        UserBuilder builder = new UserBuilder()
                .setFullName("Vasya Pupkin")
                .setLogin("admin")
                .setPassword("gotohell")
                .setEmail("vasya@gmail.com")
                .setAdmin(true);
        try {
            account = builder.getAccount();
        } catch (EntityBuilderException e) {
            e.printStackTrace();
        }
        return account;
    }

    private Account getTestAccount(){
        Account account = null;
        UserBuilder builder = new UserBuilder()
                .setFullName("Petya Pupkin")
                .setLogin("petya")
                .setPassword("gotohell")
                .setEmail("petya@gmail.com")
                .setAdmin(false);
        try {
            account = builder.getAccount();
        } catch (EntityBuilderException e) {
            e.printStackTrace();
        }
        return account;
    }
}