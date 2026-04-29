# CSC120 Final Project: The Flow

## Project Overview

**The Flow** is a survival adventure game about a little black cat trying to find its mother after a flood. The player must explore an 8 by 8 map, avoid traps, collect helpful items, find the raft, cross the flooded river, and follow clues to reach the hidden mother cat.

Our game includes both the original game setting and a graphical interface. By implementing what we learned from youtube, we designed a interface where the player uses on-screen buttons instead of typing commands in the terminal. This made the game easier to follow and more visually engaging while keeping the same core mechanics.

## Game Layout

The game world is an 8 by 8 grid.

- The player starts at `(0, 7)`
- The mother cat is hidden at `(7, 0)`
- The flooded river is the full column where `x = 4`
- The raft is located at `(2, 6)`

The river separates the left side of the map from the right side. The player cannot cross the river without first finding the raft.

## Main Features

- Hidden events placed across the map
- Trap events that remove lives
- Treat events that restore lives
- Info events that give clues
- Item events that add objects to the inventory
- Restricted access to the river until the raft is collected
- Multiple endings depending on the player’s path, steps, and remaining lives
- A graphical interface with clickable movement buttons and information hub

## Classes and Design

Our design separates the game into several classes with different responsibilities.

- `Cat` stores the player’s position, lives, steps, and inventory
- `MotherCat` stores the mother’s position and ending dialogue
- `Event` is the parent class for different event types
- `Trap`, `Treat`, `Info`, and `ItemEvent` define different event effects: remove lifes, restore lifes, give hints and add objects to the inventory
- `Cell` stores whether a location has an event and whether it has already been used
- `GameMap` stores the grid, flooded cells, and location descriptions
- `Game` handles the original game setup, event placement, and shared game logic
- `GameUI` provides the graphical interface

This structure made the project easier to organize and extend because the map, player state, events, and interface are not all mixed together in one class.

## Design

Our overall design goal was to build a game that was clear, expandable, and easy to manage. We wanted the story, map, and event system to all work together without making the code too complicated.

We chose to represent the game world as a grid of `Cell` objects inside `GameMap`, because the player moves through fixed coordinates and each location may contain a hidden event. This made it straightforward to place traps, clues, treats, and items at specific positions.

We also used inheritance for events. Instead of writing all event behavior directly in one class, we created an abstract `Event` class and then extended it with `Trap`, `Treat`, `Info`, and `ItemEvent`. This made the behavior of each event type much easier to understand and change.

For the player inventory, we used Java’s built-in `ArrayList<String>`. This was a simple and effective choice because the game only needs to store a small list of collected items, such as the raft.


## Approach

Our overall approach was to first build the core game logic and map structure, and then improve the project step by step. We started by making sure the player could move, interact with the map, trigger events, lose or gain lives, and eventually reach the mother cat.

After the basic mechanics were working, we focused on making the story more coherent and the layout more meaningful. We carefully placed clues, traps, and treats so that the player would need to think about where to go rather than just walk directly to the goal.

Later, we added the graphical interface. Instead of replacing the entire game, we reused the classes that already handled the player, map, and events, and built a new visual layer on top of them. This helped us improve the interface without rewriting the whole project.

## What We Learned

One important thing we learned was how useful it is to separate responsibilities across classes. At first, it was tempting to put too much logic in one place, but the project became much cleaner once we split player state, map structure, and event behavior into different classes.

We also understand better about inheritance and abstraction through the `Event` class structure. Using a shared parent class made the code easier to organize and helped us avoid repeating similar logic.

## What We Would Implement Differently

If we were doing the project again, one thing we might change is the way the terminal and GUI versions coexist. Right now, the project keeps both versions, which is useful, but it also means some instructions and files need to stay carefully synchronized.

We also want to try a more advanced visual map system so that the GUI could show better visual effect, such as colors, icons, or images, while still staying clear and simple. In the current version, the GUI is functional and much easier to use than the terminal version, but it could still be made more polished.

Another thing we might improve is how explored spaces are displayed. Right now, the game focuses on current position and basic symbols, but it could be interesting to track visited areas more explicitly.

## If We Had Unlimited Time

If we had unlimited time, we would add more features to make the game richer and more immersive.

Possible additions include:

- More collectible items besides the raft
- More event types, such as friendly creatures or temporary obstacles
- A stronger visual design for the GUI (probably vibecoding to be more playful)
- Sound effects or background music
- A save/load feature
- Randomized event placement for replayability (Mom and raft can change their position every round of the game)
- A visible start screen or ending screen with custom artwork

## Most Helpful Feedback

One of the most helpful pieces of feedback we received was to make the whole map as a grid and have a simple graphic design before we started. One of our peers also suggested that the game would be easier to understand and more appealing if the player could see a visual interface instead of only using the terminal. That pushed us to build the UI of the game.

## Advice to Our Past Selves

If we could go back in time, we would tell ourselves to separate the game logic into smaller classes as early as possible. That decision saved us a lot of trouble later.

We would also remind ourselves to keep user-facing files, such as the cheatsheet and README, updated while the project changes. It is easy to focus only on the code and forget that the written files also need to match the final version of the game.

## Team Dynamics

Working as a team of two helped us divide the project into manageable parts. We consistently copilot and contribute our ideas together. We work together in person all the time, so everything went on really well.

## Running the Game

To run the graphical version of the game, run:

- `GameUI.java`

To run the original terminal version, run:

- `Game.java`

## Included Files

This submission includes:

- all Java source files for the game
- `cheatsheet.md`
- `rubric.md`
- `README.md`
- architecture diagram
- design justification
- map of the game layout