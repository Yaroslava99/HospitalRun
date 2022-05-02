package hospitalrun.io.webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PatientsPage {
    protected WebDriver driver;
    WebDriverWait w;
    String pageHeader = "Patient Listing";

    @FindBy(tagName = "h1")
    private WebElement header;

    @FindBy(className = "settings-trigger")
    private WebElement cogwheel;

    @FindBy(className = "logout")
    private WebElement logout;

    public PatientsPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver, this);
        w = new WebDriverWait(driver,Duration.ofSeconds(60));
    }

    public void logout() {
        this.cogwheel.click();
        this.logout.click();
    }

    public boolean isPageOpened(){
        w.until(ExpectedConditions.visibilityOf(header));
        return header.getText().equals(pageHeader);
    }
}
