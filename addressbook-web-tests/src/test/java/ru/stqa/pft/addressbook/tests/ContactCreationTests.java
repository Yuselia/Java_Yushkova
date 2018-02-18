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
        List<Object[]> list = new ArrayList<Object[]>();
        list.add(new Object[] {"test 1", "lastname 1", "address 1", "test1", "12345", "89271111111", "yuselia@yandex.ru", "src/test/resources/stru.png"});
        list.add(new Object[] {"test 2", "lastname 2", "address 2", "test1", "12345", "89271111111", "yuselia@yandex.ru", "src/test/resources/stru.png"});
        list.add(new Object[] {"test 3", "lastname 3", "address 3", "test1", "12345", "89271111111", "yuselia@yandex.ru", "src/test/resources/stru.png"});
        return list.iterator();
    }

    @Test (dataProvider = "validContacts")
    public void testContactCreation(String name, String lastname, String address, String group, String homePhone, String mobilePhone, String email, String pathToPhoto) {
        File photo = new File(pathToPhoto);
        ContactData contact = new ContactData().withName(name).withLastname(lastname).withGroup(group)
               .withAddress(address).withHomePhone(homePhone).withMobilePhone(mobilePhone)
               .withEmail(email).withPhoto(photo);
        app.goTo().homePage();
        Contacts before = app.contact().all();
        app.contact().create(contact);
        assertThat(app.contact().count(), equalTo(before.size()+1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }
}
