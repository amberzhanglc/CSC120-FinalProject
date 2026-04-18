public class MotherCat {
    private int x;
    private int y;

    public MotherCat(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String speak(int steps) {
        if (steps <= 5) {
            return "You are so fast!";
        } else if (steps <= 10) {
            return "I knew you would find me.";
        } else {
            return "I miss you so much. Where have you been for so long?";
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}