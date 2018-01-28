package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getNavigationHelper().gotoHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("name", "Sourname", "test1","Test street", "12345", "89271111111", "yuselia@yandex.ru"));
    }
    //int before=app.getContactHelper().getContactCount();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initContactModification(before.size()-1);
    app.getContactHelper().fillContactForm(new ContactData("n", "ln", null,"address", "22222", "11111111", "yushkova@haulmont.com"), false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    //int after=app.getContactHelper().getContactCount();
    Assert.assertEquals(after.size(), before.size());

    after.remove(after.size()-1);
    before.remove(before.size()-1);
    Assert.assertEquals(before, after);
  }
}
