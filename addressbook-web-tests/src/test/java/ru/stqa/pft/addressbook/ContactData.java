package ru.stqa.pft.addressbook;

public class ContactData {
  private final String name;
  private final String lastname;
  private final String address;
  private final String homePhone;
  private final String mobilePhone;

  public ContactData(String name, String lastname, String address, String homePhone, String mobilePhone) {
    this.name = name;
    this.lastname = lastname;
    this.address = address;
    this.homePhone = homePhone;
    this.mobilePhone = mobilePhone;
  }

  public String getName() {
    return name;
  }

  public String getLastname() {
    return lastname;
  }

  public String getAddress() {
    return address;
  }

  public String getHomePhone() {
    return homePhone;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }
}
