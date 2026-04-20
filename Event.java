/**
 * Represents a general event that can happen on a map cell.
 * Specific event types, such as traps, treats, clues, and item events,
 * extend this abstract class and define their own effects.
 */
public abstract class Event {
    protected String message;

    /**
     * Creates an event with a message shown to the player.
     *
     * @param message the event message
     */
    public Event(String message) {
        this.message = message;
    }

    /**
     * Applies the event's effect to the cat or the game state.
     *
     * @param cat the player-controlled cat
     * @param mother the mother cat
     * @return the message shown after the event happens
     */
    public abstract String applyEffect(Cat cat, MotherCat mother);
}