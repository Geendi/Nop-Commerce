package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ContactUsPage;
import pages.HomePage;
import utilities.LoadProperties;

public class ContactUsTest extends TestBase{
    HomePage homePage;
    ContactUsPage contactUsPage;

    String fullName = LoadProperties.userData.getProperty("fullName");
    String friendEmail = LoadProperties.userData.getProperty("friendEmail");
    String enquiry = LoadProperties.userData.getProperty("enquiry");
    String enquiryMessage = LoadProperties.userData.getProperty("enquiryMessage");

    @Test
    public void sendContactUsEnquiry() {
        homePage = new HomePage(driver);
        contactUsPage = new ContactUsPage(driver);
        homePage.openContactUsPage();
        contactUsPage.contactUsData(fullName, friendEmail, enquiry);
        Assert.assertEquals(contactUsPage.assertSubmittedEnquiry(),enquiryMessage );
    }
}
