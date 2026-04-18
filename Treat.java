public class Treat extends Event {
    private int lifeChange;

    public Treat(String message) {
        super(message);
        this.lifeChange = 2;
    }

    public String applyEffect(Cat cat, MotherCat mother) {
        cat.gainLife(lifeChange);
        return message + " (+" + lifeChange + " lives)";
    }
}