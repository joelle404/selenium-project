// Object to store clicked status and counters for each animal
const animalData = {
  donkey: { clickedIds: {}, counter: 0, maxCount: 2 },
  cat: { clickedIds: {}, counter: 0, maxCount: 2 },
  capybara: { clickedIds: {}, counter: 0, maxCount: 2 },
  dog: { clickedIds: {}, counter: 0, maxCount: 2 }
};

// Variable to track if the clap sound has been played
let clapSoundPlayed = false;

// Function to increment the counter for a specific animal
function incrementCounter(animalId) {
  // Determine the animal type from the ID (e.g., "donkey1" -> "donkey")
  const animalType = animalId.replace(/\d+$/, ''); // Remove digits at the end to get the type
  playSoundAnd(animalType);
  
  // Get the data for the specified animal type
  const data = animalData[animalType];

  // Only increment if the specific animal hasn't been clicked and counter is below max
  if (!data.clickedIds[animalId] && data.counter < data.maxCount) {
    // Mark the animal ID as clicked
    data.clickedIds[animalId] = true;

    // Increment the counter for this animal
    data.counter++;

    // Update the counter display
    document.getElementById("counter").textContent = `${animalType}s clicked: ${data.counter}/${data.maxCount}`;

    // Check if max count is reached, play clap sound and reset counter
    if (data.counter === data.maxCount && !clapSoundPlayed) {
      clapSoundPlayed = true;
      playSoundAnd('clap');

      // Delay the counter reset so the user sees it before it resets
      setTimeout(() => {
        resetCounter(animalType);
      }, 3000); // 3-second delay before resetting
    }
  }
}

// Function to reset the counter and clicked IDs for a specific animal type
function resetCounter(animalType) {
  const data = animalData[animalType];
  data.clickedIds = {};  // Clear the clicked IDs for the animal
  data.counter = 0;      // Reset the counter to zero

  // Reset the clap sound flag for the next cycle
  clapSoundPlayed = false;

  // Update the counter display
  document.getElementById("counter").textContent = `${animalType}s clicked: 0/${data.maxCount}`;
}

// Function to play a sound based on the specified animal type or event
function playSoundAnd(animal) {
  const audio = document.getElementById("animal-sound");
  audio.src = `sounds/${animal}-sound.mp3`; // Dynamically set the audio source
  audio.play();
}

// Function to play sound and navigate to a specific animal's page
function playSoundAndNavigate(animal) {
  const audio = document.getElementById("animal-sound");
  audio.src = `sounds/${animal}-sound.mp3`; // Set audio source based on the animal clicked
  audio.play();

  // Wait for a brief moment before navigating to the page
  setTimeout(() => {
    window.location.href = `${animal}.html`;  // Navigate to the animal's page
  }, 2000); // 2-second delay to allow the sound to play
}

// Event listeners for each animal image (assuming each image has an ID like "donkey1", "cat2", etc.)
document.querySelectorAll(".animal-image").forEach(image => {
  image.addEventListener("click", () => {
    incrementCounter(image.id);
  });
});
