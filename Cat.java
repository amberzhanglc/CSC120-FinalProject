import java.util.ArrayList;

public class Cat {
    private String name;
    private int x;
    private int y;
    private int lives;
    private int steps;
    private ArrayList<String> inventory;

    public Cat(String name, int x, int y, int lives) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.lives = lives;
        this.steps = 0;
        this.inventory = new ArrayList<>();
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

    public void addItem(String item) {
        inventory.add(item);
    }

    public boolean hasItem(String item) {
        return inventory.contains(item);
    }

    public String getInventoryString() {
        return inventory.toString();
    }

    public String getName() { return name; }
    public int getX() { return x; }
    public int getY() { return y; }
    public int getLives() { return lives; }
    public int getSteps() { return steps; }
}