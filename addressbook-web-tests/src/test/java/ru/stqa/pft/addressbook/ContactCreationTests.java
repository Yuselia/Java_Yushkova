package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.initContactCreation();
        app.fillContactForm(new ContactData("name", "Sourname", "Test street", "12345", "89271111111"));
        app.submitContactCreation();
        app.gotoHomePage();
    }

}
