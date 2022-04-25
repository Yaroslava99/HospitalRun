package hospitalrun.io.webpages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import java.time.Duration;

public class LogInPage {
    protected WebDriver driver;

    private static final String PAGE_URL="http://demo.hospitalrun.io/";

    @FindBy(tagName = "h2")
    private WebElement header;

    @FindBy(id = "identification")
    private WebElement login;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(css = "#ember433 > div > form > div.signin-contents > button")
    private WebElement signInBtn;

    @FindBy(className = "alert")
    private WebElement errorMessage;

    public LogInPage(WebDriver driver){
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(PAGE_URL);
        PageFactory.initElements(driver, this);
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
        return errorMessage.getText().equals("Username or password is incorrect.");
    }

    public boolean isPageOpened(){
        return header.getText().equals("PLEASE SIGN IN");
    }
}