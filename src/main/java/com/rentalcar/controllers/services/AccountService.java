package com.rentalcar.controllers.services;

import com.rentalcar.dao.abstracted.AccountDAO;
import com.rentalcar.dao.DAOFactory;
import com.rentalcar.models.user.Account;

public class AccountService {

    private AccountService(){}

    private static class SingletonHolder{
        private static AccountService instance = new AccountService();
    }

    public static AccountService getInstance() {
        return SingletonHolder.instance;
    }

    public boolean signUp(Account account){
        if(account != null){
            AccountDAO accountDAO = DAOFactory.getAccountDAO();
            if(accountDAO.add(account) > 0)
                return logIn(account);
        }
        return false;
    }

    public boolean logIn(Account account){
        Account found = getAccount(account);
        if(found == null)
            return false;
        account.setFullName(found.getFullName());
        account.setAdmin(found.isAdmin());
        return true;
    }

    private Account getAccount(Account account){
        if(account != null) return DAOFactory.getAccountDAO().get(account);
        return null;
    }
}
