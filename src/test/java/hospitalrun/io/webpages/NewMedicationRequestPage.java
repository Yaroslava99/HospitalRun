package hospitalrun.io.webpages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

public class NewMedicationRequestPage {
    protected WebDriver driver;
    WebDriverWait w;
    String testPatientxPath = "//div[@class=\"tt-dataset tt-dataset-0\"]//div[32]";
    String patientNameDropDown = "div.tt-dataset-0 > div";
    String visitDropDown = "//*[contains(@id, 'visit-ember')]//option";
    String medicationDropDown = "div.tt-dataset-1 > div";
    String dateFormat = "M/d/yyyy";
    String pageHeader = "New Medication Request";
    String savedPopupTitle = "Medication Request Saved";

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

    @FindBy(className = "modal-title")
    private WebElement modalTitle;

    @FindBy(xpath = "//div[@class='modal-content']//*[contains(@class, 'btn')]")
    private WebElement okButton;

    @FindBy(css = "button.close")
    private WebElement xButton;

    public NewMedicationRequestPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver, this);
        w = new WebDriverWait(driver,Duration.ofSeconds(10));
        w.until(ExpectedConditions.visibilityOf(header));
    }

    protected void fillPatientName(String patientName){
        this.patientName.click();
        this.patientName.sendKeys(patientName);
        waitDropDown(this.patientName, patientNameDropDown);
        WebElement optionToSelect = driver.findElement(By.xpath(testPatientxPath));
        optionToSelect.click();
    }

    public WebElement getOkBtn(){
        return okButton;
    }

    protected void fillVisit(){
        this.visit.click();
        this.visit.sendKeys(Keys.ARROW_DOWN);
        this.visit.sendKeys(Keys.ENTER);
    }

    protected void fillMedication(String medication){
        this.medication.click();
        this.medication.sendKeys(medication);
        waitDropDown(this.medication, medicationDropDown);
        this.medication.sendKeys(Keys.TAB);
    }

    protected void fillPrescriptionDate(){
        LocalDate dateBefore = LocalDate.now().minusDays(1);
        this.prescriptionDate.clear();
        this.prescriptionDate.sendKeys(dateBefore.format(DateTimeFormatter.ofPattern(dateFormat)));
        this.prescriptionDate.sendKeys(Keys.TAB);
    }

    public void fillTheForm(String patientName, String medication, String prescription) {
        fillPatientName(patientName);
        w.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(visitDropDown), 1));
        fillVisit();
        fillMedication(medication);
        this.prescription.sendKeys(prescription);
        fillPrescriptionDate();
        quantity.sendKeys(String.valueOf(generateRandInt(1, 5)));
        refills.sendKeys(String.valueOf(generateRandInt(5, 10)));
        addButton.click();
    }

    public int generateRandInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public boolean isPageOpened(){
        return header.getText().equals(pageHeader);
    }

    public void checkMedicationRequestSavedPopup(){
        w.until(ExpectedConditions.visibilityOf(modalTitle));
        Assertions.assertEquals(savedPopupTitle, modalTitle.getText());
        Assertions.assertEquals("Ok", okButton.getText());
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
