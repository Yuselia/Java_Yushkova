package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().gotoHomePage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("name", "Sourname", "test1","Test street", "12345", "89271111111", "yuselia@yandex.ru"));
        }
    }

    @Test (enabled = false)
    public void ContactDeletionTests() {
        List<ContactData> before = app.getContactHelper().getContactList();
        int index = before.size()-1;
        app.getContactHelper().selectContact(index);
        app.getContactHelper().deleteSelectedContacts();
        app.goTo().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), index);

        before.remove(index);
        Assert.assertEquals(before, after);
    }

}
