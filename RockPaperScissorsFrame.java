import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.text.DefaultCaret;
import java.util.Random;

public class RockPaperScissorsFrame extends JFrame {
    private int playerWins = 0;
    private int computerWins = 0;
    private int ties = 0;

    private JTextArea gameResultsArea;
    private JLabel playerWinsLabel, computerWinsLabel, tiesLabel;
    private Random rand;
    private Strategy currentStrategy;

    public RockPaperScissorsFrame() {
        setTitle("Rock Paper Scissors Game");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        rand = new Random();

        // Create and set up the panels
        JPanel buttonPanel = createButtonPanel();
        JPanel statsPanel = createStatsPanel();
        JPanel resultsPanel = createResultsPanel();

        // Add panels to the frame
        add(buttonPanel, BorderLayout.NORTH);
        add(statsPanel, BorderLayout.CENTER);
        add(resultsPanel, BorderLayout.SOUTH);
    }

    // Panel for Rock, Paper, Scissors buttons
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.setLayout(new GridLayout(1, 4));

        JButton rockButton = new JButton("Rock");
        JButton paperButton = new JButton("Paper");
        JButton scissorsButton = new JButton("Scissors");
        JButton quitButton = new JButton("Quit");

        rockButton.addActionListener(e -> playGame("Rock"));
        paperButton.addActionListener(e -> playGame("Paper"));
        scissorsButton.addActionListener(e -> playGame("Scissors"));
        quitButton.addActionListener(e -> System.exit(0));

        panel.add(rockButton);
        panel.add(paperButton);
        panel.add(scissorsButton);
        panel.add(quitButton);

        return panel;
    }

    // Stats panel (Player Wins, Computer Wins, Ties)
    private JPanel createStatsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        playerWinsLabel = new JLabel("Player Wins: ");
        computerWinsLabel = new JLabel("Computer Wins: ");
        tiesLabel = new JLabel("Ties: ");

        JTextField playerWinsField = new JTextField(5);
        JTextField computerWinsField = new JTextField(5);
        JTextField tiesField = new JTextField(5);

        playerWinsField.setEditable(false);
        computerWinsField.setEditable(false);
        tiesField.setEditable(false);

        panel.add(playerWinsLabel);
        panel.add(playerWinsField);
        panel.add(computerWinsLabel);
        panel.add(computerWinsField);
        panel.add(tiesLabel);
        panel.add(tiesField);

        return panel;
    }

    // Results panel for displaying game history
    private JPanel createResultsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        gameResultsArea = new JTextArea(10, 30);
        gameResultsArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(gameResultsArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    // Play the game when a button is clicked
    private void playGame(String playerChoice) {
        String computerChoice = determineComputerChoice();
        String result = determineResult(playerChoice, computerChoice);

        updateStats(result);
        displayGameResult(result);

        // Add game result to history
        gameResultsArea.append(result + "\n");
    }

    // Determine the result of the round
    private String determineResult(String playerChoice, String computerChoice) {
        if (playerChoice.equals(computerChoice)) {
            return "Tie: " + playerChoice + " vs " + computerChoice;
        }

        boolean playerWins =
                (playerChoice.equals("Rock") && computerChoice.equals("Scissors")) ||
                        (playerChoice.equals("Paper") && computerChoice.equals("Rock")) ||
                        (playerChoice.equals("Scissors") && computerChoice.equals("Paper"));

        if (playerWins) {
            return playerChoice + " beats " + computerChoice + " (Player Wins)";
        } else {
            return computerChoice + " beats " + playerChoice + " (Computer Wins)";
        }
    }

    // Update the stats for Player, Computer, and Ties
    private void updateStats(String result) {
        if (result.contains("Player Wins")) {
            playerWins++;
        } else if (result.contains("Computer Wins")) {
            computerWins++;
        } else {
            ties++;
        }

        playerWinsLabel.setText("Player Wins: " + playerWins);
        computerWinsLabel.setText("Computer Wins: " + computerWins);
        tiesLabel.setText("Ties: " + ties);
    }

    // Display the result in the text area
    private void displayGameResult(String result) {
        DefaultCaret caret = (DefaultCaret) gameResultsArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        gameResultsArea.append(result + "\n");
    }

    // Randomly determine the computer's choice
    private String determineComputerChoice() {
        int choice = rand.nextInt(3);
        switch (choice) {
            case 0: return "Rock";
            case 1: return "Paper";
            case 2: return "Scissors";
            default: return "Rock";
        }
    }

    // Start the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RockPaperScissorsFrame frame = new RockPaperScissorsFrame();
            frame.setVisible(true);
        });
    }
}
