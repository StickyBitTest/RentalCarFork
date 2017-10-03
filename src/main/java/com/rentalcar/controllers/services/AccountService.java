package com.rentalcar.controllers.services;

import com.rentalcar.dao.abstracted.AccountDAO;
import com.rentalcar.dao.DAOFactory;
import com.rentalcar.dao.implementation.AccountRentalDAO;
import com.rentalcar.models.Account;
import com.rentalcar.models.AccountAuthInfo;

public class AccountService {

    private AccountService(){}

    private static class SingletonHolder{
        private static AccountService instance = new AccountService();
    }

    public static AccountService getInstance() {
        return SingletonHolder.instance;
    }

    public boolean signUp(Account account){
        if(account == null)
            return false;
        AccountDAO accountDAO = DAOFactory.getAccountDAO();
        if(accountDAO.add(account) > 0)
            return logIn(account);
        return false;
    }

    public boolean logIn(Account account){
        if(account == null)
            return false;
        AccountDAO accountDAO = DAOFactory.getAccountDAO();
        Account foundAccount = accountDAO.get(account);
        if(foundAccount != null){
            account.setAdmin(foundAccount.isAdmin());
            account.setMail(foundAccount.getMail());
            return true;
        }
        return false;
    }

    public boolean logOut(AccountAuthInfo authInfo){
        return false;
    }
}
