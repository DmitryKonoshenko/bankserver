package com.divanxan.data;

import java.math.BigDecimal;
import java.util.Objects;

public class Account {

    private Long id;
    private String firstName;
    private String secondName;
    private String email;
    private BigDecimal account;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getAccount() {
        return account;
    }

    public void setAccount(BigDecimal account) {
        this.account = account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return getId().equals(account.getId()) &&
                getFirstName().equals(account.getFirstName()) &&
                Objects.equals(getSecondName(), account.getSecondName()) &&
                getEmail().equals(account.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getSecondName(), getEmail());
    }
}
