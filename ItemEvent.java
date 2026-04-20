/**
 * Represents an event where the cat finds and collects an item.
 */
public class ItemEvent extends Event {
    private String itemName;

    /**
     * Creates an item event with an item name and message.
     *
     * @param itemName the name of the item collected
     * @param message the message shown when the item is found
     */
    public ItemEvent(String itemName, String message) {
        super(message);
        this.itemName = itemName;
    }

    /**
     * Adds the item to the cat's inventory if the cat does not already have it.
     *
     * @param cat the player-controlled cat
     * @param mother the mother cat
     * @return the item collection message
     */
    public String applyEffect(Cat cat, MotherCat mother) {
        if (!cat.hasItem(itemName)) {
            cat.addItem(itemName);
            return message + " You obtained: " + itemName + ".";
        }
        return "You already have the " + itemName + ".";
    }
}