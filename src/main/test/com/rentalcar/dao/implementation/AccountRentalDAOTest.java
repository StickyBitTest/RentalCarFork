package com.rentalcar.dao.implementation;

import com.rentalcar.controllers.security.Crypto;
import com.rentalcar.dao.DAOFactory;
import com.rentalcar.dao.abstracted.AccountDAO;
import com.rentalcar.models.Account;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountRentalDAOTest {

    @Test
    public void testAdd(){
        Account account = new Account();
        account.setLogin("admin");
        account.setPassword(Crypto.sha256("helloworld"));
        account.setMail("admin@gmail.com");
        account.setAdmin(true);
        AccountDAO accountDAO = DAOFactory.getAccountDAO();
        int id = accountDAO.add(account);
        System.out.println(id);
        assertTrue(id>0);
    }

    @Test
    public void testGet(){
        Account account = new Account();
        account.setLogin("admin");
        account.setPassword(Crypto.sha256("helloworld"));
        AccountDAO accountDAO = DAOFactory.getAccountDAO();
        Account newAccount = accountDAO.get(account);
        assertNotNull(newAccount);

    }

}