/**
 * Represents a helpful event that gives the cat extra lives.
 */
public class Treat extends Event {
    private int lifeChange;

    /**
     * Creates a treat with a message and default life gain.
     *
     * @param message the message shown when the treat is triggered
     */
    public Treat(String message) {
        super(message);
        this.lifeChange = 2;
    }

    /**
     * Applies the treat effect by adding lives to the cat.
     *
     * @param cat the player-controlled cat
     * @param mother the mother cat
     * @return the treat message and life gain
     */
    public String applyEffect(Cat cat, MotherCat mother) {
        cat.gainLife(lifeChange);
        return message + " (+" + lifeChange + " lives)";
    }
}