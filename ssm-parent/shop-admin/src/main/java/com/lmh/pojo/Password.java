package com.lmh.pojo;

public class Password {
    private String password;
    private String newPassword;
    private String reNewPassword;

    public Password() {
    }

    @Override
    public String toString() {
        return "Password{" +
                "password='" + password + '\'' +
                ", newPassword='" + newPassword + '\'' +
                ", reNewPassword='" + reNewPassword + '\'' +
                '}';
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getReNewPassword() {
        return reNewPassword;
    }

    public void setReNewPassword(String reNewPassword) {
        this.reNewPassword = reNewPassword;
    }

    public Password(String password, String newPassword, String reNewPassword) {
        this.password = password;
        this.newPassword = newPassword;
        this.reNewPassword = reNewPassword;
    }
}
