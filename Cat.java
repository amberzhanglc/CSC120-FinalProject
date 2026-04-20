import java.util.ArrayList;

/**
 * Represents the player-controlled cat in the game.
 * The cat stores its name, position, remaining lives, number of steps,
 * and inventory items collected during the journey.
 */
public class Cat {
    private String name;
    private int x;
    private int y;
    private int lives;
    private int steps;
    private ArrayList<String> inventory;

    /**
     * Creates a new cat with a starting name, position, and number of lives.
     *
     * @param name the cat's name
     * @param x the starting x-coordinate
     * @param y the starting y-coordinate
     * @param lives the starting number of lives
     */
    public Cat(String name, int x, int y, int lives) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.lives = lives;
        this.steps = 0;
        this.inventory = new ArrayList<>();
    }

    /**
     * Moves the cat by the given x and y changes.
     * Each successful move increases the step count by one.
     *
     * @param dx the change in the x direction
     * @param dy the change in the y direction
     */
    public void move(int dx, int dy) {
        x += dx;
        y += dy;
        steps++;
    }

    /**
     * Increases the cat's lives.
     *
     * @param amount the number of lives to add
     */
    public void gainLife(int amount) {
        lives += amount;
    }

    /**
     * Decreases the cat's lives.
     *
     * @param amount the number of lives to remove
     */
    public void loseLife(int amount) {
        lives -= amount;
    }

    /**
     * Adds an item to the cat's inventory.
     *
     * @param item the item to add
     */
    public void addItem(String item) {
        inventory.add(item);
    }

    /**
     * Checks whether the cat has a specific item.
     *
     * @param item the item to check for
     * @return true if the item is in the inventory, false otherwise
     */
    public boolean hasItem(String item) {
        return inventory.contains(item);
    }

    /**
     * Returns the inventory as a string.
     *
     * @return a string version of the inventory
     */
    public String getInventoryString() {
        return inventory.toString();
    }

    /**
     * Returns the cat's name.
     *
     * @return the cat's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the cat's current x-coordinate.
     *
     * @return the x-coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the cat's current y-coordinate.
     *
     * @return the y-coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Returns the cat's remaining lives.
     *
     * @return the number of lives
     */
    public int getLives() {
        return lives;
    }

    /**
     * Returns the number of steps the cat has taken.
     *
     * @return the step count
     */
    public int getSteps() {
        return steps;
    }
}