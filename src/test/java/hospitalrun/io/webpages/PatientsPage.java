package hospitalrun.io.webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class PatientsPage {
    protected WebDriver driver;

    @FindBy(tagName = "h1")
    private WebElement header;

    @FindBy(className = "settings-trigger")
    private WebElement cogwheel;

    @FindBy(className = "logout")
    private WebElement logout;

    public PatientsPage(WebDriver driver){
        this.driver=driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        PageFactory.initElements(driver, this);
    }

    public void Logout() {
        this.cogwheel.click();
        this.logout.click();
    }

    public boolean isPageOpened(){
        return header.getText().equals("Patient Listing");
    }
}
