package com.revature.model;

public class UserPayment {

    private int id;
    private String payment_type;
    private String provider;
    private int account_no;

    private User user;
    private int user_id_fk;

    public UserPayment() {
    }

    public UserPayment(int id, String payment_type, String provider, int account_no, User user, int user_id_fk) {
        this.id = id;
        this.payment_type = payment_type;
        this.provider = provider;
        this.account_no = account_no;
        this.user = user;
        this.user_id_fk = user_id_fk;
    }

    public UserPayment(String payment_type, String provider, int account_no) {
        this.payment_type = payment_type;
        this.provider = provider;
        this.account_no = account_no;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public int getAccount_no() {
        return account_no;
    }

    public void setAccount_no(int account_no) {
        this.account_no = account_no;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserPayment{" +
                "id=" + id +
                ", payment_type='" + payment_type + '\'' +
                ", provider='" + provider + '\'' +
                ", account_no=" + account_no +
                ", user=" + user +
                '}';
    }
}
