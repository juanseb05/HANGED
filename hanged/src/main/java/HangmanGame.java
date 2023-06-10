import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HangmanGame extends Application {

    private int maxGuesses = 6;
    private String secretWord = "hangman";
    private StringBuilder guessedWordBuilder;
    private int incorrectGuesses = 0;
    private int hangmanParts = 0;

    private Label wordLabel;
    private Label guessesLabel;
    private TextField guessField;
    private Button guessButton;

    @Override
    public void start(Stage primaryStage) {
        guessedWordBuilder = new StringBuilder(secretWord.length());
        guessedWordBuilder.setLength(secretWord.length());
        guessedWordBuilder.setCharAt(0, secretWord.charAt(0)); // Mostrar la primera letra de la palabra secreta

        wordLabel = new Label();
        guessesLabel = new Label();

        guessField = new TextField();
        guessButton = new Button("Guess");
        guessButton.setOnAction(e -> handleGuess());

        HBox inputBox = new HBox(guessField, guessButton);
        inputBox.setSpacing(10);
        inputBox.setAlignment(Pos.CENTER);

        VBox root = new VBox(wordLabel, guessesLabel, inputBox);
        root.setSpacing(20);
        root.setAlignment(Pos.CENTER);

        updateWordLabel();
        updateGuessesLabel();

        Scene scene = new Scene(root, 400, 200);
        primaryStage.setTitle("Hangman Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleGuess() {
        String guess = guessField.getText().toLowerCase();
        guessField.clear();

        if (guess.length() != 1) {
            showAlert("Invalid guess! Please enter a single letter.");
            return;
        }

        if (guessedWordBuilder.toString().contains(guess)) {
            showAlert("You already guessed that letter!");
            return;
        }

        boolean correctGuess = false;
        for (int i = 0; i < secretWord.length(); i++) {
            if (secretWord.charAt(i) == guess.charAt(0)) {
                guessedWordBuilder.setCharAt(i, guess.charAt(0));
                correctGuess = true;
            }
        }

        if (correctGuess) {
            updateWordLabel();

            if (guessedWordBuilder.toString().equals(secretWord)) {
                // Player wins
                showAlert("Congratulations! You guessed the word: " + secretWord);
                resetGame();
            }
        } else {
            incorrectGuesses++;
            hangmanParts++;
            updateWordLabel();
            updateGuessesLabel();
            drawHangman();

            if (incorrectGuesses >= maxGuesses) {
                // Player loses
                showAlert("Game Over! The word was: " + secretWord);
                resetGame();
            }
        }
    }

    private void updateWordLabel() {
        StringBuilder displayWordBuilder = new StringBuilder(guessedWordBuilder.length() * 2);
        for (int i = 0; i < guessedWordBuilder.length(); i++) {
            char c = guessedWordBuilder.charAt(i);
            if (c == '\0') {
                displayWordBuilder.append("_ ");
            } else {
                displayWordBuilder.append(c).append(" ");
            }
        }
        wordLabel.setText("Word: " + displayWordBuilder.toString());
    }

    private void updateGuessesLabel() {
        guessesLabel.setText("Incorrect Guesses: " + incorrectGuesses + " / " + maxGuesses);
    }

    private void drawHangman() {
        // Draw different parts of the hangman based on the number of incorrect guesses
        StringBuilder hangmanBuilder = new StringBuilder();

        hangmanBuilder.append("   ____\n");
        hangmanBuilder.append("  |    |\n");

        switch (hangmanParts) {
            case 1:
                hangmanBuilder.append("  |    O\n");
                break;
            case 2:
                hangmanBuilder.append("  |    O\n");
                hangmanBuilder.append("  |    |\n");
                break;
            case 3:
                hangmanBuilder.append("  |    O\n");
                hangmanBuilder.append("  |   /|\n");
                break;
            case 4:
                hangmanBuilder.append("  |    O\n");
                hangmanBuilder.append("  |   /|\\\n");
                break;
            case 5:
                hangmanBuilder.append("  |    O\n");
                hangmanBuilder.append("  |   /|\\\n");
                hangmanBuilder.append("  |   /\n");
                break;
            case 6:
                hangmanBuilder.append("  |    O\n");
                hangmanBuilder.append("  |   /|\\\n");
                hangmanBuilder.append("  |   / \\\n");
                break;
        }

        hangmanBuilder.append("  |\n");
        hangmanBuilder.append("_______\n");

        System.out.println(hangmanBuilder.toString());
    }

    private void resetGame() {
        guessedWordBuilder.setLength(secretWord.length());
        guessedWordBuilder.setCharAt(0, secretWord.charAt(0));
        incorrectGuesses = 0;
        hangmanParts = 0;
        updateWordLabel();
        updateGuessesLabel();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Hangman Game");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
