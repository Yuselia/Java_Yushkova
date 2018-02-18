package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.sun.javafx.tools.packager.PackagerException;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

  @Parameter (names = "-c", description = "Contact count")
  public int count;

  @Parameter (names = "-f", description = "Target file")
  public String file;

  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);

    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<ContactData> contacts = generateContacts(count);
    save(contacts, new File(file));
  }

  private void save(List<ContactData> contacts, File file) throws IOException {
    Writer writer = new FileWriter(file);
    for (ContactData contact: contacts) {
      writer.write(String.format("%s:%s;%s;%s;%s;%s;%s;%s;%s;%s;src/test/resources/stru.png\n", contact.getName(), contact.getLastname(),
              contact.getAddress(), contact.getHomePhone(), contact.getMobilePhone(),
              contact.getWorkPhone(), contact.getEmail(), contact.getEmail2(),
              contact.getEmail3(), contact.getGroup()));
    }
    writer.close();
  }

  private List<ContactData> generateContacts(int count) {
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
