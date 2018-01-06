package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

    @Test
    public void ContactDeletionTests() {
        app.gotoHomePage();
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContacts();
        app.gotoHomePage();
    }

}
