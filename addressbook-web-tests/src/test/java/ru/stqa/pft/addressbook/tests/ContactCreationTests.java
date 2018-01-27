package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> before = app.getContactHelper().getContactList();
        //int before=app.getContactHelper().getContactCount();
        app.getContactHelper().createContact(new ContactData("name", "Sourname", "test1","Test street", "12345", "89271111111", "yuselia@yandex.ru"));
        List<ContactData> after = app.getContactHelper().getContactList();
        //int after=app.getContactHelper().getContactCount();
        Assert.assertEquals(after.size(), before.size()+1);

        after.remove(after.size()-1);
        Assert.assertEquals(before, after);
    }

}
