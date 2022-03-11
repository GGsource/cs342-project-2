import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Project2Keno extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Welcome to JavaFX");
		
		//A menubar holds a series of menus
		//A menu holds a series of menuitems
		//A menuitem functions as a button and can have an event handler
		MenuItem rulesMenuItem = new MenuItem("Rules");
		//TODO: Implement action for rules menu item being clicked
		rulesMenuItem.setOnAction(e->System.out.println("We have yet to implement rules menu item functionality!"));
		MenuItem oddsMenuItem = new MenuItem("Odds");
		//TODO: Implement action for odds menu item being clicked
		oddsMenuItem.setOnAction(e->System.out.println("We have yet to implement odds menu item functionality!"));
		
		Menu mainMenu = new Menu("Menu");
		mainMenu.getItems().addAll(rulesMenuItem, oddsMenuItem); //Add items to menu
		MenuBar welcomeMenuBar = new MenuBar(mainMenu);

		//Add the main logo to the welcome screen
		Image kenoImage = new Image("/src/main/resources/Keno_Logo.png", true);
		
		
		
				
		Scene scene = new Scene(welcomeMenuBar, 700,700);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
