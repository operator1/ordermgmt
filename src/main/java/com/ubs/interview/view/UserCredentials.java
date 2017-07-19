package com.ubs.interview.view;

public class UserCredentials {

    private String username;
    private String company;
    private String password;

    public UserCredentials() {
    }

    public UserCredentials(String username, String company, String password) {
        this.username = username;
        this.company = company;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final UserCredentials that = (UserCredentials) o;

        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (company != null ? !company.equals(that.company) : that.company != null) return false;
        return password != null ? password.equals(that.password) : that.password == null;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (company != null ? company.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
