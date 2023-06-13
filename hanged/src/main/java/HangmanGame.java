import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HangmanGame extends Application {
    private static final String SECRET_WORD = "HOME";
    private static final int MAX_ERRORS = 6;

    private int incorrectGuesses;
    private String guessedWord;

    private Text guessedWordText;
    private TextField letterInput;
    private Button guessButton;
    private Group hangmanContainer;

    @Override
    public void start(Stage primaryStage) {
        incorrectGuesses = 0;
        guessedWord = "";

        // Hangman visual
        hangmanContainer = new Group();

        // Guessed word text
        guessedWordText = new Text();
        guessedWordText.setStyle("-fx-font-size: 20;");

        // Letter input field
        letterInput = new TextField();
        letterInput.setPrefWidth(40);

        // Guess button
        guessButton = new Button("Guess");
        guessButton.setOnAction(e -> handleGuess());

        // Layout
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.getChildren().addAll(hangmanContainer, guessedWordText, createInputPane());

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Hangman Game");
        primaryStage.setScene(scene);
        primaryStage.show();

        initializeGuessedWord();
        updateGuessedWordText();
    }

    private void handleGuess() {
        String letter = letterInput.getText().toUpperCase();
        letterInput.clear();

        if (letter.length() == 1 && Character.isLetter(letter.charAt(0))) {
            if (guessedWord.contains(letter)) {
                // Letter already guessed
                return;
            }

            boolean correctGuess = false;

            for (int i = 0; i < SECRET_WORD.length(); i++) {
                if (SECRET_WORD.charAt(i) == letter.charAt(0)) {
                    StringBuilder sb = new StringBuilder(guessedWord);
                    sb.setCharAt(i, letter.charAt(0));
                    guessedWord = sb.toString();
                    correctGuess = true;
                }
            }

            if (!correctGuess) {
                incorrectGuesses++;
                drawHangman();
            }

            updateGuessedWordText();
            checkGameStatus();
        }
    }

    private void initializeGuessedWord() {
        guessedWord = "";
        for (int i = 0; i < SECRET_WORD.length(); i++) {
            if (Character.isLetter(SECRET_WORD.charAt(i))) {
                guessedWord += "_";
            } else {
                guessedWord += SECRET_WORD.charAt(i);
            }
        }
    }

    private void updateGuessedWordText() {
        StringBuilder sb = new StringBuilder();

        for (char c : guessedWord.toCharArray()) {
            sb.append(c).append(" ");
        }

        guessedWordText.setText(sb.toString());
    }

    private void checkGameStatus() {
        if (guessedWord.equals(SECRET_WORD)) {
            showMessage("Congratulations! You guessed the word!");
            disableInput();
        } else if (incorrectGuesses == MAX_ERRORS) {
            showMessage("Game Over! You reached the maximum number of incorrect guesses.");
            disableInput();
        }
    }

    private void showMessage(String message) {
        Text messageText = new Text(message);
        VBox root = (VBox) guessedWordText.getParent();
        root.getChildren().add(messageText);
    }

    private void disableInput() {
        letterInput.setDisable(true);
        guessButton.setDisable(true);
    }

    private void drawHangman() {
        hangmanContainer.getChildren().clear();

        if (incorrectGuesses >= 1) {
            Circle head = new Circle(20);
            head.setTranslateY(40);
            hangmanContainer.getChildren().add(head);
        }

        if (incorrectGuesses >= 2) {
            Line body = new Line();
            body.setStartX(0);
            body.setStartY(60);
            body.setEndX(0);
            body.setEndY(140);
            hangmanContainer.getChildren().add(body);
        }

        if (incorrectGuesses >= 3) {
            Line leftArm = new Line();
            leftArm.setStartX(-20);
            leftArm.setStartY(80);
            leftArm.setEndX(-60);
            leftArm.setEndY(100);
            hangmanContainer.getChildren().add(leftArm);
        }

        if (incorrectGuesses >= 4) {
            Line rightArm = new Line();
            rightArm.setStartX(20);
            rightArm.setStartY(80);
            rightArm.setEndX(60);
            rightArm.setEndY(100);
            hangmanContainer.getChildren().add(rightArm);
        }

        if (incorrectGuesses >= 5) {
            Line leftLeg = new Line();
            leftLeg.setStartX(-20);
            leftLeg.setStartY(160);
            leftLeg.setEndX(-60);
            leftLeg.setEndY(200);
            hangmanContainer.getChildren().add(leftLeg);
        }

        if (incorrectGuesses >= 6) {
            Line rightLeg = new Line();
            rightLeg.setStartX(20);
            rightLeg.setStartY(160);
            rightLeg.setEndX(60);
            rightLeg.setEndY(200);
            hangmanContainer.getChildren().add(rightLeg);
        }
    }

    private GridPane createInputPane() {
        GridPane inputPane = new GridPane();
        inputPane.setAlignment(Pos.CENTER);
        inputPane.setHgap(10);
        inputPane.setVgap(5);

        Label letterLabel = new Label("Enter a letter:");
        inputPane.add(letterLabel, 0, 0);
        inputPane.add(letterInput, 1, 0);
        inputPane.add(guessButton, 2, 0);

        return inputPane;
    }

    public static void main(String[] args) {
        launch(args);
    }
}






