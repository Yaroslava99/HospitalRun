package hospitalrun.io.helperclasses;

import io.github.bonigarcia.wdm.config.DriverManagerType;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import io.github.bonigarcia.wdm.managers.EdgeDriverManager;
import io.github.bonigarcia.wdm.managers.FirefoxDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

abstract public class AbstractTest {
    protected WebDriver driver;

    @BeforeEach
    public void setup(){
        String browserName = getBrowserParameter();
        driver = setBrowser(browserName);
    }

    @AfterEach
    public void close(){
        driver.close();
    }

    protected String getBrowserParameter() {
        String value = System.getProperty("browser");
        if (value == null || value.isEmpty())
            value = "chrome";
        return value;
    }

    public WebDriver setBrowser(String browserName) {
        WebDriver driver = null;
        if (browserName.equalsIgnoreCase("chrome")) {
            ChromeDriverManager.getInstance(DriverManagerType.CHROME).setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");
            driver = new ChromeDriver(options);
        }
        if (browserName.equalsIgnoreCase("firefox")) {
            FirefoxDriverManager.getInstance(DriverManagerType.FIREFOX).setup();
            driver = new FirefoxDriver();
        }
        if (browserName.equalsIgnoreCase("edge")) {
            EdgeDriverManager.getInstance(DriverManagerType.EDGE).setup();
            driver = new EdgeDriver();
        }
        return driver;
    }
}
