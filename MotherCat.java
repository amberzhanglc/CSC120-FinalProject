/**
 * Represents the mother cat that the player is trying to find.
 * The mother cat has a fixed position and gives different messages
 * depending on how quickly the player reaches her.
 */
public class MotherCat {
    private int x;
    private int y;

    /**
     * Creates a mother cat at a specific location.
     *
     * @param x the mother cat's x-coordinate
     * @param y the mother cat's y-coordinate
     */
    public MotherCat(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the mother cat's ending message based on the player's steps.
     *
     * @param steps the number of steps the player took
     * @return the ending message
     */
    public String speak(int steps) {
        if (steps <= 12) {
            return "You are so fast!";
        } else if (steps <= 18) {
            return "I knew you would find me.";
        } else {
            return "I miss you so much... where have you been for so long?";
        }
    }

    /**
     * Returns the mother cat's x-coordinate.
     *
     * @return the x-coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the mother cat's y-coordinate.
     *
     * @return the y-coordinate
     */
    public int getY() {
        return y;
    }
}