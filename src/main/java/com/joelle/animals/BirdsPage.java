package com.joelle.animals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BirdsPage {
    WebDriver driver;

    // Page elements
    @FindBy(tagName = "h1")
    WebElement pageTitle;

    @FindBy(xpath = "//p[contains(text(), 'Birds are warm-blooded creatures with feathers')]")
    WebElement description1;

    @FindBy(xpath = "//p[contains(text(), 'Birds communicate through a variety of sounds')]")
    WebElement description2;

    @FindBy(xpath = "//p[contains(text(), 'Birds play important roles in ecosystems')]")
    WebElement description3;

    @FindBy(linkText = "Back to Main Page")
    WebElement backToMainPageLink;

    // Constructor to initialize elements
    public BirdsPage(WebDriver driver) {
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

    public void clickBackToMainPage() {
        backToMainPageLink.click();
    }

    // Validation methods
    public boolean isBirdDescriptionDisplayed() {
        return description1.isDisplayed() && description2.isDisplayed() && description3.isDisplayed();
    }
}
