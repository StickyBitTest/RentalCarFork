package com.rentalcar.models.builders;

import com.rentalcar.controllers.security.Crypto;
import com.rentalcar.controllers.utils.ErrorMessage;
import com.rentalcar.models.builders.validators.UserValidator;
import com.rentalcar.models.user.Account;
import com.rentalcar.models.user.Client;
import com.rentalcar.models.user.CreditCard;

import java.util.Date;


/**
 * Builder for user entities and user attributes
 */
public class UserBuilder extends EntityBuilder implements UserValidator {

    private String login;
    private String password;
    private boolean isAdmin;

    private String passport;

    private String cardNumber;
    private Date cardExpires;
    private int cvv2;

    private String fullName;
    private String email;

    public Account getAccount() throws EntityBuilderException {
        validate();
        Account account = new Account();
        account.setLogin(login);
        account.setPassword(password);
        account.setAdmin(isAdmin);
        account.setEmail(email);
        account.setFullName(fullName);
        return account;
    }

    public Client getClient() throws EntityBuilderException{
        validate();
        Client client = new Client();
        client.setPassport(passport);
        client.setAccount(getAccount());
        client.setCard(getCreditCard());
        client.setEmail(email);
        client.setFullName(fullName);
        return client;
    }

    public CreditCard getCreditCard() throws EntityBuilderException {
        validate();
        CreditCard creditCard = new CreditCard(cardNumber, cardExpires, cvv2);
        return creditCard;
    }


    public UserBuilder setLogin(String login) {
        validate(isLoginValid(login), ErrorMessage.ERROR_LOGIN);
        this.login = login;
        return this;
    }

    public UserBuilder setPassword(String password) {
        validate(isPasswordValid(password), ErrorMessage.ERROR_PASSWORD);
        this.password = Crypto.sha256(password);
        return this;

    }

    public UserBuilder setAdmin(boolean isAdmin){
        this.isAdmin = isAdmin;
        return this;
    }
    

    public UserBuilder setPassport(String passport){
        validate(isPassportValid(passport), ErrorMessage.ERROR_CLIENT_PASSPORT);
        this.passport = passport;
        return this;
    }

    public UserBuilder setCardNumber(String cardNumber){
        validate(isValidCardNumber(cardNumber), ErrorMessage.ERROR_CARD_NUMBER);
        this.cardNumber = cardNumber;
        return this;
    }

    public UserBuilder setCardExpiresDate(Date expiresDate){
        validate(isValidExpireDate(expiresDate), ErrorMessage.ERROR_CARD_EXPIRES_DATE);
        this.cardExpires = expiresDate;
        return this;
    }

    public UserBuilder setCVV2(int cvv2){
        validate(isValidCVV2(cvv2), ErrorMessage.ERROR_CVV2);
        this.cvv2 = cvv2;
        return this;
    }


    public UserBuilder setEmail(String email){
        validate(isEmailValid(email), ErrorMessage.ERROR_EMAIL);
        this.email = email;
        return this;
    }


    public UserBuilder setFullName(String fullName){
        validate(isFullNameValid(fullName), ErrorMessage.ERROR_FULL_NAME);
        this.fullName = fullName;
        return this;
    }

    public UserBuilder setConfirmPassword(String confirmPassword){
        validate(password.matches(confirmPassword), ErrorMessage.ERROR_PASSWORD_MATCHES);
        return this;
    }

}