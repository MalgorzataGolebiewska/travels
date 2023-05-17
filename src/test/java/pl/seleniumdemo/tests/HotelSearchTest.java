package pl.seleniumdemo.tests;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.ResultsPage;
import pl.seleniumdemo.utils.ExcelReader;

import java.io.IOException;
import java.util.List;

public class HotelSearchTest extends BaseTest {


    @Test
    public void searchHotelTest() throws IOException {
        ExtentTest test = extentReports.createTest("Search Hotel Test");
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setCity("Dubai");
        test.log(Status.PASS,"Setting city done");
        hotelSearchPage.setDates("29/05/2023", "30/05/2023");
        test.log(Status.PASS,"Setting dates done");
        hotelSearchPage.setTravellers(1, 2);
        test.log(Status.PASS,"Setting travellers done");
        hotelSearchPage.performSearch();
        test.log(Status.PASS,"Performing search done");
        test.log(Status.PASS,"Screenshot", MediaEntityBuilder.createScreenCaptureFromPath("src/test/resources/screenshots/failedTest.png").build());

        ResultsPage resultsPage = new ResultsPage(driver);
        List<String> hotelNames = resultsPage.getHotelNames();

        Assert.assertEquals(hotelNames.get(0), "Jumeirah Beach Hotel");
        Assert.assertEquals(hotelNames.get(1), "Oasis Beach Tower");
        Assert.assertEquals(hotelNames.get(2), "Rose Rayhaan Rotana");
        Assert.assertEquals(hotelNames.get(3), "Hyatt Regency Perth");
        test.log(Status.PASS,"Assertions passed");


    }


    @Test
    public void hotelWithoutNameSearchTest() {

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setDates("29/05/2023", "30/05/2023");
        hotelSearchPage.setTravellers(0, 1);
        hotelSearchPage.performSearch();

        ResultsPage resultsPage = new ResultsPage(driver);

        Assert.assertTrue(resultsPage.resultHeading.isDisplayed());
        Assert.assertEquals(resultsPage.getHeadingText(), "No Results Found");

    }

    @Test(dataProvider = "data")
    public void searchHotelTestWithDataProvider(String city, String hotel) {
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setCity(city);
        hotelSearchPage.setDates("29/05/2023", "30/05/2023");
        hotelSearchPage.setTravellers(1, 2);
        hotelSearchPage.performSearch();

        ResultsPage resultsPage = new ResultsPage(driver);
        List<String> hotelNames = resultsPage.getHotelNames();

        Assert.assertEquals(hotelNames.get(0), hotel);



    }

    @DataProvider
    public Object[][] data() throws IOException {
        return ExcelReader.readExcel("testData.xlsx");
    }


}
