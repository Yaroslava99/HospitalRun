package hospitalrun.io.tests;

import hospitalrun.io.webpages.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class TestMedication extends AbstractTest{
    WebDriver driver;

    @BeforeEach
    public void setup(){
        String browserName = super.getBrowserParameter();
        driver = setBrowser(browserName);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @DisplayName("IV. Request a new medication")
    @Order(1)
    @ParameterizedTest(name = "{index} => Patient name={0}, Medication={1}")
    @CsvSource({
            "Test Patient - P00201, Pramoxine, Testing prescription"
    })
    public void RequestANewMedication(String patientName, String medication, String prescription) {
        LogInPage loginPage = new LogInPage(driver);
        Assertions.assertTrue(loginPage.isPageOpened());
        loginPage.loginAs("hr.doctor@hospitalrun.io", "HRt3st12");
        PatientsPage patientsPage= new PatientsPage(driver);
        Assertions.assertTrue(patientsPage.isPageOpened());
        MenuPageFragment menu = new MenuPageFragment(driver);
        menu.openNewRequestPage();
        NewMedicationRequestPage requestPage = new NewMedicationRequestPage(driver);
        Assertions.assertTrue(requestPage.isPageOpened());
        requestPage.fillTheForm(patientName, medication, prescription);
        requestPage.CheckMedicationRequestSavedPopup();
    }

    @Test
    @DisplayName("Assert that Medication Section contains 4 items")
    @Order(2)
    public void CheckMedicationMenu(){
        LogInPage loginPage = new LogInPage(driver);
        Assertions.assertTrue(loginPage.isPageOpened());
        loginPage.loginAs("hr.doctor@hospitalrun.io", "HRt3st12");
        PatientsPage patientsPage= new PatientsPage(driver);
        Assertions.assertTrue(patientsPage.isPageOpened());
        MenuPageFragment menu = new MenuPageFragment(driver);
        menu.openNewRequestPage();
        Assertions.assertTrue(menu.CheckMedicationSection());
    }

    @AfterEach
    public void close(){
        //driver.close();
    }
}
