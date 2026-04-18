public abstract class Event {
    protected String message;

    public Event(String message) {
        this.message = message;
    }

    public abstract String applyEffect(Cat cat, MotherCat mother);
}