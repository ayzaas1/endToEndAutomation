package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends BasePage{

    public MainPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[text()='Sign up' and contains(@class, 'contained')]")
    public WebElement signUpButton;

    @FindBy(id = "email_input_text")
    public WebElement email;

    @FindBy(id = "password_input_text")
    public WebElement password;

    @FindBy(id = "repeat_password_input_text")
    public WebElement confirmPassword;

    @FindBy(xpath = "//button[@form='register-form-1']")
    public WebElement continueBtn;

    @FindBy(id = "first_name_input_text")
    public WebElement firstName;

    @FindBy(id = "last_name_input_text")
    public WebElement lastName;

    @FindBy(id = "company_name_input_text")
    public WebElement nameOfBusiness;

    @FindBy(id = "mui-component-select-business_area_id")
    public WebElement areaOfBusinessDropdown;

    @FindBy(xpath = "//li[text()='Financial Services']")
    //or //li[.='Financial Services']
    public WebElement areaOfBusinessOption;

    @FindBy(id = "address_input_text")
    public WebElement address;

    @FindBy(id = "mui-component-select-currency")
    public WebElement currencyDropdown;

    @FindBy(xpath = "//li[@data-value='USD']")
    public WebElement currencyOption;

    @FindBy(xpath = "//button[@form='register-form-2']")
    public WebElement signUpBtn2;

    public void signUp(){
        signUpButton.click();
        email.sendKeys(faker.internet().emailAddress());
        password.sendKeys("abc123");
        confirmPassword.sendKeys("abc123");
        continueBtn.click();

        waitVisible(firstName);
        firstName.sendKeys(faker.name().firstName());
        lastName.sendKeys(faker.name().lastName());
        nameOfBusiness.sendKeys(faker.company().name());

        areaOfBusinessDropdown.click();
        waitVisible(areaOfBusinessOption);
        areaOfBusinessOption.click();

        address.sendKeys(faker.address().fullAddress());

        currencyDropdown.click();
        waitVisible(currencyOption);
        currencyOption.click();

        signUpBtn2.click();
    }

    @Override
    public String name() {
        return "Main Page";
    }
}
