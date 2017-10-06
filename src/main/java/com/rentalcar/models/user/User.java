package com.rentalcar.models.user;

import com.rentalcar.models.Entity;

public abstract class User extends Entity {

    protected String fullName;
    protected String email;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
