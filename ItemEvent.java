public class ItemEvent extends Event {
    private String itemName;

    public ItemEvent(String itemName, String message) {
        super(message);
        this.itemName = itemName;
    }

    public String applyEffect(Cat cat, MotherCat mother) {
        if (!cat.hasItem(itemName)) {
            cat.addItem(itemName);
            return message + " You obtained: " + itemName + ".";
        }
        return "You already have the " + itemName + ".";
    }
}