package hospitalrun.io.webpages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class NewMedicationRequestPage {
    protected WebDriver driver;

    @FindBy(tagName = "h1")
    private WebElement header;

    @FindBy(css = "[id^=patientTypeAhead-ember]")
    private WebElement patientName;

    @FindBy(css = "[id^=visit-ember]")
    private WebElement visit;

    @FindBy(css = "[id^=inventoryItemTypeAhead-ember]")
    private WebElement medication;

    @FindBy(css = "[id^=prescription-ember]")
    private WebElement prescription;

    @FindBy(css = "[id^=display_prescriptionDate-ember]")
    private WebElement prescriptionDate;

    @FindBy(css = "[id^=quantity-ember]")
    private WebElement quantity;

    @FindBy(css = "[id^=refills-ember]")
    private WebElement refills;

    @FindBy(css = ".btn.btn-primary.on-white")
    private WebElement addButton;

    public NewMedicationRequestPage(WebDriver driver){
        this.driver=driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        PageFactory.initElements(driver, this);
    }

    public void fillTheForm(String patientName, String medication, String prescription) {
        SimpleDateFormat formatter= new SimpleDateFormat("M/d/yyyy");
        Instant before = Instant.now().minus(Duration.ofDays(1));
        Date dateBefore = Date.from(before);
        this.patientName.click();
        this.patientName.sendKeys("Test Patient");
        waitDropDown(this.patientName, "div.tt-dataset-0 > div");
        WebElement optionToSelect = driver.findElement(By.xpath("//*[@id=\"ember2570\"]/span/div/div/div[32]"));
        optionToSelect.click();
        WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(10));
        w.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("#visit-ember2579 option"), 1));
        this.visit.click();
        this.visit.sendKeys(Keys.ARROW_DOWN);
        this.visit.sendKeys(Keys.ENTER);
        this.medication.click();
        this.medication.sendKeys(medication);
        waitDropDown(this.medication, "div.tt-dataset-1 > div");
        this.medication.sendKeys(Keys.TAB);
        this.prescription.sendKeys(prescription);
        this.prescriptionDate.clear();
        this.prescriptionDate.sendKeys(formatter.format(dateBefore));
        this.medication.sendKeys(Keys.ENTER);
        this.quantity.sendKeys(String.valueOf(generateRandInt(1, 5)));
        this.refills.sendKeys(String.valueOf(generateRandInt(5, 10)));
        addButton.click();
    }

    public int generateRandInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public boolean isPageOpened(){
        return header.getText().equals("New Medication Request");
    }

    public void CheckMedicationRequestSavedPopup(){
        WebElement modalTitle = driver.findElement(By.className("modal-title"));
        Assertions.assertEquals("Medication Request Saved", modalTitle.getText());
        WebElement okButton = driver.findElement(By.cssSelector(" #ember3305 > div > div > div > div.modal-footer > button"));
        Assertions.assertEquals("OK", okButton.getText());
        Assertions.assertEquals("Medication Request Saved", modalTitle.getText());
        WebElement xButton = driver.findElement(By.cssSelector("button.close"));
        Assertions.assertTrue(xButton.isDisplayed());
    }

    private void waitDropDown(WebElement element, String selector){
        List<WebElement> optionsToSelect;
        do {
            element.sendKeys(" ");
            element.sendKeys(Keys.BACK_SPACE);
            optionsToSelect = this.driver.findElements(By.cssSelector(selector));
        }while(optionsToSelect.size()==0);
    }
}
