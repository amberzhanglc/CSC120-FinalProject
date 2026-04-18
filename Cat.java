public class Cat {
    private int x;
    private int y;
    private int lives;
    private int steps;

    public Cat(int x, int y, int lives) {
        this.x = x;
        this.y = y;
        this.lives = lives;
        this.steps = 0;
    }

    public void move(int dx, int dy) {
        x += dx;
        y += dy;
        steps++;
    }

    public void gainLife(int amount) {
        lives += amount;
    }

    public void loseLife(int amount) {
        lives -= amount;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getLives() {
        return lives;
    }

    public int getSteps() {
        return steps;
    }
}