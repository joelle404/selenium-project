package com.joelle.animals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DonkeyPage {
    WebDriver driver;

    @FindBy(id = "donkey1")
    WebElement donkey1Button;

    @FindBy(id = "donkey2")
    WebElement donkey2Button;

    @FindBy(id = "dog")
    WebElement dogButton;

    @FindBy(id = "bird")
    WebElement birdButton;

    @FindBy(id = "capybara")
    WebElement capybaraButton;

    @FindBy(id = "counter")
    WebElement counter;

    @FindBy(linkText = "Back to Main Page")
    WebElement backToMainPageButton;

    public DonkeyPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickDonkey1() {
        donkey1Button.click();
    }

    public void clickDonkey2() {
        donkey2Button.click();
    }

    public void clickWrongAnimal() {
        birdButton.click(); // You can adapt this to click any wrong animal
    }

    public String getCounterText() {
        return counter.getText();
    }

    public void goBackToMainPage() {
        backToMainPageButton.click();
    }
}
