/**
 * Represents an informational clue that helps guide the player.
 */
public class Info extends Event {

    /**
     * Creates an info event with a clue message.
     *
     * @param message the clue message shown to the player
     */
    public Info(String message) {
        super(message);
    }

    /**
     * Shows the clue message without changing the cat's lives or inventory.
     *
     * @param cat the player-controlled cat
     * @param mother the mother cat
     * @return the clue message
     */
    public String applyEffect(Cat cat, MotherCat mother) {
        return message;
    }
}