import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 * Graphical UI version of The Flow.
 * This class reuses the existing game logic classes and displays the game
 * in a Swing window instead of the terminal.
 */
public class GameUI extends JFrame {
    private static final int GRID_SIZE = 8;
    private static final int RAFT_X = 2;
    private static final int RAFT_Y = 6;

    private GameMap map;
    private Cat player;
    private MotherCat mother;

    private JButton[][] mapButtons;

    private JLabel livesLabel;
    private JLabel stepsLabel;
    private JLabel inventoryLabel;
    private JLabel positionLabel;

    private JTextArea messageArea;

    private JButton northButton;
    private JButton southButton;
    private JButton eastButton;
    private JButton westButton;
    private JButton lookButton;
    private JButton restartButton;
    private JButton endButton;

    /**
     * Creates the game window and starts a new game.
     */
    public GameUI() {
        setTitle("The Flow");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(12, 12));

        initializeGameState();
        buildUI();
        updateDisplay();
        showWelcomeMessage();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Initializes or resets the game state.
     */
    private void initializeGameState() {
        map = Game.createMap();
        player = new Cat("Little Black Cat", 0, 7, 8);
        mother = new MotherCat(7, 0);
        Game.placeEvents(map);
    }

    /**
     * Builds the whole window layout.
     */
    private void buildUI() {
        JPanel mainPanel = new JPanel(new BorderLayout(12, 12));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        JPanel mapPanel = buildMapPanel();
        JPanel sidePanel = buildSidePanel();

        mainPanel.add(mapPanel, BorderLayout.CENTER);
        mainPanel.add(sidePanel, BorderLayout.EAST);

        add(mainPanel, BorderLayout.CENTER);
    }

