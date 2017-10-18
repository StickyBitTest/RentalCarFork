package com.rentalcar.models.user;

public class Client extends User {

    private String passport;
    private CreditCard creditCard;
    private Account account;

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public void addCard(CreditCard card) {
        this.creditCard = card;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
