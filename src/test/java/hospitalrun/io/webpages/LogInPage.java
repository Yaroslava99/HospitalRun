package hospitalrun.io.webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LogInPage{
    protected WebDriver driver;
    WebDriverWait w;
    String expectedHeader = "PLEASE SIGN IN";
    private static final String PAGE_URL="http://demo.hospitalrun.io/";
    String errorText = "Username or password is incorrect.";

    @FindBy(tagName = "h2")
    private WebElement header;

    @FindBy(id = "identification")
    private WebElement login;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(xpath = "//*[@type='submit']")
    private WebElement signInBtn;

    @FindBy(className = "alert")
    private WebElement errorMessage;

    public LogInPage(WebDriver driver){
        this.driver = driver;
        driver.get(PAGE_URL);
        PageFactory.initElements(driver, this);
        w = new WebDriverWait(driver,Duration.ofSeconds(20));
    }

    public void setLogin(String login){
        this.login.clear();
        this.login.sendKeys(login);
    }

    public void setPassword(String password){
        this.password.clear();
        this.password.sendKeys(password);
    }

    public void clickOnLogin(){
        this.signInBtn.click();
    }

    public void loginAs(String userName, String password) {
        this.login.sendKeys(userName);
        this.password.sendKeys(password);
        signInBtn.click();
    }

    public boolean isLoginError(){
        w.until(ExpectedConditions.visibilityOf(errorMessage));
        return errorMessage.getText().equals(errorText);
    }

    public boolean isPageOpened(){
        w.until(ExpectedConditions.visibilityOf(header));
        return header.getText().equals(expectedHeader);
    }
}