    /**
     * Builds the visual map panel.
     *
     * @return the map panel
     */
    private JPanel buildMapPanel() {
        JPanel outerPanel = new JPanel(new BorderLayout(8, 8));

        JLabel title = new JLabel("Map", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        outerPanel.add(title, BorderLayout.NORTH);

        JPanel gridPanel = new JPanel(new GridLayout(GRID_SIZE, GRID_SIZE, 2, 2));
        gridPanel.setPreferredSize(new Dimension(480, 480));

        mapButtons = new JButton[GRID_SIZE][GRID_SIZE];

        for (int y = 0; y < GRID_SIZE; y++) {
            for (int x = 0; x < GRID_SIZE; x++) {
                JButton cellButton = new JButton();
                cellButton.setFocusPainted(false);
                cellButton.setEnabled(false);
                cellButton.setFont(new Font("SansSerif", Font.BOLD, 18));
                cellButton.setPreferredSize(new Dimension(55, 55));
                mapButtons[y][x] = cellButton;
                gridPanel.add(cellButton);
            }
        }

        outerPanel.add(gridPanel, BorderLayout.CENTER);
        return outerPanel;
    }

    /**
     * Builds the right-side information and controls panel.
     *
     * @return the side panel
     */
    private JPanel buildSidePanel() {
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setPreferredSize(new Dimension(320, 480));

        JLabel infoTitle = new JLabel("Game Status");
        infoTitle.setFont(new Font("SansSerif", Font.BOLD, 18));

        livesLabel = new JLabel();
        stepsLabel = new JLabel();
        inventoryLabel = new JLabel();
        positionLabel = new JLabel();

        sidePanel.add(infoTitle);
        sidePanel.add(Box.createVerticalStrut(10));
        sidePanel.add(livesLabel);
        sidePanel.add(Box.createVerticalStrut(6));
        sidePanel.add(stepsLabel);
        sidePanel.add(Box.createVerticalStrut(6));
        sidePanel.add(inventoryLabel);
        sidePanel.add(Box.createVerticalStrut(6));
        sidePanel.add(positionLabel);
        sidePanel.add(Box.createVerticalStrut(16));

        JLabel messageTitle = new JLabel("Message");
        messageTitle.setFont(new Font("SansSerif", Font.BOLD, 18));

        messageArea = new JTextArea(10, 24);
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        messageArea.setEditable(false);
        messageArea.setFont(new Font("SansSerif", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(messageArea);
        scrollPane.setPreferredSize(new Dimension(300, 180));

        sidePanel.add(messageTitle);
        sidePanel.add(Box.createVerticalStrut(8));
        sidePanel.add(scrollPane);
        sidePanel.add(Box.createVerticalStrut(16));

        JLabel controlsTitle = new JLabel("Controls");
        controlsTitle.setFont(new Font("SansSerif", Font.BOLD, 18));
        sidePanel.add(controlsTitle);
        sidePanel.add(Box.createVerticalStrut(10));

        JPanel controlsPanel = new JPanel(new GridLayout(3, 3, 6, 6));

        controlsPanel.add(new JLabel(""));
        northButton = new JButton("NORTH");
        controlsPanel.add(northButton);
        controlsPanel.add(new JLabel(""));

        westButton = new JButton("WEST");
        controlsPanel.add(westButton);

        lookButton = new JButton("LOOK");
        controlsPanel.add(lookButton);

        eastButton = new JButton("EAST");
        controlsPanel.add(eastButton);

        controlsPanel.add(new JLabel(""));
        southButton = new JButton("SOUTH");
        controlsPanel.add(southButton);
        controlsPanel.add(new JLabel(""));

        sidePanel.add(controlsPanel);
        sidePanel.add(Box.createVerticalStrut(12));

        JPanel bottomButtons = new JPanel(new GridLayout(1, 2, 6, 6));
        restartButton = new JButton("RESTART");
        endButton = new JButton("END");
        bottomButtons.add(restartButton);
        bottomButtons.add(endButton);

        sidePanel.add(bottomButtons);

        attachListeners();

        return sidePanel;
    }

    /**
     * Connects button clicks to game actions.
     */
    private void attachListeners() {
        northButton.addActionListener(e -> movePlayer(0, -1));
        southButton.addActionListener(e -> movePlayer(0, 1));
        eastButton.addActionListener(e -> movePlayer(1, 0));
        westButton.addActionListener(e -> movePlayer(-1, 0));

        lookButton.addActionListener(e -> {
            String message = map.describeLocation(player.getX(), player.getY());
            setMessage(message);
        });

        restartButton.addActionListener(e -> restartGame());

        endButton.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to end the game?",
                    "End Game",
                    JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {
                dispose();
            }
        });
    }

    /**
     * Updates all labels and map cells.
     */
    private void updateDisplay() {
        updateStatusLabels();
        updateMapButtons();
    }

    /**
     * Updates the status labels shown on the right side.
     */
    private void updateStatusLabels() {
        livesLabel.setText("Lives: " + player.getLives());
        stepsLabel.setText("Steps: " + player.getSteps());
        inventoryLabel.setText("Inventory: " + player.getInventoryString());
        positionLabel.setText("Position: (" + player.getX() + ", " + player.getY() + ")");
    }

    /**
     * Updates the map cell text to reflect player and flooded tiles.
     */
    private void updateMapButtons() {
        for (int y = 0; y < GRID_SIZE; y++) {
            for (int x = 0; x < GRID_SIZE; x++) {
                JButton button = mapButtons[y][x];

                if (player.getX() == x && player.getY() == y) {
                    button.setText("🐈‍");
                } else if (map.isFlooded(x, y)) {
                    button.setText("🌊");
                } else {
                    button.setText("❔");
                }
            }
        }
    }

    /**
     * Handles player movement using the same logic as the terminal version,
     * but displays messages in the UI instead of printing to the console.
     *
     * @param dx change in x
     * @param dy change in y
     */
    private void movePlayer(int dx, int dy) {
        int newX = player.getX() + dx;
        int newY = player.getY() + dy;

        if (!map.isValidMove(newX, newY)) {
            setMessage("You cannot move outside the map.");
            return;
        }

        if (map.isFlooded(newX, newY) && !player.hasItem("raft")) {
            String hint = Game.getDirectionHint(
                    player.getX(),
                    player.getY(),
                    RAFT_X,
                    RAFT_Y,
                    "The raft may be somewhere"
            );
            setMessage("The floodwater is too deep here. You need a raft to cross.\n" + hint);
            return;
        }

        player.move(dx, dy);

        StringBuilder result = new StringBuilder();
        result.append("You moved to (")
              .append(player.getX())
              .append(", ")
              .append(player.getY())
              .append(").");

        Cell currentCell = map.getCell(player.getX(), player.getY());
        if (currentCell != null) {
            result.append("\n").append(currentCell.triggerEvent(player, mother));
        }

        setMessage(result.toString());
        updateDisplay();

        if (player.getX() == mother.getX() && player.getY() == mother.getY()) {
            showWinDialog();
            return;
        }

        if (player.getLives() <= 0) {
            showLoseDialog();
        }
    }

    /**
     * Displays the welcome message at the start of the game.
     */
    private void showWelcomeMessage() {
        String message = "Welcome to The Flow!\n"
                + "You are a little black cat trying to find your mother after a flood.\n"
                + "You can sense that your mother is to the northeast, across the river.\n"
                + "Find the raft, cross the water, and survive the journey.";
        setMessage(message);
    }

    /**
     * Restarts the game from the beginning.
     */
    private void restartGame() {
        initializeGameState();
        updateDisplay();
        showWelcomeMessage();
    }

    /**
     * Sets the message text in the message area.
     *
     * @param message the message to show
     */
    private void setMessage(String message) {
        messageArea.setText(message);
        messageArea.setCaretPosition(0);
    }

    /**
     * Shows the win dialog and allows the player to restart or end.
     */
    private void showWinDialog() {
        String endingType;

        if (player.getSteps() <= 16) {
            endingType = "Best Ending: You found your mother quickly and safely.";
        } else if (player.getLives() <= 3) {
            endingType = "Survival Ending: You barely made it through the flood, but you are finally safe.";
        } else {
            endingType = "Safe Ending: After a long journey, you finally reunited with your mother.";
        }

        String message = "You found your mother!\n"
                + mother.speak(player.getSteps()) + "\n\n"
                + endingType + "\n"
                + "You win!";

        int choice = JOptionPane.showOptionDialog(
                this,
                message,
                "Victory",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                new String[]{"RESTART", "END"},
                "RESTART"
        );

        if (choice == JOptionPane.YES_OPTION) {
            restartGame();
        } else {
            dispose();
        }
    }

    /**
     * Shows the lose dialog and allows the player to restart or end.
     */
    private void showLoseDialog() {
        int choice = JOptionPane.showOptionDialog(
                this,
                "You lost all your lives.\nGame over.",
                "Game Over",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                new String[]{"RESTART", "END"},
                "RESTART"
        );

        if (choice == JOptionPane.YES_OPTION) {
            restartGame();
        } else {
            dispose();
        }
    }

    /**
     * Starts the graphical version of the game.
     *
     * @param args command-line arguments, not used
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameUI::new);
    }
}