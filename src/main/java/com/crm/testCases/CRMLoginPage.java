package com.crm.testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.crm.base.Base;

public class CRMLoginPage extends Base{
		
	@FindBy(xpath = "//input[@id='login:usernameDecorate:username']")
	WebElement userName;
	
	@FindBy(xpath = "//input[@id='login:passwordDecorate:password']")
	WebElement password;
	
	@FindBy(xpath = "//input[@id='login:login']")
	WebElement loginButton;
	
	@FindBy(xpath = "//*[@role='navigation']//a[@aria-label='People & Organisations']")
	WebElement peopleMainMenu;
	
	@FindBy(xpath = "//a[@href='/party/person/new']")
	WebElement addPerson;
		
	@FindBy(xpath = "//span[@class='currentPage']")
	WebElement newPersonTitle;
	
	@FindBy(xpath = "//select[@name='party:j_id108:j_id116']")
	WebElement titleDDL;
	
	@FindBy(xpath = "//select[@name='party:j_id108:j_id116']//option[@value='Mr']")
	WebElement selectMr;
	
	@FindBy(xpath = "//input[@id='party:fnDecorate:fn']")
	WebElement firstName;
	
	@FindBy(xpath = "//input[@id='party:lnDecorate:ln']")
	WebElement lastName;
		
	@FindBy(xpath = "//input[@id='party:roleDecorate:jobTitle']")
	WebElement jobTitle;
	
	@FindBy(xpath = "//input[@id='party:orgDecorate:org']")
	WebElement orgnisationName;
	
	@FindBy(xpath = "//input[@id='party:tagsDecorate:tagComboBox']")
	WebElement tags;
	
	@FindBy(xpath = "//input[@id='party:tagsDecorate:j_id187']")
	WebElement addTags;
		
	@FindBy(xpath = "//input[@id='party:j_id325:0:phnDecorate:number']")
	WebElement phoneNumber;
	
	@FindBy(xpath = "//input[@id='party:j_id342:0:emlDecorate:nmbr']")
	WebElement emailAddress;
		
	@FindBy(xpath = "//input[@id='party:j_id370:0:webDecorate:web']")
	WebElement website;
	
	@FindBy(xpath = "//input[@id='party:save']")
	WebElement saveAddPeopleButton;
		
	@FindBy(xpath = "//nav[@role='navigation']//a[@href='/cases']")
	WebElement casesMainMenu;
		
	@FindBy(xpath = "//a[@href='/case/add']")
	WebElement addCaseButton;

	@FindBy(xpath = "//input[@id='partySearch']")
	WebElement partySearch;
	
	@FindBy(xpath = "//input[@id='caseNameDecorate:name']")
	WebElement caseName;
	
	@FindBy(xpath = "//textarea[@id='caseDescriptionDecorate:description']")
	WebElement caseDescription;
		
	@FindBy(xpath = "//input[@id='tagsDecorate:tagComboBox']")
	WebElement caseTag;
		
	@FindBy(xpath = "//input[@id='tagsDecorate:j_id191']")
	WebElement caseAddTag;
	
	@FindBy(xpath = "//input[@id='save']")
	WebElement caseSaveButton;
	
	@FindBy(xpath = "//*[@id='ember91']")
	WebElement getCaseName;
	
	@FindBy(xpath = "//div[@class='kase-summary ember-view']//span[contains(text(), 'Open')]")
	WebElement caseOpenStaus;
		
	@FindBy(xpath = "//a[@class='ember-view']")
	WebElement verifiCaseName;
	
		
	public CRMLoginPage() {
		PageFactory.initElements(driver, this);
	}	
	
	public void loginCRM() {
		
		driver.get(prop.getProperty("baseURL"));
		userName.sendKeys(prop.getProperty("userName"));
		password.sendKeys(prop.getProperty("password"));
		loginButton.click();

	}
	
	public void navigateAddPeoplePage () {
    
		peopleMainMenu.click();
    	explicitWait(addPerson);
		
	}
	
    public void addPerson () {
    	
    	peopleMainMenu.click();
    	explicitWait(addPerson);
    	addPerson.click();
    	explicitWait(newPersonTitle);
		titleDDL.click();
		selectMr.click();
		firstName.sendKeys(Constants.FIRST_NAME);
		lastName.sendKeys(Constants.LAST_NAME);
		jobTitle.sendKeys(Constants.JOB_TITLE);
		orgnisationName.sendKeys(Constants.ORGANISATION_NAME);
		tags.sendKeys(Constants.TAGS);
		addTags.click();
		phoneNumber.sendKeys(Constants.PHONE_NUMBER);
		emailAddress.sendKeys(Constants.EMAIL_ADDRESS);
		website.sendKeys(Constants.WEBSITE);
		saveAddPeopleButton.click();
		driver.navigate().refresh();
		
    }
    
    public void navigateCasesPage() {
    	
    	explicitWait(casesMainMenu);
    	casesMainMenu.click();
    	explicitWait(addCaseButton);
    	driver.navigate().refresh();
    	
    }
    
   public void addCase() throws InterruptedException {
	 
	   addCaseButton.click();
	   explicitWait(partySearch);
	   partySearch.sendKeys(Constants.FIRST_NAME);
	   Thread.sleep(1000);
	   partySearch.sendKeys(Keys.ARROW_DOWN);
	   partySearch.sendKeys(Keys.ENTER);
	   caseName.sendKeys(Constants.CASE_NAME);
	   caseDescription.sendKeys(Constants.CASE_DESCRIPTION);
	   caseTag.sendKeys(Constants.CASE_TAG);
	   caseAddTag.click();
	   caseSaveButton.click();
	   driver.navigate().refresh();
	   navigateCasesPage();
	   Thread.sleep(2000);
	   String fullName = Constants.FIRST_NAME + " "+ Constants.LAST_NAME + "," +" "+ Constants.CASE_NAME;
	   String partialName = Constants.FIRST_NAME + " "+ Constants.LAST_NAME;
	   String getFullName=  driver.findElement(By.xpath("//*[@class='list-results-cell is-kase is-system is-summary ember-view']//a[contains(text(),'"+ partialName +"')]")).getText();
	   //--Assert if the Case is Created? -
	   Assert.assertEquals(getFullName, fullName);
	   //--Assert if the Case is in Open status? -
	   driver.findElement(By.xpath("//*[@class='list-results-cell is-kase is-system is-summary ember-view']//a[contains(text(),'"+ partialName +"')]")).click();
	   explicitWait(caseOpenStaus);
	   Assert.assertEquals(caseOpenStaus.getText(), "Open");
	   //--Assert if the Case Name is Created? -
	   Assert.assertEquals(verifiCaseName.getText(), partialName);
   }
     
}
