package com.crm.testCases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.base.Base;

public class CRMLoginTest extends Base{
	
	CRMLoginPage loginPage; 
	
	@BeforeClass
	public void setUp() throws Exception {
		loadconfigFile();
		openBrowser();
		loginPage = new  CRMLoginPage();
	}


	public void loginCRMTest() throws Exception {

		loginPage.loginCRM();
	}
	
	@Test
	public void addPeopleTest() {
		
		loginPage.loginCRM();
		loginPage.navigateAddPeoplePage();
		loginPage.addPerson();
			
	}
	

	@Test(dependsOnMethods={"addPeopleTest"})
	public void addCasesTest() throws InterruptedException {
		
		loginPage.navigateCasesPage();
		loginPage.addCase();
		
	}
	

//	@AfterClass
	
	
	
//	public void closeBrowser() {
//       tearDown();
//	}
	
}


