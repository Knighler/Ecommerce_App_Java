# GUIproject - JavaFX Application

## Overview

**GUIproject** is a JavaFX-based graphical user interface (GUI) application designed to simulate an e-commerce platform, "Better Call Mall." It supports multiple user roles, including Admin and Customer, and provides functionalities such as user management, category management, product management, and search capabilities.

## Features

### Admin Functionalities:
- **Dashboard:** Overview of platform statistics.
- **Profile Management:** Update and view admin profile details.
- **Database Management:**
  - View all items (categories, products, and users).
  - Add new items (categories, products).
  - View details of a single item (categories, products, users).

### Customer Functionalities:
- **Home Page:** View personalized content.
- **Product Search:** Search for categories or specific products.
- **Profile Management:** Update and view customer profile details.
- **Signup/Login:** Secure authentication and user registration.

### Shared Functionalities:
- **Search:** Robust search capabilities for finding categories, products, or users.
- **Scene Transitions:** Seamless navigation between different scenes in the application.

## Core Components

### Main Classes:

#### 1. **GUIproject.java**
This is the main class responsible for initializing the application, loading FXML files, and setting up the scenes for Admin and Customer functionalities. Key features include:
- Adding sample users and data to the database.
- Configuring scenes using FXML files and custom controllers.
- Setting up the primary stage and launching the application.

#### 2. **MainController.java**
This class manages the overall application flow, including:
- Scene transitions using JavaFX's `Platform.runLater()`.
- Search functionality for categories, products, and users.
- Handling user inputs via `TextField` and search.

### FXML Files and Scene Controllers:

- **AdminProfile.fxml:** Admin profile scene.
- **AdminDashboard.fxml:** Admin dashboard scene.
- **DatabaseAllItems.fxml:** View all items in the database.
- **DatabaseSingleItem.fxml:** View details of a single item.
- **AddItemsScene.fxml:** Add new items (categories/products).
- **loginupdatedd.fxml:** Login screen.
- **signupupdatedd.fxml:** Signup screen.

Each FXML file has a corresponding controller class for managing its logic and user interactions.

### Database Integration
The application uses a mock `Database` class to:
- Add and retrieve users (Admins and Customers).
- Add and retrieve categories and products.
- Search for items by name.

### Key Methods:

- **setStageAndScene():**
  Initializes and sets the primary stage and all scenes for the application.

- **switchTo[SceneName]():**
  Dynamically transitions between different scenes.

- **handleTextFieldAction():**
  Handles the search functionality for both Admin and Customer roles.

## Dependencies

- **JavaFX:** For creating GUI components and managing scenes.
- **FXML:** For defining the structure and layout of the GUI.
- **JavaFX Controls:** Includes `Button`, `TextField`, `Alert`, and `Stage` components.
- **Custom Classes:**
  - `Admin`: Represents admin users.
  - `Customer`: Represents customer users.
  - `Category`: Represents product categories.
  - `Product`: Represents individual products.
  - `Database`: Mock database to store users, categories, and products.

## Getting Started

### Prerequisites
- **Java 8 or higher.**
- **JavaFX SDK.**
- A Java-compatible IDE (e.g., IntelliJ IDEA, Eclipse, or NetBeans).

### Setup Instructions
1. Clone the repository.
2. Open the project in your IDE.
3. Ensure the JavaFX SDK is correctly configured.
4. Add the `bettercallmallLogo.png` image file to the resources directory.
5. Run the `GUIproject` class.

### Running the Application
Upon running, the application:
1. Initializes the database with sample data.
2. Launches the login screen (`loginupdatedd.fxml`).
3. Allows navigation through different functionalities based on user roles.

## Future Enhancements
- Implement persistent storage for the database.
- Add authentication mechanisms.
- Enhance the UI/UX with modern JavaFX libraries.
- Improve error handling and logging.

## Contribution
Feel free to contribute to this project by creating a pull request or opening an issue for discussion.

