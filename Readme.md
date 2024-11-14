 Animals Application Test Automation Documentation

 Project Overview

This project automates testing for a web-based application, Animals Application, using Selenium WebDriver with JUnit. The application includes pages that allow users to interact with animal sounds and images, specifically for dogs and donkeys. The objective is to verify that various UI elements (buttons, forms, audio playback, etc.) function as expected across multiple browsers (Chrome, Firefox, Edge).

 Technologies Used

- Java - Main programming language
- JUnit 5 - For managing test cases
- Selenium WebDriver - For browser automation
- OpenCSV - For CSV file handling
- Maven - For dependency management
- Bonigarcia WebDriverManager - For managing WebDriver binaries

 Project Structure

 _ Package: `com.joelle.animals`

- AnimalsApplicationTests: Main test class containing all test cases.
- AnimalPage and DonkeyPage: Page Object Model (POM) classes representing each page in the application.

 _ Configuration

- TestcontainersConfiguration: Manages configurations for running tests in a containerized environment. This is helpful when running tests in parallel for improved performance.


 Class Documentation

 1. AnimalsApplicationTests

The primary test class where all test cases for the Animals Application are defined and executed.

 _ Attributes
- `WebDriver driver`: The WebDriver instance used for browser interactions.
- `AnimalPage animalPage`: Represents the page with animal-related actions.

 _ Annotations Used
- `@Execution(ExecutionMode.CONCURRENT)`: Enables parallel execution of test cases for improved efficiency.
- `@BeforeEach` and `@AfterEach`: Used for setting up and tearing down the browser before and after each test case.

 _ Key Methods

- setUp()
  - Configures the WebDriver for Chrome, Firefox, and Edge using `WebDriverManager` to simplify setup.
  
- tearDown()
  - Closes the WebDriver after each test to free up resources.
  
- maximizeWindowAndWait()
  - Maximizes the browser window and introduces a short delay for better visualization of the test in action.
  
- waitForElement(By locator)
  - Uses `WebDriverWait` to wait until a specific element is present in the DOM, ensuring that elements are ready before interaction.
  
- getNameFromCSV(String csvFilePath)
  - Reads a name from a CSV file for testing form inputs based on external data.

 _ Test Cases

- testWelcomePage()
  - Tests that the correct greeting is displayed on the welcome page based on input from a CSV file.
  
- testEnterNewName()
  - Verifies that a newly entered name displays correctly on the welcome page.
  
- FirefoxTest, ChromeTest, EdgeDriverTest
  - Browser-specific tests to verify cross-browser compatibility of the `runTest()` method.
  
- testClickDonkeyImageNavigatesToDonkeyPage()
  - Ensures that clicking the Donkey image navigates correctly to the Donkey page.
  
- testBackButtonWorks()
  - Verifies the functionality of the back button from the Donkey page back to the main page.
  
- testClickCorrectPictureIncrementsCounter()
  - Checks that clicking the correct picture increments the counter.
  
- testCounterResetsAfterTwoCorrectClicks()
  - Confirms that the counter resets after two correct clicks on the donkey images.

 _ Helper Method

- isAudioPlaying()
  - Executes JavaScript to check if the audio element is playing, which is used to verify sound functionality.

 2. AnimalPage (Page Object Model)


A class representing the main Animal page of the application.

 _ Attributes
- `WebDriver driver`: The WebDriver instance used to interact with page elements.

 _ Methods
- clickDog(): Clicks the element associated with the dog sound to trigger playback.
- clickDonkey(): Clicks the element to navigate to the Donkey page.

 3. DonkeyPage (Page Object Model)

A class representing the Donkey-specific page of the application.

 Methods
- clickDonkey1() / clickDonkey2(): Clicks either the first or second donkey image to increment a counter.
- goBackToMainPage(): Clicks the "Back" button to return to the main page.
- getCounterText(): Retrieves the text of the counter to verify correct increments after interactions.


 Setup and Execution

 Prerequisites

1. Java Development Kit (JDK): Ensure JDK 8 or later is installed.
2. Maven: Used for dependency management.
3. Supported Browsers: Chrome, Firefox, and Edge browsers must be installed on the testing machine.
4. Selenium WebDriver Binaries: Managed automatically using `WebDriverManager`.

 Installing Dependencies

Use Maven to install project dependencies by running:
```bash
mvn clean install


    you can run the project useing this command, 
    mvn test
    OR by click on run button in the project

    CSV File Setup:
    - Create a CSV file named "name.csv" in the root directory of the project.
    inside the file put:

    names:
    shouq
    mays
    joelle
    rayan
    jone



This complete `README.md` should cover everything from setup to troubleshooting, making it an effective reference for anyone working on or reviewing your project. Let me know if you’d like any further customization!