package ru.stqa.pft.addressbook.generators;

import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

  public static void main(String[] args) throws IOException {
    int count = Integer.parseInt(args[0]);
    File file = new File(args[1]);

    List<ContactData> contacts = generateContacts(count);
    save(contacts, file);
  }

  private static void save(List<ContactData> contacts, File file) throws IOException {
    Writer writer = new FileWriter(file);
    for (ContactData contact: contacts) {
      writer.write(String.format("%s:%s;%s;%s;%s;%s;%s;%s;%s;%s\n", contact.getName(), contact.getLastname(),
              contact.getAddress(), contact.getHomePhone(), contact.getMobilePhone(),
              contact.getWorkPhone(), contact.getEmail(), contact.getEmail2(),
              contact.getEmail3(), contact.getGroup()));
    }
    writer.close();
  }

  private static List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    for (int i = 0; i < count; i++) {
      contacts.add(new ContactData().withName(String.format("test %s", i))
              .withLastname(String.format("lastname %s", i))
              .withAddress(String.format("address %s", i))
              .withHomePhone(String.format("%s%s%s", i, i, i))
              .withMobilePhone(String.format("%s%s%s%s", i, i, i, i))
              .withWorkPhone(String.format("%s%s", i, i))
              .withEmail(String.format("yuselia+%s@yandex.ru", i))
              .withEmail2(String.format("yuselia+%s%s@yandex.ru", i, i))
              .withEmail3(String.format("yuselia+%s%s%s@yandex.ru", i, i, i))
              .withGroup(String.format("test 1")));
    }
    return contacts;

  }
}
