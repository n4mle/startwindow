import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartWindow extends JFrame {
    public StartWindow() {
        setTitle("Game Introduction");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            UIManager.put("nimbusBase", new Color(0, 102, 204)); // Set base color
            UIManager.put("nimbusBlueGrey", new Color(51, 153, 255)); // Set blue-grey color
            UIManager.put("control", new Color(204, 229, 255)); // Set control color
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        JButton instructionsButton = new JButton("Instructions");
        JButton playButton = new JButton("Play");
        JButton quitButton = new JButton("Quit");

        instructionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InstructionsWindow instructionsWindow = new InstructionsWindow();
                instructionsWindow.setVisible(true);
            }
        });

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameWindow gameWindow = new GameWindow();
                gameWindow.setVisible(true);
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the start window
            }
        });

        JPanel panel = new JPanel(new GridLayout(3, 1));
        panel.add(instructionsButton);
        panel.add(playButton);
        panel.add(quitButton);

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                StartWindow startWindow = new StartWindow();
                startWindow.setVisible(true);
            }
        });
    }
}

class InstructionsWindow extends JFrame {
    public InstructionsWindow() {
        setTitle("Game Instructions");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        JButton backButton = new JButton("Back");

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the instructions window
            }
        });

        JPanel panel = new JPanel();
        panel.add(new JLabel("In this game, you will guess a number between 1 and 100."));
        panel.add(backButton);

        add(panel);
    }
}

class GameWindow extends JFrame {
    private int randomNumber;
    private JTextField guessField;
    private JLabel resultLabel;

    public GameWindow() {
        setTitle("Guessing Game");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        randomNumber = generateRandomNumber();

        guessField = new JTextField(10);
        JButton checkButton = new JButton("Check");

        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });

        resultLabel = new JLabel();

        JPanel panel = new JPanel();
        panel.add(new JLabel("Guess a number between 1 and 100:"));
        panel.add(guessField);
        panel.add(checkButton);
        panel.add(resultLabel);

        add(panel);
    }

    private int generateRandomNumber() {
        return (int) (Math.random() * 100) + 1;
    }

    private void checkGuess() {
        try {
            int guess = Integer.parseInt(guessField.getText());

            if (guess < randomNumber) {
                resultLabel.setText("Too low!");
            } else if (guess > randomNumber) {
                resultLabel.setText("Too high!");
            } else {
                resultLabel.setText("You got it!");
            }
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input");
        }
    }
}
