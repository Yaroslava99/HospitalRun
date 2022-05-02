package hospitalrun.io.tests;

import hospitalrun.io.helperclasses.AbstractTest;
import hospitalrun.io.webpages.LogInPage;
import hospitalrun.io.webpages.PatientsPage;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TestLogin extends AbstractTest {

    @DisplayName("I. User can log in with correct credential")
    @Story("User tries to login the system with valid username and valid password.")
    @Description("Valid Login Test with Valid Username and Valid Password.")
    @ParameterizedTest(name = "{index} => login={0}, password={1}")
    @CsvSource({
            "hr.doctor@hospitalrun.io, HRt3st12"
    })
    public void logInWithCorrectCredentials(String login, String password) {
        //Create object of LogInPage Class
        LogInPage loginPage = new LogInPage(driver);
        //Check if LogIn page is opened
        Assertions.assertTrue(loginPage.isPageOpened());
        //Fill in the form with valid credentials
        loginPage.loginAs(login,password);
        //Create object of PatientsPage
        PatientsPage patientsPage = new PatientsPage(driver);
        //Check if log in is successful
        Assertions.assertTrue(patientsPage.isPageOpened());
    }


    @DisplayName("II. User can`t login with invalid credentials")
    @ParameterizedTest(name = "{index} => login={0}, password={1}")
    @CsvSource({
            "dev@hospitalrun.io, y83Lo0cNMzxc",
            "hr.doctor@hospitalrun.io, hdD424fdsf3rf"
    })
    public void logInWithInvalidCredentials(String login, String password) {
        //Create object of LogInPage Class
        LogInPage loginPage = new LogInPage(driver);
        //Check if LogIn page is opened
        Assertions.assertTrue(loginPage.isPageOpened());
        //Fill in the form with valid credentials
        loginPage.loginAs(login,password);
        //Assert that User is stayed on Login Page and Error message is displayed.
        Assertions.assertTrue(loginPage.isLoginError());
        Assertions.assertTrue(loginPage.isPageOpened());
    }

    @DisplayName("III. User is able to logout")
    @ParameterizedTest(name = "{index} => login={0}, password={1}")
    @CsvSource({
            "hr.doctor@hospitalrun.io, HRt3st12"
    })
    public void logOut(String login, String password) {
        //Create object of LogInPage Class
        LogInPage loginPage = new LogInPage(driver);
        //Check if LogIn page is opened
        Assertions.assertTrue(loginPage.isPageOpened());
        //Fill in the form with valid credentials
        loginPage.loginAs(login,password);
        //Create object of PatientsPage
        PatientsPage patientsPage = new PatientsPage(driver);
        //Check if log in is successful
        Assertions.assertTrue(patientsPage.isPageOpened());
        //Click to Logout link
        patientsPage.logout();
        //Assert that User is logged out and Login Page is displayed
        Assertions.assertTrue(loginPage.isPageOpened());
    }

}
