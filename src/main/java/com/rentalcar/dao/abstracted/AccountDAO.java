package com.rentalcar.dao.abstracted;

import com.rentalcar.models.Account;

public interface AccountDAO {

    int add(Account account);

    Account get(Account account);

}
