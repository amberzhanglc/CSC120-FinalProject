/**
 * Represents one location on the game map.
 * A cell may contain an event and keeps track of whether that event
 * has already been used.
 */
public class Cell {
    private int x;
    private int y;
    private Event event;
    private boolean used;

    /**
     * Creates a cell at a specific map location.
     *
     * @param x the cell's x-coordinate
     * @param y the cell's y-coordinate
     */
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.used = false;
    }

    /**
     * Places an event in this cell.
     *
     * @param event the event stored in this cell
     */
    public void setEvent(Event event) {
        this.event = event;
    }

    /**
     * Triggers the cell's event if one exists and has not already been used.
     *
     * @param cat the player-controlled cat
     * @param mother the mother cat
     * @return the result message from the event, or a default message
     */
    public String triggerEvent(Cat cat, MotherCat mother) {
        if (event != null && !used) {
            used = true;
            return event.applyEffect(cat, mother);
        }
        return "Nothing happens here.";
    }

    /**
     * Describes the cell before or after its event has been used.
     *
     * @return a short description of the cell
     */
    public String describe() {
        if (used) return "This place feels quiet now.";
        if (event instanceof Treat) return "You smell something tasty nearby.";
        if (event instanceof Trap) return "Something feels dangerous here.";
        if (event instanceof Info) return "You sense a clue nearby.";
        if (event instanceof ItemEvent) return "Something useful is floating here.";
        return "This place seems empty.";
    }
}