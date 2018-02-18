package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContacts() {
        File photo = new File("src/test/resources/stru.png");
        List<Object[]> list = new ArrayList<Object[]>();
        list.add(new Object[] {new ContactData().withName("test 1").withLastname("lastname 1").withAddress("address 1")
                .withGroup("test1").withHomePhone("12345").withMobilePhone("89271111111")
        .withEmail("yuselia@yandex.ru").withPhoto(photo)});
        list.add(new Object[] {new ContactData().withName("test 2").withLastname("lastname 1").withAddress("address 1")
                .withGroup("test1").withHomePhone("12345").withMobilePhone("89271111111")
                .withEmail("yuselia@yandex.ru").withPhoto(photo)});
        list.add(new Object[] {new ContactData().withName("test 3").withLastname("lastname 1").withAddress("address 1")
                .withGroup("test1").withHomePhone("12345").withMobilePhone("89271111111")
                .withEmail("yuselia@yandex.ru").withPhoto(photo)});
        return list.iterator();
    }

    @Test (dataProvider = "validContacts")
    public void testContactCreation(ContactData contact) {

        app.goTo().homePage();
        Contacts before = app.contact().all();
        app.contact().create(contact);
        assertThat(app.contact().count(), equalTo(before.size()+1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }
}
