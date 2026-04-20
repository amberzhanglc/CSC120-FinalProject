import java.util.Scanner;

/**
 * Runs the main game loop for The Flow.
 * This class creates the map, player, events, and handles player commands.
 */
public class Game {
    private static final int RAFT_X = 2;
    private static final int RAFT_Y = 6;

    /**
     * Starts the game and allows the player to restart after winning or losing.
     *
     * @param args command-line arguments, not used in this game
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean keepPlaying = true;

        while (keepPlaying) {
            keepPlaying = playOneGame(scanner);
        }

        scanner.close();
    }

    /**
     * Runs one full round of the game.
     *
     * @param scanner the scanner used to read player commands
     * @return true if the player wants to restart, false if the player wants to end
     */
    public static boolean playOneGame(Scanner scanner) {
        GameMap map = createMap();
        Cat player = new Cat("Little Black Cat", 0, 7, 8);
        MotherCat mother = new MotherCat(7, 0);

        placeEvents(map);

        System.out.println("Welcome to The Flow!");
        System.out.println("You are a little black cat trying to find your mother after a flood.");
        System.out.println("Your mother is hidden on the map, somewhere across the flooded river.");
        System.out.println("You may need to find something useful before you can cross the water.");
        System.out.println("Type help to see available commands.");
        System.out.println();

        printQuickStatus(player);
        System.out.println("Current map:");
        map.printMap(player, mother);

        while (true) {
            System.out.println();
            System.out.print("> ");
            String command = scanner.nextLine().trim().toLowerCase();

            if (command.equals("help")) {
                printHelp();

            } else if (command.equals("status")) {
                printFullStatus(player);

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

            } else if (command.equals("restart")) {
                System.out.println("Restarting the game...");
                return true;

            } else if (command.equals("end") || command.equals("quit")) {
                System.out.println("Ending the game. Goodbye!");
                return false;

            } else {
                System.out.println("Invalid command. Type help to see available commands.");
            }

            if (player.getX() == mother.getX() && player.getY() == mother.getY()) {
                printEnding(player, mother);
                return askRestartOrEnd(scanner);
            }

            if (player.getLives() <= 0) {
                System.out.println("You lost all your lives.");
                System.out.println("Game over.");
                return askRestartOrEnd(scanner);
            }
        }
    }

    /**
     * Creates the game map and marks flooded cells.
     *
     * @return the initialized game map
     */
    public static GameMap createMap() {
        GameMap map = new GameMap(8, 8);

        // A full flooded river blocks the left side from the right side.
        for (int y = 0; y < 8; y++) {
            map.setFlooded(4, y);
        }

        return map;
    }

    /**
     * Places all hidden events on the game map.
     *
     * @param map the game map
     */
    public static void placeEvents(GameMap map) {
        // Left side: player must search for the raft before crossing the river.
        map.placeEvent(0, 6, new Info("You hear rushing water to the east. You may need something to float on."));
        map.placeEvent(1, 7, new Trap("A frightened bird swoops down and attacks you!"));
        map.placeEvent(2, 7, new Trap("Broken glass hidden in the mud cuts your paw."));
        map.placeEvent(3, 7, new Trap("A loose plank flips under your feet."));

        map.placeEvent(1, 6, new Treat("You find a dry box and rest inside for a moment."));
        map.placeEvent(RAFT_X, RAFT_Y, new ItemEvent("raft", "You found a small wooden raft!"));
        map.placeEvent(3, 6, new Info("The river is wide, but the raft may help you cross."));

        map.placeEvent(0, 5, new Trap("A pile of sharp debris blocks your way."));
        map.placeEvent(1, 5, new Trap("The mud suddenly pulls at your paws."));
        map.placeEvent(2, 5, new Info("A faint meow seems to come from far to the northeast."));
        map.placeEvent(3, 5, new Treat("You found a small fish under a floating plank."));

        map.placeEvent(0, 4, new Trap("A hidden hole makes you stumble hard."));
        map.placeEvent(1, 4, new Info("The safest path does not always look like the shortest one."));
        map.placeEvent(2, 4, new Trap("A broken fence scratches your side."));
        map.placeEvent(3, 4, new Trap("A sudden wave pushes debris into your path."));

        map.placeEvent(1, 3, new Treat("A friendly animal lets you rest beside it."));
        map.placeEvent(2, 3, new Trap("The ground collapses under your paws."));
        map.placeEvent(3, 3, new Info("The smell of your mother grows stronger across the river."));

        // River: crossing is required, but some parts are dangerous.
        map.placeEvent(4, 7, new Trap("The current pulls hard against your raft."));
        map.placeEvent(4, 6, new Trap("A hidden branch hits the side of the raft."));
        map.placeEvent(4, 5, new Info("The raft shakes, but you manage to stay above the water."));
        map.placeEvent(4, 4, new Trap("Cold water splashes over you and slows you down."));
        map.placeEvent(4, 3, new Treat("You find a calm patch of water and catch your breath."));
        map.placeEvent(4, 2, new Trap("A strong current almost pulls you under."));
        map.placeEvent(4, 1, new Info("You are across the worst of the river now."));

        // Right side: mother is hidden somewhere in the northeast.
        map.placeEvent(5, 7, new Trap("A sudden wave knocks you against a broken wall."));
        map.placeEvent(6, 7, new Trap("You step on sharp stones near the shore."));
        map.placeEvent(7, 7, new Treat("You find a warm, dry corner and recover some strength."));

        map.placeEvent(5, 6, new Trap("The wet ground slips under your paws."));
        map.placeEvent(6, 6, new Info("The scent of your mother is stronger toward the north."));
        map.placeEvent(7, 6, new Trap("A falling branch almost traps you."));

        map.placeEvent(5, 5, new Treat("You find a safe ledge and rest for a moment."));
        map.placeEvent(6, 5, new Trap("A broken board snaps beneath you."));
        map.placeEvent(7, 5, new Info("You hear a soft meow somewhere above you."));

        map.placeEvent(5, 4, new Trap("A dog barks suddenly and scares you backward."));
        map.placeEvent(6, 4, new Trap("A pile of metal scraps cuts your paw."));
        map.placeEvent(7, 4, new Info("The meow is closer now. Keep moving north."));

        map.placeEvent(5, 3, new Treat("You find a dry spot and recover some strength."));
        map.placeEvent(6, 3, new Trap("A loose roof tile falls beside you."));
        map.placeEvent(7, 3, new Info("Your mother's scent is very strong now."));

        map.placeEvent(5, 2, new Trap("A deep puddle hides sharp debris."));
        map.placeEvent(6, 2, new Info("The sound is coming from the northeast."));
        map.placeEvent(7, 2, new Trap("You slip on the wet stone steps."));

        map.placeEvent(5, 1, new Trap("A sudden gust pushes you into a broken branch."));
        map.placeEvent(6, 1, new Info("Your mother is very close."));
        map.placeEvent(7, 1, new Trap("A final wave crashes near the shore."));
    }

