# Regex Text Processing Tool

## Overview
The Regex Text Processing Tool is a JavaFX application designed for matching, finding, and replacing text using regular expressions. It also includes a history feature for managing past pattern-text pairs.

## Features
1. **Match Regex:**
   - Checks if the entire text matches the regex pattern.
   - Displays the match result.

2. **Find Regex:**
   - Searches for all occurrences of the pattern within the text.
   - Displays all matches along with their start and end positions.

3. **Replace Regex:**
   - Replaces parts of the text that match the given pattern with a replacement string.
   - Displays the modified text.

4. **Add to History:**
   - Saves the regex pattern and corresponding text for future reference.

5. **Show History:**
   - Displays a list of all saved pattern-text pairs.
   - Allows deletion of individual pairs.

## Installation
1. **Clone the Repository:**
   ```bash
   git clonehttps://github.com/sayv-xo/amalitech_gtp.git
   ```
2. **Navigate to the Project Directory:**
   ```bash
   cd regex-text-processing-tool
   ```
3. **Build the Project:**
   Use your preferred Java build tool (e.g., Maven, Gradle) to build the project.
   ```bash
   mvn clean install
   ```
4. **Run the Application:**
   ```bash
   java -jar target/regex-text-processing-tool-1.0.jar
   ```

## Usage
1. **Launching the Application:**
   - Run the application using the above command.

2. **Match Regex:**
   - Enter the regex pattern in the "Regex Input" field.
   - Enter the text in the "Text Input" field.
   - Click the "Match Regex" button.
   - The result will display if the pattern fully matches the text.

3. **Find Regex:**
   - Enter the regex pattern in the "Regex Input" field.
   - Enter the text in the "Text Input" field.
   - Click the "Find Regex" button.
   - The result will display all matches with their start and end positions.

4. **Replace Regex:**
   - Enter the regex pattern in the "Regex Input" field.
   - Enter the replacement text in the "Replace Input" field.
   - Enter the text in the "Text Input" field.
   - Click the "Replace Regex" button.
   - The result will display the modified text.

5. **Add to History:**
   - After entering the regex pattern and text, click the "Add to History" button to save the pair.

6. **Show History:**
   - Click the "Show History" button to view saved pattern-text pairs in a new window.
   - Select a pair and click the "Delete Selected" button to remove it.

## Development
### Prerequisites
- Java 8 or higher
- JavaFX
- Maven or Gradle


## Contact
- **Author:** sayv ‎يزن
- **GitHub:** [sayv-xo](https://github.com/sayv-xo)

## Live Demo
A video to the live demonstration of the app can be found [here](https://www.loom.com/share/44ba322eeaa745b1b8bbca9c9f579c29?sid=7c86cd8d-558b-49bb-8154-33b486283bf9)

---

This README provides an overview of the Regex Text Processing Tool, instructions for installation, usage, development, and contribution guidelines.
