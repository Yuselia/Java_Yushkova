package ru.stqa.pft.addressbook.appmanager;

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

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
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
    wd.findElement(By.xpath("//input[@id='"+id+"']/../../td[8]")).click();
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void returnToHomePage() {
    click(By.linkText("home page"));
  }

  public void create(ContactData contact) {
    initContactCreation();
    fillContactForm(contact, true);
    submitContactCreation();
    contactCache=null;
    returnToHomePage();
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

  public int getContactCount() {
    return wd.findElements(By.xpath("//table[@id='maintable']/tbody/tr")).size() - 1;
  }

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache!=null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> strings = wd.findElements(By.xpath("//tr[@name]"));

    for (int i=0; i<strings.size(); i++)
    {
      int id=Integer.parseInt(strings.get(i).findElement(By.xpath("td[1]/input")).getAttribute("value"));
      String lastName = strings.get(i).findElement(By.xpath("./td[2]")).getText();
      String firstName = strings.get(i).findElement(By.xpath("./td[3]")).getText();
      contactCache.add(new ContactData().withId(id).withName(firstName).withLastname(lastName));
    }
    return new Contacts(contactCache);
  }
}
