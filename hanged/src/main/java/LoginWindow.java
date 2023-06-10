import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class LoginWindow {

    private Stage primaryStage;

    public LoginWindow(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void show() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));

        Label titleLabel = new Label("Hanged Game");
        titleLabel.setFont(Font.font(20));
        titleLabel.setTextAlignment(TextAlignment.CENTER);

        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            if (authenticateUser(username, password)) {
                HangmanGame game = new HangmanGame();
                game.start(primaryStage);
            } else {
                showError("Invalid credentials. Please try again.");
            }
        });

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(titleLabel, usernameLabel, usernameField, passwordLabel, passwordField, loginButton);
        vbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vbox, 300, 200);
        primaryStage.setTitle("Hanged Game - Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showError(String message) {
        // Show error message to the user
        System.out.println("Error: " + message);
    }

    private boolean authenticateUser(String username, String password) {
        // Implement your authentication logic here
        // Return true if the credentials are valid, false otherwise
        return username.equals("admin") && password.equals("password");
    }
}

