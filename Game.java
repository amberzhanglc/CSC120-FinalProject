import java.util.Scanner;

/**
 * Runs the main game loop for The Flow.
 * This class creates the map, player, events, and handles player commands.
 */
public class Game {

    /**
     * Starts the game and repeatedly asks the player for commands until
     * the player wins, loses, or quits.
     *
     * @param args command-line arguments, not used in this game
     */
    public static void main(String[] args) {
        GameMap map = new GameMap(6, 6);
        Cat player = new Cat("Little Black Cat", 0, 5, 8);
        MotherCat mother = new MotherCat(5, 0);
        
        // Place flooded cells
        map.setFlooded(3, 2);
        map.setFlooded(3, 1);
        map.setFlooded(4, 2);

        // Place events on the map
        map.placeEvent(1, 5, new Trap("A frightened bird swoops down and attacks you!"));
        map.placeEvent(2, 4, new Treat("You found a fish under a floating plank. Yum yum!"));
        map.placeEvent(1, 1, new Info("You hear a faint meow from the northeast."));
        map.placeEvent(4, 5, new ItemEvent("raft", "You found a small wooden raft!"));
        map.placeEvent(3, 3, new Trap("A sharp branch scratches your paw."));
        map.placeEvent(4, 4, new Treat("A friendly animal lets you rest beside it."));
        map.placeEvent(0, 2, new Info("The scent of your mother feels stronger to the east."));
        map.placeEvent(2, 2, new Info("The water looks dangerous ahead. Maybe you need something to float on."));
        map.placeEvent(5, 4, new Trap("A sudden wave knocks you against a broken wall."));
        map.placeEvent(5, 2, new Treat("You find a dry spot and recover some strength."));
        map.placeEvent(4, 1, new Info("You can almost hear your mother now."));

        Scanner scanner = new Scanner(System.in);
        boolean playing = true;

        System.out.println("Welcome to The Flow!");
        System.out.println("You are a little black cat trying to find your mother after a flood.");
        System.out.println("Type help to see available commands.");

        while (playing) {
            System.out.println();
            System.out.print("> ");
            String command = scanner.nextLine().trim().toLowerCase();

            if (command.equals("help")) {
                System.out.println("Available commands:");
                System.out.println("  north, south, east, west  - move");
                System.out.println("  look                      - describe your current location");
                System.out.println("  map                       - show the map");
                System.out.println("  status                    - show your lives, steps, and items");
                System.out.println("  quit                      - leave the game");
                System.out.println();
                System.out.println("Map symbols:");
                System.out.println("  C  - you, the little cat");
                System.out.println("  M  - your mother");
                System.out.println("  ~  - flooded water");
                System.out.println("  .  - normal area");

            } else if (command.equals("status")) {
                System.out.println("Position: (" + player.getX() + ", " + player.getY() + ")");
                System.out.println("Lives: " + player.getLives());
                System.out.println("Steps: " + player.getSteps());
                System.out.println("Inventory: " + player.getInventoryString());

            } else if (command.equals("look")) {
                System.out.println(map.describeLocation(player.getX(), player.getY()));

            } else if (command.equals("map")) {
                map.printMap(player, mother);

            } else if (command.equals("north")) {
                movePlayer(player, map, mother, 0, -1);

            } else if (command.equals("south")) {
                movePlayer(player, map, mother, 0, 1);

            } else if (command.equals("east")) {
                movePlayer(player, map, mother, 1, 0);

            } else if (command.equals("west")) {
                movePlayer(player, map, mother, -1, 0);

            } else if (command.equals("quit")) {
                System.out.println("Goodbye!");
                playing = false;

            } else {
                System.out.println("Invalid command. Type help to see available commands.");
            }

            // Win condition
            if (player.getX() == mother.getX() && player.getY() == mother.getY()) {
                printEnding(player, mother);
                playing = false;
            }

            // Lose condition
            if (player.getLives() <= 0) {
                System.out.println("You lost all your lives.");
                System.out.println("Game over.");
                playing = false;
            }
        }

        scanner.close();
    }

    /**
     * Attempts to move the player in a chosen direction.
     * The move is blocked if the destination is outside the map or if
     * the destination is flooded and the player does not have the raft.
     *
     * @param player the player-controlled cat
     * @param map the game map
     * @param mother the mother cat
     * @param dx the change in the x direction
     * @param dy the change in the y direction
     */
    public static void movePlayer(Cat player, GameMap map, MotherCat mother, int dx, int dy) {
        int newX = player.getX() + dx;
        int newY = player.getY() + dy;

        if (!map.isValidMove(newX, newY)) {
            System.out.println("You cannot move outside the map.");
            return;
        }

        // Restricted access: need raft to cross flooded cells
        if (map.isFlooded(newX, newY) && !player.hasItem("raft")) {
            System.out.println("The floodwater is too deep here. You need a raft to cross.");
            return;
        }

        player.move(dx, dy);
        System.out.println("You moved to (" + player.getX() + ", " + player.getY() + ").");
        System.out.println(map.describeLocation(player.getX(), player.getY()));
    }

    /**
     * Prints the ending message based on the player's steps and remaining lives.
     *
     * @param player the player-controlled cat
     * @param mother the mother cat
     */
    public static void printEnding(Cat player, MotherCat mother) {
        System.out.println("You found your mother!");
        System.out.println(mother.speak(player.getSteps()));
        
        if (player.getSteps() <= 12) {
            System.out.println("Best Ending: You found your mother quickly and safely.");
        } else if (player.getLives() <= 3) {
            System.out.println("Survival Ending: You barely made it through the flood, but you are finally safe.");
        } else {
            System.out.println("Safe Ending: After a long journey, you finally reunited with your mother.");
        }

        System.out.println("You win!");
    }
}