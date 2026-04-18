import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        GameMap map = new GameMap(6, 6);
        Cat player = new Cat("Little Black Cat", 0, 5, 8);
        MotherCat mother = new MotherCat(5, 0);

        // Place events on the map
        map.placeEvent(1, 5, new Trap("A frightened bird swoops down and attacks you!"));
        map.placeEvent(2, 4, new Treat("You found a fish under a floating plank. Yum yum!"));
        map.placeEvent(1, 1, new Info("You hear a faint meow from the northeast."));
        map.placeEvent(4, 5, new ItemEvent("raft", "You found a small wooden raft!"));
        map.placeEvent(3, 3, new Trap("A sharp branch scratches your paw."));
        map.placeEvent(4, 4, new Treat("A friendly animal lets you rest beside it."));
        map.placeEvent(0, 2, new Info("The scent of your mother feels stronger to the east."));

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

            } else if (command.equals("status")) {
                System.out.println("Position: (" + player.getX() + ", " + player.getY() + ")");
                System.out.println("Lives: " + player.getLives());
                System.out.println("Steps: " + player.getSteps());
                System.out.println("Inventory: " + player.getInventoryString());

            } else if (command.equals("look")) {
                Cell currentCell = map.getCell(player.getX(), player.getY());
                if (currentCell != null) {
                    System.out.println(currentCell.describe());
                }

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
                System.out.println("You found your mother!");
                System.out.println(mother.speak(player.getSteps()));
                System.out.println("You win!");
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

    public static void movePlayer(Cat player, GameMap map, MotherCat mother, int dx, int dy) {
        int newX = player.getX() + dx;
        int newY = player.getY() + dy;

        if (!map.isValidMove(newX, newY)) {
            System.out.println("You cannot move outside the map.");
            return;
        }

        // Restricted access: need raft to cross this flooded cell
        if (newX == 3 && newY == 2 && !player.hasItem("raft")) {
            System.out.println("The floodwater is too deep here. You need a raft to cross.");
            return;
        }

        player.move(dx, dy);
        System.out.println("You moved to (" + player.getX() + ", " + player.getY() + ").");

        Cell currentCell = map.getCell(player.getX(), player.getY());
        if (currentCell != null) {
            System.out.println(currentCell.triggerEvent(player, mother));
        }
    }
}