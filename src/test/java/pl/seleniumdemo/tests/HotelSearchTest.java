package pl.seleniumdemo.tests;

import org.openqa.selenium.By;
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
        hotelSearchPage.setTravellers();
        hotelSearchPage.performSearch();

        ResultsPage resultsPage = new ResultsPage(driver);
        List<String> hotelNames = resultsPage.getHotelNames();

        Assert.assertEquals(hotelNames.get(0),"Jumeirah Beach Hotel");
        Assert.assertEquals(hotelNames.get(1),"Oasis Beach Tower");
        Assert.assertEquals(hotelNames.get(2),"Rose Rayhaan Rotana");
        Assert.assertEquals(hotelNames.get(3),"Hyatt Regency Perth");


    }

    /*
    @Test
    public void hotelWithoutNameSearchTest() {

        driver.findElement(By.name("checkin")).click();
        driver.findElements(By.xpath("//td[@class='day ' and text()='11']"))
                .stream()
                .filter(ell -> ell.isDisplayed())
                .findFirst()
                .ifPresent(ell -> ell.click());

        driver.findElement(By.name("checkout")).click();
        driver.findElements(By.xpath("//td[@class='day ' and text()='30']"))
                .stream()
                .filter(el -> el.isDisplayed())
                .findFirst()
                .ifPresent(el -> el.click());

        driver.findElement(By.id("travellersInput")).click();
        driver.findElement(By.id("childPlusBtn")).click();
        driver.findElement(By.xpath("//button[text()=' Search']")).click();

        WebElement noResult = driver.findElement(By.xpath("//div[@class='itemscontainer']//h2"));
        Assert.assertTrue(noResult.isDisplayed());
        Assert.assertEquals(noResult.getText(), "No Results Found");

    }*/


}
