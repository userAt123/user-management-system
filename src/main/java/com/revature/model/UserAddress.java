package com.revature.model;

public class UserAddress {

    private int id;
    private String address;
    private String city;
    private int zip_code;
    private String country;

    private User user;

    private int user_id_fk;

    public UserAddress(int userIdFk) {
        user_id_fk = userIdFk;
    }

    public UserAddress(int id, String address, String city, int zip_code, String country, User user, int userIdFk) {
        this.id = id;
        this.address = address;
        this.city = city;
        this.zip_code = zip_code;
        this.country = country;
        this.user = user;
        this.user_id_fk = userIdFk;
    }

    public UserAddress(String address, String city, int zip_code, String country) {
        this.address = address;
        this.city = city;
        this.zip_code = zip_code;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getZip_code() {
        return zip_code;
    }

    public void setZip_code(int zip_code) {
        this.zip_code = zip_code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserAddress{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", zip_code=" + zip_code +
                ", country='" + country + '\'' +
                ", user=" + user +
                '}';
    }

}
