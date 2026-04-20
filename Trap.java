/**
 * Represents a dangerous event that causes the cat to lose lives.
 */
public class Trap extends Event {
    private int damage;

    /**
     * Creates a trap with a message and default damage.
     *
     * @param message the message shown when the trap is triggered
     */
    public Trap(String message) {
        super(message);
        this.damage = 2;
    }

    /**
     * Applies the trap effect by removing lives from the cat.
     *
     * @param cat the player-controlled cat
     * @param mother the mother cat
     * @return the trap message and life loss
     */
    public String applyEffect(Cat cat, MotherCat mother) {
        cat.loseLife(damage);
        return message + " (-" + damage + " lives)";
    }
}