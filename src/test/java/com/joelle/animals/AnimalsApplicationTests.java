package com.joelle.animals;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

import org.junit.jupiter.api.parallel.ExecutionMode;


@Execution(ExecutionMode.CONCURRENT)  
@Import(TestcontainersConfiguration.class)
@SpringBootTest
class AnimalsApplicationTests {

    private WebDriver driver;
    private AnimalPage animalPage;
    private DogsPage dogsPage;

@BeforeEach
public void setUp() {
    WebDriverManager.chromedriver().setup();   
    WebDriverManager.firefoxdriver().setup();   
    WebDriverManager.edgedriver().setup();   
}
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    private void maximizeWindowAndWait() {
        driver.manage().window().maximize();  // Maximize the window
        try {
            Thread.sleep(5000);  // Add a sleep for 1 second before actions
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    // Method to read CSV data (like your `addBooksFromCSV` method)
    public String[] getNameFromCSV(String csvFilePath) throws CsvValidationException {
        try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
            reader.readNext(); // Skip header row
            String[] line = reader.readNext(); // Read the first row with the name
            return line; // Return the first name (assuming only one entry)
        } catch (IOException e) {
            e.printStackTrace();
            return new String[]{"Guest"}; // Return a default value if file reading fails
        }
    }

    @Test
    public void testWelcomePage() throws CsvValidationException {
        // Read name from CSV file
        String[] nameData = getNameFromCSV("src/main/resources/name.csv");
        String name = nameData[0]; // Assume the name is in the first column

        // Set up WebDriver (e.g., ChromeDriver)
        driver = new ChromeDriver();
        driver.get("file:///C:/Users/HP-G9/Desktop/laaast/lastchance/src/main/resources/index.html");
        maximizeWindowAndWait();
        // Enter the name and submit the form
        driver.findElement(By.id("name")).sendKeys(name);
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // Wait for the main page to load and check the greeting
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Correct usage in Selenium 4.x
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("userName"))); // Look for the userName span

        // Verify that the greeting is correct
        String greeting = driver.findElement(By.id("userName")).getText();
        // System.out.println(name +"   hellooo  "+ greeting);
        assertEquals(name , greeting.trim()); // Compare the actual and expected greeting
        // Close the browser
        driver.quit();
    }

    @Test
    public void testEnterNewName() {
        // New name to test
        String newName = "John Doe";

        // Set up WebDriver (e.g., ChromeDriver)
        driver = new ChromeDriver();
        driver.get("file:///C:/Users/HP-G9/Desktop/laaast/lastchance/src/main/resources/index.html");
        maximizeWindowAndWait();
        // Enter the new name into the form
        driver.findElement(By.id("name")).sendKeys(newName);
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // Wait for the page to load and check if the name is displayed
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("userName"))); // Look for the userName span

        // Get the displayed name
        String displayedName = driver.findElement(By.id("userName")).getText();

        // Assert that the displayed name matches the new name entered
        assertEquals(newName, displayedName.trim()); // Compare the actual and expected name

        // Close the browser
        driver.quit();
    }
    @Test
    public void FirefoxTest() {     
        // Initialize Firefox driver
        driver = new FirefoxDriver(); 
        runTest();
    }
 
    @Test
    public void ChromeTest() { 
        // Initialize Chrome driver
        driver = new ChromeDriver();
        runTest();
    }

    @Test
    public void EdgeDriverTest() {     
        // Initialize Edge driver
        driver = new EdgeDriver();
        runTest();
    }

    private void runTest() {
        driver.get("file:///C:/Users/HP-G9/Desktop/selenium project/animals/src/main/resources/index.html");
        maximizeWindowAndWait();
        // Initialize the AnimalPage object with the current WebDriver instance
        animalPage = new AnimalPage(driver);

        try {
            animalPage.clickDog();
            assertTrue(isAudioPlaying(), "Dog sound is not playing!");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Test failed due to an exception: " + e.getMessage());
        }
    }

    private boolean isAudioPlaying() {
        JavascriptExecutor js = (JavascriptExecutor) driver;  // Get WebDriver instance for current thread
        Boolean isPlaying = (Boolean) js.executeScript(
            "var audio = document.getElementById('animal-sound');" +
            "return audio && !audio.paused;");
        return isPlaying != null && isPlaying;
    }

    @Test
public void testClickDonkeyImageNavigatesToDonkeyPage() {
    // Set up WebDriver
    driver = new ChromeDriver();
    driver.get("file:///C:/Users/HP-G9/Desktop/laaast/lastchance/src/main/resources/index.html");
    maximizeWindowAndWait();
    // Instantiate the AnimalPage POM class
    AnimalPage animalPage = new AnimalPage(driver);

    // Click on the Donkey image
    animalPage.clickDonkey();

    // Wait for navigation to donkey page
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.urlContains("donkey.html"));

    // Assert that the URL is correct
    String currentUrl = driver.getCurrentUrl();
    assertTrue(currentUrl.contains("donkey.html"));

    driver.quit();
}
@Test
public void testBackButtonWorks() {
    // Set up WebDriver
    driver = new ChromeDriver();
    driver.get("file:///C:/Users/HP-G9/Desktop/laaast/lastchance/src/main/resources/donkey.html");
    maximizeWindowAndWait();
    // Instantiate the DonkeyPage POM class
    DonkeyPage donkeyPage = new DonkeyPage(driver);

    // Click the back button (this will navigate to the main page)
    donkeyPage.goBackToMainPage();

    // Wait for the main page to load
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.urlContains("index.html"));

    // Assert that we are on the main page
    String currentUrl = driver.getCurrentUrl();
    assertTrue(currentUrl.contains("index.html"));

    driver.quit();
}

    @Test
