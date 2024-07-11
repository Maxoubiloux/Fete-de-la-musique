# Fête de la Musique Project

Welcome to the Fête de la Musique project repository. This project simulates management of concerts and music groups using Java in Visual Studio Code.

## Getting Started

This project allows you to manage various aspects of organizing concerts for Fête de la Musique.

### Folder Structure

The workspace contains the following folders by default:

- `src`: holds the source code
- `lib`: stores dependencies
- `bin`: default folder for compiled output

If you wish to customize this structure, modify the settings in `.vscode/settings.json`.

### Dependency Management

You can manage dependencies using the `JAVA PROJECTS` view in Visual Studio Code. Detailed instructions can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).

## Running the Project

To run the project, execute the `App.java` file. It initializes data, imports music groups from a CSV file, and provides a menu-driven interface to add groups, manage concerts, and view listings.

## Project Structure

The project includes the following classes:

- `App.java`: Main class managing the application flow and user interactions.
- `Genre.java`, `Concert.java`, `Groupe.java`, `Lieu.java`: Classes representing music genres, concerts, music groups, and concert venues.
- `GroupeComparator.java`: Utility class to compare music groups alphabetically.
