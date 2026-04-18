public class Trap extends Event {
    private int damage;

    public Trap(String message) {
        super(message);
        this.damage = 1;
    }

    public String applyEffect(Cat cat, MotherCat mother) {
        cat.loseLife(damage);
        return message + " (-" + damage + " life)";
    }
}