package com.joelle.animals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AnimalPage {
    WebDriver driver;

    @FindBy(id = "cat")
    WebElement catButton;

    @FindBy(id = "dog")
    WebElement dogButton;

    @FindBy(id = "bird")
    WebElement birdButton;

    @FindBy(id = "donkey")
    WebElement donkeyButton;

    @FindBy(id = "capybara")
    WebElement capybaraButton;

    @FindBy(id = "animal-sound")
    WebElement animalSound;

    public AnimalPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickCat() {
        catButton.click();
    }

    public void clickDog() {
        dogButton.click();
    }

    public void clickDonkey() {
        donkeyButton.click();
    }

    public void clickCapybara() {
        capybaraButton.click();
    }

    public String getAnimalSound() {
        return animalSound.getText(); // Assuming the sound text or sound file name is displayed
    }
}
