package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase{

    @Test
    public void ContactDeletionTests() {
        app.gotoHomePage();
        app.selectContact();
        app.deleteSelectedContacts();
        app.gotoHomePage();
    }

}
