package com.Hotel;

import java.util.Objects;

public class Client {
    private String firstName;
    private String lastName;
    private String telephone;
    private String creditCard;
    private String address;
    private String CIN;

    public Client(String firstName, String lastName, String telephone, String creditCard, String address, String CIN) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephone = telephone;
        this.creditCard = creditCard;
        this.address = address;
        this.CIN = CIN;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCIN() {
        return CIN;
    }

    public void setCIN(String CIN) {
        this.CIN = CIN;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(firstName, client.firstName) && Objects.equals(lastName, client.lastName) && Objects.equals(telephone, client.telephone) && Objects.equals(creditCard, client.creditCard) && Objects.equals(address, client.address) && Objects.equals(CIN, client.CIN);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, telephone, creditCard, address, CIN);
    }

    @Override
    public String toString() {
        return "Client{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", telephone='" + telephone + '\'' +
                ", creditCard='" + creditCard + '\'' +
                ", address='" + address + '\'' +
                ", CIN='" + CIN + '\'' +
                '}';
    }
}