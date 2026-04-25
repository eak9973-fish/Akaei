import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Akaei extends Application {

    @Override
    public void start(Stage stage) {

        TabPane tabPane = new TabPane();


        tabPane.getTabs().add(createNewTab("https://www.google.com"));

        Button newTabButton = new Button("+");
        newTabButton.setOnAction(e -> {
            Tab newTab = createNewTab("https://www.google.com");
            tabPane.getTabs().add(newTab);
            tabPane.getSelectionModel().select(newTab);
        });

        BorderPane root = new BorderPane();
        root.setTop(newTabButton);
        root.setCenter(tabPane);

        Scene scene = new Scene(root, 1000, 700);
        stage.setTitle("Akaei");
        stage.setScene(scene);
        stage.show();
    }

    private Tab createNewTab(String defaultUrl) {
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        TextField urlField = new TextField(defaultUrl);

        urlField.setOnAction(e -> {
            String url = urlField.getText();
            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                url = "https://" + url;
            }
            webEngine.load(url);
        });

        BorderPane layout = new BorderPane();
        layout.setTop(urlField);
        layout.setCenter(webView);

        webEngine.load(defaultUrl);

        Tab tab = new Tab("New Tab");
        tab.setContent(layout);
        tab.setClosable(true);

      
        webEngine.titleProperty().addListener((obs, oldTitle, newTitle) -> {
            tab.setText(newTitle);
        });

        return tab;
    }

    public static void main(String[] args) {
        launch();
    }
}
