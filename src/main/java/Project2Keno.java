import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Project2Keno extends Application {
	//Scenes declared globally so theyre accessible in functions
	private Scene prevScene = null;
	private Scene welcomeScene = null;
	private Scene rulesScene = null;
	private Scene oddsScene = null;
	private Scene gameplayScene = null;

	private int spotCount;
	private Button chosenSpotButton = null;
	private int drawCount;
	
	private void initializeScenes(Stage givenStage) {
		welcomeScene = createWelcomeScene(givenStage);
		rulesScene = createRuleScene(givenStage);
		oddsScene = createOddScene(givenStage);
		gameplayScene = createGameplayScene(givenStage);
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Welcome to Keno");

		initializeScenes(primaryStage); //Initialize the scenes to use

		primaryStage.setScene(welcomeScene); ////Set the scene to welcome
		primaryStage.show(); //Display on screen
	}

	private Scene createWelcomeScene(Stage givenStage) {
		//Use a Vbox to put all our items on screen
		VBox welcomeVBox = new VBox();
		//Our first scene, the welcome screen
		Scene welcomeScene = new Scene(welcomeVBox, 700,700);
		
		//Add a menu bar at the top for the rules and odds
		Menu rulesAndOddsMenu = createRulesAndOddsMenu(givenStage);
		MenuBar welcomeMenuBar = new MenuBar(rulesAndOddsMenu);

		//Add the main logo to the welcome screen
		Image kenoImage = new Image("file:./src/main/resources/Keno_Logo.png", true); //Create image object from png file
		ImageView kenoImageView = new ImageView(kenoImage);

		//Add Button to begin the game
		Button playButton = new Button("Play");
		playButton.setOnAction(e->givenStage.setScene(gameplayScene));

		//Add everything to our vbox to show up on welcome screen
		welcomeVBox.getChildren().addAll(welcomeMenuBar, kenoImageView, playButton);
		
		return welcomeScene; //Send welcome scene
	}

	private Scene createRuleScene(Stage primaryStage) {
		//Rules Scene - Needs Title, Label, and a return button
		ImageView rulesImageView = new ImageView(new Image("file:./src/main/resources/rules_title.png", true));
		//FIXME: Add the actual rules
		Label rulesLabel = new Label("rule 1...\n rule 2...");
		Button returnButton = new Button("Return"); //Should return you to previous scene
		returnButton.setOnAction(e->primaryStage.setScene(prevScene));
		VBox rulesVBox = new VBox(rulesImageView, rulesLabel, returnButton);
		Scene rulesScene = new Scene(rulesVBox, 700, 700);
		return rulesScene; //Send rules scene
	}
	private Scene createOddScene(Stage primaryStage) {
		//Odds Scene - Needs Title, Label, and a return button
		ImageView oddsImageView = new ImageView(new Image("file:./src/main/resources/odds_title.png", true));
		//FIXME: Add the actual odds
		Label oddsLabel = new Label("odds 1...\n odds 2...");
		Button returnButton = new Button("Return"); //Should return you to previous scene
		returnButton.setOnAction(e->primaryStage.setScene(prevScene));
		VBox oddsVBox = new VBox(oddsImageView, oddsLabel, returnButton);
		Scene oddsScene = new Scene(oddsVBox, 700, 700);
		return oddsScene; //Send odds scene
	}

	private Scene createGameplayScene(Stage givenStage) {
		//Create a menu for changing the app's appearance
		Label newLookLabel = new Label("New Look");
		newLookLabel.setOnMouseClicked(e->pickNewLook());
		Menu newLookMenu = new Menu();
		newLookMenu.setGraphic(newLookLabel);
		//NewLookMenu being pressed, even without mouse, will now trigger new look
		newLookMenu.setOnAction(e->newLookLabel.fireEvent(new MouseEvent(MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true, true, true, true, true, true, true, true, true, null)));
		//Toolbar with previous Menu and new look menu
		Menu rulesAndOddsMenu = createRulesAndOddsMenu(givenStage);
		MenuBar gameplayMenuBar = new MenuBar(rulesAndOddsMenu, newLookMenu);
		//Image - Lets the user see they are now on the bet card
		ImageView gameplayImageView = new ImageView(new Image("file:./src/main/resources/gameplay_title.png", true));
		
		//Spots Label - Tells user to pick how many spots to play
		Label spotsLabel = new Label("Pick how many Spots you would like to play! ");
		//HBox Will hold buttons horizontally
		HBox spotsButtonsHBox = new HBox();
		//Spots Buttons - Put in a list since they all do pretty much the same thing
		Button spotButtonList[] = {new Button("1"), new Button("4"), new Button("8"), new Button("10")};
		for (Button spotButton : spotButtonList) {
			spotsButtonsHBox.getChildren().add(spotButton); //Add the button to HBox
			spotButton.setStyle("-fx-background-color: #e87564;");
			//Pressing these buttons sets spotCount to the intended Number
			spotButton.setOnAction(e->{
				if (chosenSpotButton != spotButton) { //No need to repeatedly set if it's already set
					//Make the pressed button stand out
					spotButton.setStyle("-fx-background-color: #e24028;");
					//make sure any previous pressed are returned to normal
					if (chosenSpotButton != null)
						chosenSpotButton.setStyle("-fx-background-color: #e87564;");
					chosenSpotButton = spotButton; //update chosen spot button
					spotCount = Integer.parseInt(spotButton.getText());
					System.out.println("Spot button was pressed! spotCount is now " + spotCount);
				}
			});
		}

		//Drawings Label
		Label drawLabel = new Label("Pick how many Drawings you would like to play!");
		//Drawings Buttons
		Button drawAmountOne = new Button("1");
		Button drawAmountTwo = new Button("2");
		Button drawAmountThree = new Button("3");
		Button drawAmountFour = new Button("4");
		//FIXME: Draw amount buttons currently do nothing
		HBox drawButtonsHBox = new HBox(drawAmountOne, drawAmountTwo, drawAmountThree, drawAmountFour);

		//Gridpane w/ Buttons
		GridPane betCardGridPane = new GridPane();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 10; j++ ) {
				betCardGridPane.add(new Button(""+((i*10)+j+1)), j, i);
				//FIXME: Grid buttons currently do nothing
			}
		}
		betCardGridPane.setDisable(true); //Disable at first until spot count is chosen
		//Pick for Me Button
		Button pickForMeButton = new Button("Pick For Me!");
		pickForMeButton.setOnAction(e->randomizeBetCard());
		pickForMeButton.setDisable(true); //Disable until spot count is chosen
		//Begin Draw Button
		Button beginDrawButton = new Button("Begin Draw");
		//TODO: Implement Drawing Process
		beginDrawButton.setOnAction(e->System.out.println("Begin draw was pressed but not yet implemented..."));
		beginDrawButton.setDisable(true); //Disable until spotcount and drawcount are chosen

		VBox gameplayVBox = new VBox(gameplayMenuBar, gameplayImageView, spotsLabel, spotsButtonsHBox, drawLabel, drawButtonsHBox, betCardGridPane, pickForMeButton, beginDrawButton);
		Scene gameplayScene = new Scene(gameplayVBox, 700, 700);
		return gameplayScene;
	}

	private Menu createRulesAndOddsMenu(Stage givenStage) {
		//A menu item is clickable like a button

		MenuItem rulesMenuItem = new MenuItem("Rules");
		//Pressing the rules menu button takes us to the rules Scene
		rulesMenuItem.setOnAction(e->{
			prevScene = givenStage.getScene(); //Save current scene to return to
			givenStage.setScene(rulesScene); //Go to Rules Scene
		});

		MenuItem oddsMenuItem = new MenuItem("Odds");
		//Pressing the odds menu button takes us to the odds Scene
		oddsMenuItem.setOnAction(e->{
			prevScene = givenStage.getScene(); //Save current scene to return to
			givenStage.setScene(oddsScene); //Go to Odds Scene
		});

		//Menu is composed of menu items
		Menu rulesAndOddsMenu = new Menu("Menu");
		rulesAndOddsMenu.getItems().addAll(rulesMenuItem, oddsMenuItem); //Add items to menu

		return rulesAndOddsMenu; //send the menu back
	}

	private void pickNewLook() {
		//TODO: Implement New Look Menu Option
		System.out.println("User wants to change the look of the application but this has not yet been implemented...");
	}

	private void randomizeBetCard() {
		//TODO: Implement Randomized selection of bet card
		System.out.println("Randomize Bet Card was chosen but is not implemented yet...");
	}

}