public void testClickCorrectPictureIncrementsCounter() {
    // Set up WebDriver
    driver = new ChromeDriver();
    driver.get("file:///C:/Users/HP-G9/Desktop/laaast/lastchance/src/main/resources/donkey.html");
    maximizeWindowAndWait();
    // Instantiate the DonkeyPage POM class
    DonkeyPage donkeyPage = new DonkeyPage(driver);

    // Click on the first donkey image
    donkeyPage.clickDonkey1();


    // Assert the counter has been updated
    String counter = donkeyPage.getCounterText();
    assertEquals("donkeys clicked: 1/2", counter);

    driver.quit();
}
@Test
public void testCounterResetsAfterTwoCorrectClicks() {
    // Set up WebDriver
    driver = new ChromeDriver();
    driver.get("file:///C:/Users/HP-G9/Desktop/laaast/lastchance/src/main/resources/donkey.html");
    maximizeWindowAndWait();
    // Instantiate the DonkeyPage POM class
    DonkeyPage donkeyPage = new DonkeyPage(driver);

    // Click on two correct donkey images
    donkeyPage.clickDonkey1();
    donkeyPage.clickDonkey2();

    // Wait for the counter to reset to the expected value
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    wait.until(ExpectedConditions.textToBe(
        By.id("counter"),
        "donkeys clicked: 0/2"
    ));

    // Assert the counter has been reset
    String counter = donkeyPage.getCounterText();
    assertEquals("donkeys clicked: 0/2", counter);

    driver.quit();
}



@Test
public void testClickWrongImageDoesNotIncrementCounter() {
    // Set up WebDriver
    driver = new ChromeDriver();
    driver.get("file:///C:/Users/HP-G9/Desktop/laaast/lastchance/src/main/resources/donkey.html");
    maximizeWindowAndWait();
    // Instantiate the DonkeyPage POM class
    DonkeyPage donkeyPage = new DonkeyPage(driver);

    // Click on a wrong image (e.g., Dog image)
    donkeyPage.clickWrongAnimal();

    // Wait for the counter to stay the same
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    wait.until(ExpectedConditions.textToBe(By.id("counter"), "Donkeys clicked: 0/2"));

    // Assert the counter hasn't changed
    String counter = donkeyPage.getCounterText();
    assertEquals("Donkeys clicked: 0/2", counter);

    driver.quit();
}

@Test
public void testDogsPageTitleAndDescriptions() {
    // Initialize WebDriver and navigate to the Dogs page
    driver = new ChromeDriver();
    driver.get("file:///C:/Users/HP-G9/Desktop/laaast/lastchance/src/main/resources/dog.html");
    maximizeWindowAndWait();

    // Instantiate DogsPage POM
    dogsPage = new DogsPage(driver);

    // Validate page title
    String pageTitle = dogsPage.getPageTitleText();
    assertEquals("About Dogs", pageTitle);

    // Validate page descriptions
    assertTrue(dogsPage.isDogDescriptionDisplayed(), "Dog descriptions are not displayed correctly.");

    driver.quit();
}

@Test
public void testDogImageClicksIncrementCounter() {
    // Initialize WebDriver and navigate to the Dogs page
    driver = new ChromeDriver();
    driver.get("file:///C:/Users/HP-G9/Desktop/laaast/lastchance/src/main/resources/dog.html");
    maximizeWindowAndWait();

    // Instantiate DogsPage POM
    dogsPage = new DogsPage(driver);

    // Click on dog images and verify counter updates
    dogsPage.clickDogImage1();
    assertEquals("dogs clicked: 1/2", dogsPage.getCounterText(), "Counter did not update to 1/2.");

    dogsPage.clickDogImage2();
    assertEquals("dogs clicked: 2/2", dogsPage.getCounterText(), "Counter did not update to 2/2.");

    driver.quit();
}

@Test
public void testDogCounterResetsAfterTwoClicks() {
    // Initialize WebDriver and navigate to the Dogs page
    driver = new ChromeDriver();
    driver.get("file:///C:/Users/HP-G9/Desktop/laaast/lastchance/src/main/resources/dog.html");
    maximizeWindowAndWait();

    // Instantiate DogsPage POM
    dogsPage = new DogsPage(driver);

    // Click both dog images to reach 2/2, then verify counter resets
    dogsPage.clickDogImage1();
    dogsPage.clickDogImage2();

    // Wait for counter to reset
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(9));
    wait.until(ExpectedConditions.textToBe(By.id("counter"), "dogs clicked: 0/2"));

    assertEquals("dogs clicked: 0/2", dogsPage.getCounterText(), "Counter did not reset after two clicks.");

    driver.quit();
}

@Test
public void testBackToMainPageLink() {
    // Initialize WebDriver and navigate to the Dogs page
    driver = new ChromeDriver();
    driver.get("file:///C:/Users/HP-G9/Desktop/laaast/lastchance/src/main/resources/dog.html");
    maximizeWindowAndWait();

    // Instantiate DogsPage POM
    dogsPage = new DogsPage(driver);

    // Click back link to navigate to the main page
    dogsPage.clickBackToMainPage();

    // Wait and verify that we are on the main page
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.urlContains("index.html"));

    String currentUrl = driver.getCurrentUrl();
    assertTrue(currentUrl.contains("index.html"), "Did not navigate back to the main page.");

    driver.quit();
}



}
