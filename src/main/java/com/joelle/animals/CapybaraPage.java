package com.joelle.animals;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CapybaraPage {
    WebDriver driver;

    // Page elements
    @FindBy(id = "capybara1")
    WebElement capybaraImage1;

    @FindBy(xpath = "//img[@alt='Donkey 2' and @id='capybara1']")
    WebElement capybaraImage2;

    @FindBy(xpath = "//img[@alt='Dog']")
    WebElement dogImage;

    @FindBy(xpath = "//img[@alt='Cat']")
    WebElement donkeyImage;

    @FindBy(xpath = "//img[@alt='Bird']")
    WebElement birdImage;

    @FindBy(xpath = "//img[@alt='Capybara']")
    WebElement catImage;

    @FindBy(id = "counter")
    WebElement counterText;

    @FindBy(id = "clap-sound")
    WebElement clapSoundAudio;

    @FindBy(id = "animal-sound")
    WebElement animalSoundAudio;

    @FindBy(linkText = "Back to Main Page")
    WebElement backToMainPageLink;

    // Constructor to initialize elements
    public CapybaraPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Methods to interact with page elements
    public void clickCapybaraImage1() {
        capybaraImage1.click();
    }

    public void clickCapybaraImage2() {
        capybaraImage2.click();
    }

    public void clickDogImage() {
        dogImage.click();
    }

    public void clickDonkeyImage() {
        donkeyImage.click();
    }

    public void clickBirdImage() {
        birdImage.click();
    }

    public void clickCatImage() {
        catImage.click();
    }

    public String getCounterText() {
        return counterText.getText();
    }


    public void clickBackToMainPage() {
        backToMainPageLink.click();
    }
    

}
