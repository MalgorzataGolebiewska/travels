package pl.seleniumdemo.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.ResultsPage;

import java.util.List;
import java.util.stream.Collectors;

public class HotelSearchTest extends BaseTest {


    @Test
    public void searchHotelTest()  {
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setCity("Dubai");
        hotelSearchPage.setDates("29/05/2023", "30/05/2023");
        hotelSearchPage.setTravellers(1,2);
        hotelSearchPage.performSearch();

        ResultsPage resultsPage = new ResultsPage(driver);
        List<String> hotelNames = resultsPage.getHotelNames();

        Assert.assertEquals(hotelNames.get(0),"Jumeirah Beach Hotel");
        Assert.assertEquals(hotelNames.get(1),"Oasis Beach Tower");
        Assert.assertEquals(hotelNames.get(2),"Rose Rayhaan Rotana");
        Assert.assertEquals(hotelNames.get(3),"Hyatt Regency Perth");


    }


    @Test
    public void hotelWithoutNameSearchTest() {

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setDates("29/05/2023","30/05/2023");
        hotelSearchPage.setTravellers(0, 1);
        hotelSearchPage.performSearch();

        ResultsPage resultsPage = new ResultsPage(driver);

        Assert.assertTrue(resultsPage.resultHeading.isDisplayed());
        Assert.assertEquals(resultsPage.getHeadingText(), "No Results Found");

    }


}
