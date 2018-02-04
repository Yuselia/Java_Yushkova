package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

public class ContactModificationTests extends TestBase {

 @BeforeMethod
 public void ensurePreconditions() {
   app.goTo().homePage();
   if (app.contact().all().size()==0){
     app.contact().create(new ContactData().withName("name").withLastname("Sourname").withGroup("test1").withAddress("Test street").withHomePhone("12345").withMobilePhone("89271111111").withEmail("yuselia@yandex.ru"));
   }
 }

  @Test
  public void testContactModification() {
    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().
            withId(modifiedContact.getId()).withName("test1").withLastname("test1").withAddress("address").withHomePhone("22222").withMobilePhone("11111111").withEmail("yushkova@haulmont.com");
    app.contact().modify(contact);
    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }
}
