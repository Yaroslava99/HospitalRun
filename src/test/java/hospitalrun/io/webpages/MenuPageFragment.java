package hospitalrun.io.webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Arrays;
import java.util.List;

public class MenuPageFragment {
    protected WebDriver driver;
    List<String> items = Arrays.asList("Requests","Completed", "New Request", "Return Medication");

    @FindBy(linkText = "Medication")
    private WebElement medicationTab;

    @FindBy(linkText = "New Request")
    private WebElement newRequestTab;

    @FindBy(css = "#ember765 .category-sub-items a")
    private List<WebElement> options;

    public MenuPageFragment(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    public void openNewRequestPage(){
        this.medicationTab.click();
        this.newRequestTab.click();
    }

    public void clickMedicationTab(){
        this.medicationTab.click();
    }

    public void clickNewRequestTab(){
        this.newRequestTab.click();
    }

    public boolean checkMedicationSection(){
        if(options.size()!=4)
            return false;
        for (WebElement option : options) {
            if(!this.items.contains(option.getText())) {
                return false;
            }
        }
        return true;
    }
}
