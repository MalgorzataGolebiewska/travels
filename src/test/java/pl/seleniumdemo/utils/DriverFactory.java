package pl.seleniumdemo.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.FirefoxDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    public static WebDriver getDriver(String name) {
        if (name.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            return new FirefoxDriver();

        } else {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        }

    }
}
