import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Hang extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Text text = new Text(0, 20, "Welcome to the Hangman Game!");
        text.setWrappingWidth(300);
        text.setTextAlignment(TextAlignment.CENTER);
        Group group = new Group(text);
        Scene scene = new Scene(group, 300, 50);
        primaryStage.setTitle("Hangman Game");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    public static void main(String[] args) {
        Application.launch();
    } 
}
