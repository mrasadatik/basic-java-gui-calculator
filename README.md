# GUI Calculator

GUI Calculator is a graphical user interface (GUI) calculator application built using Java and JavaFX. This project serves as a basic demonstration of a functional calculator with an intuitive interface and custom styling.

## Table of Contents
- [Requirements](#requirements)
- [Installation](#installation)
- [Usage](#usage)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Contributing](#contributing)
- [License](#license)
- [Acknowledgements](#acknowledgements)

## Requirements
- Java Development Kit (JDK) 11 or higher
- Apache Maven 3.6.0 or higher

## Installation

### Clone the Repository
```bash
git clone https://github.com/mrasadatik/basic-java-gui-calculator.git
cd basic-java-gui-calculator
```

### Build the Project
```bash
mvn clean install
```

### Run the Application
```bash
mvn clean javafx:run
```

## Usage
Once the application is running, you will see a calculator interface with the following functionalities:
- Basic arithmetic operations (addition, subtraction, multiplication, division)
- Decimal point for fractional numbers
- Square root calculation
- Delete the last entered character
- Clear the display
- Turn off the calculator

## Features
- **Custom Styling:** The calculator uses a custom font and theme for an enhanced user experience.
- **Responsive Layout:** Buttons and display are arranged in a grid layout that adapts to different screen sizes.
- **Basic Arithmetic Operations:** Perform addition, subtraction, multiplication, and division.
- **Additional Functions:** Square root calculation and delete functionality.
- **Exit Confirmation:** Double confirmation to turn off the calculator.

## Technologies Used
- **Java 11:** The core programming language.
- **JavaFX:** For building the GUI.
- **Atlantafx Base:** A modern theme for JavaFX applications.
- **Maven:** For project management and build automation.

## Project Structure
```plaintext
|   .classpath
|   .gitignore
|   .project
|   LICENSE
|   pom.xml
|   README.md
|   structure.txt
|   
+---.settings
|       org.eclipse.core.resources.prefs
|       org.eclipse.jdt.core.prefs
|       org.eclipse.m2e.core.prefs
|       
\---src
    \---main
        +---java
        |   |   module-info.java
        |   |   
        |   \---com
        |       \---zynotic
        |           \---studios
        |               \---noor
        |                   \---calculator
        |                           App.java
        |                           
        \---resources
            \---assets
                +---fonts
                |       seven_segment.ttf
                |       
                \---images
                    \---icons
                            icon.png
```

## Contributing
Contributions are welcome! If you have suggestions for improvements, please follow these steps:
1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Make your changes.
4. Commit your changes (`git commit -m 'Add new feature'`).
5. Push to the branch (`git push origin feature-branch`).
6. Create a Pull Request.

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.

## Acknowledgements
- Special thanks to the developers of JavaFX and the Atlantafx Base theme for their great tools and libraries.

---

Thank you for using GUI Calculator! If you have any questions or feedback, feel free to reach out or open an issue on GitHub.
