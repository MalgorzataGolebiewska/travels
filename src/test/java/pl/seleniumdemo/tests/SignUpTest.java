package pl.seleniumdemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pl.seleniumdemo.model.User;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.LoggedUserPage;
import pl.seleniumdemo.pages.SignUpPage;

import java.util.List;

public class SignUpTest extends BaseTest {


    @Test
    public void signUpTest() {

        String firstName = "Pepino";
        int randomNumber = (int) (Math.random() * 1000);
        String email = "pepino" + randomNumber + "@tester.pl";

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.setFirstName(firstName);
        signUpPage.setLastName("Testowy");
        signUpPage.setPhone("123456789");
        signUpPage.setEmail(email);
        signUpPage.setPassword("Test123");
        signUpPage.setConfirmPassword("Test123");
        signUpPage.signUpButton();




        LoggedUserPage loggedUserPage = new LoggedUserPage(driver);
        Assert.assertTrue(loggedUserPage.getHeadingText().contains(firstName));
        Assert.assertEquals(loggedUserPage.getHeadingText(), "Hi, Pepino Testowy");


    }

    @Test
    public void signUpTest2() {


        int randomNumber = (int) (Math.random() * 1000);
        String email = "pepino" + randomNumber + "@tester.pl";

        User user = new User();
        user.setFirstName("Pepino");
        user.setLastName("Testowy");
        user.setPhone("123456789");
        user.setEmail(email);
        user.setPassword("test123");

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();

        SignUpPage signUpPage = new SignUpPage(driver);
        // signUpPage.fillSignUpForm("firstName","Testowy","123456789", "test","test","test");
        signUpPage.fillSignUpForm(user);

        LoggedUserPage loggedUserPage = new LoggedUserPage(driver);
        Assert.assertTrue(loggedUserPage.getHeadingText().contains(user.getLastName()));
        Assert.assertEquals(loggedUserPage.getHeadingText(), "Hi, Pepino Testowy");


    }
    @Test
    public void signUpEmptyFormTest() {
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.signUpButton();


        List<String> errors = signUpPage.getErrors();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(errors.contains("The Email field is required."));
        softAssert.assertTrue(errors.contains("The Password field is required."));
        softAssert.assertTrue(errors.contains("The Password field is required."));
        softAssert.assertTrue(errors.contains("The First name field is required."));
        softAssert.assertTrue(errors.contains("The Last Name field is required."));
        softAssert.assertAll();


    }

    @Test
    public void signUpInvalidEmailTest() {

        String firstName = "Pepino";

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.setFirstName(firstName);
        signUpPage.setLastName("Testowy");
        signUpPage.setPhone("123456789");
        signUpPage.setEmail("tester");
        signUpPage.setPassword("Test123");
        signUpPage.setConfirmPassword("Test123");
        signUpPage.signUpButton();


        ;

        Assert.assertTrue(signUpPage.getErrors().contains("The Email field must contain a valid email address."));


    }
}
