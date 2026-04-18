public class Cell {
    private int x;
    private int y;
    private Event event;
    private boolean used;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.used = false;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String triggerEvent(Cat cat, MotherCat mother) {
        if (event != null && !used) {
            used = true;
            return event.applyEffect(cat, mother);
        }
        return "Nothing happens here.";
    }

    public String describe() {
        if (used) return "This place feels quiet now.";
        if (event instanceof Treat) return "You smell something tasty nearby.";
        if (event instanceof Trap) return "Something feels dangerous here.";
        if (event instanceof Info) return "You sense a clue nearby.";
        if (event instanceof ItemEvent) return "Something useful is floating here.";
        return "This place seems empty.";
    }
}