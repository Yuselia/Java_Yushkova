package ru.stqa.pft.addressbook.appmanager;

import com.sun.javafx.binding.StringFormatter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getName());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("email"), contactData.getEmail());
    attach(By.name("photo"), contactData.getPhoto());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void create(ContactData contact) {
    initContactCreation();
    fillContactForm(contact, true);
    submitContactCreation();
    contactCache=null;
    returnToHomePage();
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void deleteSelectedContacts() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    wd.switchTo().alert().accept();
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[id='"+id+"']")).click();
  }

  public void initContactModificationById(int id) {
    wd.findElement(By.xpath(String.format("//input[@id='%s']/../../td[8]/a", id))).click();
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void returnToHomePage() {
    click(By.linkText("home page"));
  }



  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContacts();
    contactCache=null;
  }

  public void modify(ContactData contact) {
    initContactModificationById(contact.getId());
    fillContactForm(contact, false);
    submitContactModification();
    contactCache=null;
    returnToHomePage();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int count() {
    return wd.findElements(By.xpath("//table[@id='maintable']/tbody/tr")).size() - 1;
  }

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache!=null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> rows = wd.findElements(By.xpath("//tr[@name]"));

    for (WebElement row: rows)
    {
      List<WebElement> cells=row.findElements(By.tagName("td"));
      int id=Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
      String lastName = cells.get(1).getText();
      String firstName = cells.get(2).getText();
      String address=cells.get(3).getText();
      String allEmails = cells.get(4).getText();
      String allPhones = cells.get(5).getText();
      contactCache.add(new ContactData().withId(id).withName(firstName).withLastname(lastName).withAddress(address)
              .withAllPhones(allPhones)
              .withAllEmails(allEmails));
    }
    return new Contacts(contactCache);
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());

    String firstname=wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname=wd.findElement(By.name("lastname")).getAttribute("value");
    String address=wd.findElement(By.name("address")).getText();
    String home=wd.findElement(By.name("home")).getAttribute("value");
    String mobile=wd.findElement(By.name("mobile")).getAttribute("value");
    String work=wd.findElement(By.name("work")).getAttribute("value");
    String email=wd.findElement(By.name("email")).getAttribute("value");
    String email2=wd.findElement(By.name("email2")).getAttribute("value");
    String email3=wd.findElement(By.name("email3")).getAttribute("value");

    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withName(firstname).withLastname(lastname).withAddress(address)
            .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work)
            .withEmail(email).withEmail2(email2).withEmail3(email3);
  }
}
