package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.HashSet;
import java.util.Set;

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
    Set<ContactData> before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().
            withId(modifiedContact.getId()).withName("test1").withLastname("test1").withAddress("address").withHomePhone("22222").withMobilePhone("11111111").withEmail("yushkova@haulmont.com");
    app.contact().modify(contact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size());

    before.remove(modifiedContact);
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}