    /**
     * Prints all available commands and map symbols.
     */
    public static void printHelp() {
        System.out.println("Available commands:");
        System.out.println("  north, south, east, west  - move");
        System.out.println("  look                      - describe your current location");
        System.out.println("  map                       - show the map");
        System.out.println("  status                    - show your lives, steps, and items");
        System.out.println("  restart                   - restart the game");
        System.out.println("  end                       - end the game");
        System.out.println("  quit                      - end the game");
        System.out.println();
        System.out.println("Map symbols:");
        System.out.println("  C  - you, the little cat");
        System.out.println("  ~  - flooded water");
        System.out.println("  .  - normal area");
        System.out.println();
        System.out.println("Your mother is hidden on the map, somewhere across the flooded river.");
        System.out.println("If you cannot cross the river, look for something useful on your side first.");
        System.out.println("Hidden events are not shown on the map. Move carefully.");
    }

    /**
     * Prints a full status report for the player.
     *
     * @param player the player-controlled cat
     */
    public static void printFullStatus(Cat player) {
        System.out.println("Position: (" + player.getX() + ", " + player.getY() + ")");
        System.out.println("Lives: " + player.getLives());
        System.out.println("Steps: " + player.getSteps());
        System.out.println("Inventory: " + player.getInventoryString());
    }

    /**
     * Prints a short status report after movement.
     *
     * @param player the player-controlled cat
     */
    public static void printQuickStatus(Cat player) {
        System.out.println("Lives: " + player.getLives()
                + " | Steps: " + player.getSteps()
                + " | Inventory: " + player.getInventoryString());
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

        if (map.isFlooded(newX, newY) && !player.hasItem("raft")) {
            System.out.println("The floodwater is too deep here. You need a raft to cross.");
            System.out.println(getDirectionHint(player.getX(), player.getY(), RAFT_X, RAFT_Y,
                    "The raft may be somewhere"));
            return;
        }

        player.move(dx, dy);
        System.out.println("You moved to (" + player.getX() + ", " + player.getY() + ").");

        Cell currentCell = map.getCell(player.getX(), player.getY());
        if (currentCell != null) {
            System.out.println(currentCell.triggerEvent(player, mother));
        }

        printQuickStatus(player);
        System.out.println("Current map:");
        map.printMap(player, mother);
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

        if (player.getSteps() <= 16) {
            System.out.println("Best Ending: You found your mother quickly and safely.");
        } else if (player.getLives() <= 3) {
            System.out.println("Survival Ending: You barely made it through the flood, but you are finally safe.");
        } else {
            System.out.println("Safe Ending: After a long journey, you finally reunited with your mother.");
        }

        System.out.println("You win!");
    }

    /**
     * Gives a general direction from the player's current location to a target.
     *
     * @param currentX the player's current x-coordinate
     * @param currentY the player's current y-coordinate
     * @param targetX the target x-coordinate
     * @param targetY the target y-coordinate
     * @param messageStart the beginning of the hint message
     * @return a direction hint
     */
    public static String getDirectionHint(int currentX, int currentY, int targetX, int targetY, String messageStart) {
        String vertical = "";
        String horizontal = "";

        if (targetY < currentY) {
            vertical = "north";
        } else if (targetY > currentY) {
            vertical = "south";
        }

        if (targetX < currentX) {
            horizontal = "west";
        } else if (targetX > currentX) {
            horizontal = "east";
        }

        if (!vertical.equals("") && !horizontal.equals("")) {
            return messageStart + " to the " + vertical + horizontal + ".";
        } else if (!vertical.equals("")) {
            return messageStart + " to the " + vertical + ".";
        } else if (!horizontal.equals("")) {
            return messageStart + " to the " + horizontal + ".";
        } else {
            return "You are already at the place you were looking for.";
        }
    }

    /**
     * Asks the player to restart or end the game.
     *
     * @param scanner the scanner used to read input
     * @return true if the player chooses restart, false if the player chooses end
     */
    public static boolean askRestartOrEnd(Scanner scanner) {
        while (true) {
            System.out.println();
            System.out.print("Type restart to play again, or type end to finish: ");
            String answer = scanner.nextLine().trim().toLowerCase();

            if (answer.equals("restart")) {
                System.out.println("Restarting the game...");
                return true;
            } else if (answer.equals("end") || answer.equals("quit")) {
                System.out.println("Ending the game. Goodbye!");
                return false;
            } else {
                System.out.println("Invalid choice. Please type restart or end.");
            }
        }
    }
}