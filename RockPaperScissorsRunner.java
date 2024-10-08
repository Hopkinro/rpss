import javax.swing.SwingUtilities;

public class RockPaperScissorsRunner {
    public static void main(String[] args) {
        // Launch the GUI in the Swing event dispatch thread
        SwingUtilities.invokeLater(() -> {
            RockPaperScissorsFrame frame = new RockPaperScissorsFrame();
            frame.setVisible(true);  // Make the frame visible
        });
    }
}

