package com.joelle.animals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DogsPage {
    WebDriver driver;

    // Page elements
    @FindBy(tagName = "h1")
    WebElement pageTitle;

    @FindBy(xpath = "//p[contains(text(), 'Dogs are loyal and social animals')]")
    WebElement description1;

    @FindBy(xpath = "//p[contains(text(), 'Dogs communicate using a variety of sounds')]")
    WebElement description2;

    @FindBy(xpath = "//p[contains(text(), 'As social animals, dogs thrive in environments')]")
    WebElement description3;

    @FindBy(id = "dog1")
    WebElement dogImage1;

    @FindBy(id = "dog2")
    WebElement dogImage2;

    @FindBy(id = "counter")
    WebElement counterText;

    @FindBy(linkText = "Back to Main Page")
    WebElement backToMainPageLink;

    // Constructor to initialize elements
    public DogsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Methods to interact with page elements
    public String getPageTitleText() {
        return pageTitle.getText();
    }

    public String getDescriptionText1() {
        return description1.getText();
    }

    public String getDescriptionText2() {
        return description2.getText();
    }

    public String getDescriptionText3() {
        return description3.getText();
    }

    public void clickDogImage1() {
        dogImage1.click();
    }

    public void clickDogImage2() {
        dogImage2.click();
    }

    public String getCounterText() {
        return counterText.getText();
    }

    public void clickBackToMainPage() {
        backToMainPageLink.click();
    }

    // Validation methods
    public boolean isDogDescriptionDisplayed() {
        return description1.isDisplayed() && description2.isDisplayed() && description3.isDisplayed();
    }

    public boolean isCounterUpdated(String expectedCounter) {
        return counterText.getText().equals(expectedCounter);
    }
}

