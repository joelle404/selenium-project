package com.joelle.animals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CatsPage {
    WebDriver driver;

    // Page elements
    @FindBy(tagName = "h1")
    WebElement pageTitle;

    @FindBy(xpath = "//p[contains(text(), 'Cats are small, carnivorous mammals')]")
    WebElement description1;

    @FindBy(xpath = "//p[contains(text(), 'Cats have been associated with humans for thousands of years')]")
    WebElement description2;

    @FindBy(xpath = "//p[contains(text(), 'Cats communicate with humans and other animals')]")
    WebElement description3;

    @FindBy(id = "cat1")
    WebElement catImage1;

    @FindBy(id = "cat2")
    WebElement catImage2;

    @FindBy(id = "counter")
    WebElement counterText;

    @FindBy(linkText = "Back to Main Page")
    WebElement backToMainPageLink;

    // Constructor to initialize elements
    public CatsPage(WebDriver driver) {
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

    public void clickCatImage1() {
        catImage1.click();
    }

    public void clickCatImage2() {
        catImage2.click();
    }

    public String getCounterText() {
        return counterText.getText();
    }

    public void clickBackToMainPage() {
        backToMainPageLink.click();
    }

    // Validation methods
    public boolean isCatDescriptionDisplayed() {
        return description1.isDisplayed() && description2.isDisplayed() && description3.isDisplayed();
    }

    public boolean isCounterUpdated(String expectedCounter) {
        return counterText.getText().equals(expectedCounter);
    }
}
