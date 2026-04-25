import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Akaei extends Application {

    @Override
    public void start(Stage stage) {
        // Create WebView and engine
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        // URL bar
        TextField urlField = new TextField("https://www.priv.au);

        urlField.setOnAction(e -> {
            String url = urlField.getText();
            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                url = "https://" + url;
            }
            webEngine.load(url);
        });

        // Layout
        BorderPane root = new BorderPane();
        root.setTop(urlField);
        root.setCenter(webView);

        // Load default page
        webEngine.load(urlField.getText());

        
        Scene scene = new Scene(root, 1000, 700);
        stage.setTitle("Test complete");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
