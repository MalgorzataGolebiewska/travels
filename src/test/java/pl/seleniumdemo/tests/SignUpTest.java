package pl.seleniumdemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.LoggedUserPage;
import pl.seleniumdemo.pages.SignUpPage;

import java.util.List;

public class SignUpTest extends BaseTest {


    @Test
    public void signUpTest() {

        String firstName = "Pepino";
        int randomNumber = (int) (Math.random() * 1000);

        LoggedUserPage loggedUserPage = new HotelSearchPage(driver)
                .openSignUpForm()
                .setFirstName(firstName)
                .setLastName("Testowy")
                .setPhone("123456789")
                .setEmail("pepino" + randomNumber + "@tester.pl")
                .setPassword("Test123")
                .setConfirmPassword("Test123")
                .signUpButton();


        Assert.assertTrue(loggedUserPage.getHeadingText().contains(firstName));
        Assert.assertEquals(loggedUserPage.getHeadingText(), "Hi, Pepino Testowy");


    }

    //
    @Test
    public void signUpEmptyFormTest() {
        SignUpPage signUpPage = new HotelSearchPage(driver)
                .openSignUpForm();
        signUpPage.signUpButton(); //zostaje tak jak jest, po openSignForm otworzyłoby stronę zalogowanego użytkownika, a tutaj nie wypełniamy formatki, przekierowałoby na LoggedUserPage


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

        SignUpPage signUpPage = new HotelSearchPage(driver)
                .openSignUpForm()
                .setFirstName(firstName)
                .setLastName("Testowy")
                .setPhone("123456789")
                .setEmail("tester")
                .setPassword("Test123")
                .setConfirmPassword("Test123");
        signUpPage.signUpButton();


        Assert.assertTrue(signUpPage.getErrors().contains("The Email field must contain a valid email address."));


    }
}
