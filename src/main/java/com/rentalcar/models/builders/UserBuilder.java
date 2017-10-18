package com.rentalcar.models.builders;

import com.rentalcar.controllers.security.Crypto;
import com.rentalcar.models.builders.validators.UserValidator;
import com.rentalcar.models.user.Account;
import com.rentalcar.models.user.Client;
import com.rentalcar.models.user.CreditCard;

import java.util.Date;

import static com.rentalcar.constants.MessageWrapper.Error;

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
        client.addCard(getCreditCard());
        client.setEmail(email);
        client.setFullName(fullName);
        return client;
    }

    public CreditCard getCreditCard() throws EntityBuilderException {
        validate();
        return new CreditCard(cardNumber, cardExpires, cvv2);
    }


    public UserBuilder setLogin(String login) {
        validate(isLoginValid(login), Error.LOGIN);
        this.login = login;
        return this;
    }

    public UserBuilder setPassword(String password) {
        validate(isPasswordValid(password), Error.PASSWORD);
        this.password = Crypto.sha256(password);
        return this;

    }

    public UserBuilder setAdmin(boolean isAdmin){
        this.isAdmin = isAdmin;
        return this;
    }
    

    public UserBuilder setPassport(String passport){
        validate(isPassportValid(passport), Error.CLIENT_PASSPORT);
        this.passport = passport;
        return this;
    }

    public UserBuilder setCardNumber(String cardNumber){
        validate(isValidCardNumber(cardNumber), Error.CARD_NUMBER);
        this.cardNumber = cardNumber;
        return this;
    }

    public UserBuilder setCardExpiresDate(Date expiresDate){
        validate(isValidExpireDate(expiresDate), Error.CARD_EXPIRES_DATE);
        this.cardExpires = expiresDate;
        return this;
    }

    public UserBuilder setCVV2(int cvv2){
        validate(isValidCVV2(cvv2), Error.CVV2);
        this.cvv2 = cvv2;
        return this;
    }


    public UserBuilder setEmail(String email){
        validate(isEmailValid(email), Error.EMAIL);
        this.email = email;
        return this;
    }


    public UserBuilder setFullName(String fullName){
        validate(isFullNameValid(fullName), Error.FULL_NAME);
        this.fullName = fullName;
        return this;
    }

    public UserBuilder setConfirmPassword(String confirmPassword){
        validate(password.equals(Crypto.sha256(confirmPassword)), Error.PASSWORD_MATCHES);
        return this;
    }

}