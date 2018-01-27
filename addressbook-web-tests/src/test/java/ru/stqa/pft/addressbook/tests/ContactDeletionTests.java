package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactDeletionTests extends TestBase {

    @Test
    public void ContactDeletionTests() {
        app.getNavigationHelper().gotoHomePage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("name", "Sourname", "test1","Test street", "12345", "89271111111", "yuselia@yandex.ru"));
        }
        int before=app.getContactHelper().getContactCount();
        app.getContactHelper().selectContact(before);
        app.getContactHelper().deleteSelectedContacts();
        app.getNavigationHelper().gotoHomePage();
        int after=app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before-1);
    }

}
