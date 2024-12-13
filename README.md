# Todo-Abbas Android App

A native Android todo list application built with Java that demonstrates core Android development concepts and SQLite database integration.

## Features

- Create, Read, Update, and Delete tasks
- Mark tasks as complete/incomplete
- Swipe gestures for editing and deleting tasks
- Persistent storage using SQLite
- Material Design UI components
- Splash screen with app branding

## Technical Stack

### Core Components
- **Language**: Java
- **Minimum SDK**: 26 (Android 8.0)
- **Target SDK**: 34
- **Database**: SQLite
- **Build System**: Gradle

### Key Dependencies
- androidx.appcompat
- com.google.android.material
- androidx.constraintlayout
- androidx.recyclerview
- androidx.cardview

## Project Structure

### 1. Activities
- **SplashActivity**: Initial loading screen (2-second delay)
- **MainActivity**: Main screen containing the task list and add button

### 2. Database Implementation
The app uses SQLite for data persistence with the following structure:

~~~sql
CREATE TABLE toDoList (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    task TEXT,
    status INTEGER
)
~~~

### 3. Core Features

#### Task Management
- **Model**: ToDoModel class for task data structure
- **Adapter**: ToDoAdapter for RecyclerView implementation
- **Database Handler**: CRUD operations for tasks

#### UI Components
- RecyclerView for task list
- FloatingActionButton for adding new tasks
- BottomSheetDialog for task creation/editing
- Custom CardView for task items
- Swipe gestures for edit/delete actions

### 4. User Interface

The main activity layout includes:
- Header with "Tasks" title
- RecyclerView for displaying tasks
- FloatingActionButton for adding new tasks
- Custom CardView for each task item
- Swipe actions for edit/delete

## Key Features Implementation

### 1. Task Creation
New tasks are added through a BottomSheetDialog with:
- EditText for task input
- Save button
- Input validation
- Database insertion

### 2. Swipe Actions
The app implements swipe gestures for task management:
- Swipe Right: Edit task
- Swipe Left: Delete task with confirmation

### 3. Database Operations
All database operations are handled through the DatabaseHandler class:
- Insert tasks
- Update task status
- Update task content
- Delete tasks
- Retrieve all tasks

## Project Setup

1. Clone the repository
2. Open in Android Studio
3. Sync Gradle files
4. Run on an emulator or physical device (Android 8.0 or higher)

## Architecture

The project follows a simple architecture:
- **Model**: Data structure and database operations
- **View**: XML layouts and UI components
- **Controller**: Activities and Adapters

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit changes
4. Push to the branch
5. Create a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Acknowledgments

- Material Design Components
- Android SQLite Documentation
- RecyclerView and CardView implementations
