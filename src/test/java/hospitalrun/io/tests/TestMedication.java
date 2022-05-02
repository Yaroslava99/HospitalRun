package hospitalrun.io.tests;

import hospitalrun.io.helperclasses.AbstractTest;
import hospitalrun.io.webpages.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TestMedication extends AbstractTest {
    String login = "hr.doctor@hospitalrun.io";
    String password = "HRt3st12";

    @DisplayName("IV. Request a new medication")
    @ParameterizedTest(name = "{index} => Patient name={0}, Medication={1}")
    @CsvSource({
            "Test Patient, Pramoxine, Testing prescription"
    })
    public void requestANewMedication(String patientName, String medication, String prescription) {
        LogInPage loginPage = new LogInPage(driver);
        Assertions.assertTrue(loginPage.isPageOpened());
        loginPage.loginAs(login, password);
        PatientsPage patientsPage= new PatientsPage(driver);
        Assertions.assertTrue(patientsPage.isPageOpened());
        MenuPageFragment menu = new MenuPageFragment(driver);
        menu.openNewRequestPage();
        NewMedicationRequestPage requestPage = new NewMedicationRequestPage(driver);
        Assertions.assertTrue(requestPage.isPageOpened());
        requestPage.fillTheForm(patientName, medication, prescription);
        requestPage.checkMedicationRequestSavedPopup();
        requestPage.getOkBtn().click();
    }

    @Test
    @DisplayName("Assert that Medication Section contains 4 items")
    public void checkMedicationMenu(){
        LogInPage loginPage = new LogInPage(driver);
        Assertions.assertTrue(loginPage.isPageOpened());
        loginPage.loginAs(login, password);
        PatientsPage patientsPage= new PatientsPage(driver);
        Assertions.assertTrue(patientsPage.isPageOpened());
        MenuPageFragment menu = new MenuPageFragment(driver);
        menu.openNewRequestPage();
        Assertions.assertTrue(menu.checkMedicationSection());
    }
}
