
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

/*
Geo Gonzalez
UIN: 657437605
NetID: ggonza55@uic.edu

CS 342 Project 2 - Keno
Sorry about turning this in late, when you said we had 1 week for the wireframe
and two weeks for the rest of the project I thought you meant 2 weeks after we
turned in the wireframe. So I had to spend the last 2 days speeding through
this from halfway done to complete.
*/

public class Project2Keno extends Application {
	//Global Scenes declared so theyre accessible in all functions
	private Scene prevScene = null;
	private Scene welcomeScene = null;
	private Scene rulesScene = null;
	private Scene oddsScene = null;
	private Scene gameplayScene = null;
	private Scene drawingScene = null;

	//Global Variables
	private int chosenSpotCount; //Amount of spots user said they want
	private int currentSpotCount;//Amount of spots user has chosen so far
	private ToggleButton chosenSpotButton = null; //Keep track of which spot button is chosen
	private int chosenDrawCount;
	private int currentDrawCount = 0;
	private ToggleButton chosenDrawButton = null;
	private HashSet<ToggleButton> spotsSet = new HashSet<>();
	private Button beginDrawButton = new Button("Begin Draw");
	private Button nextdrawingButton = new Button("Next Drawing");
	private ArrayList<Integer> drawTimeList = new ArrayList<>();
	private ArrayList<Integer> matchedList = new ArrayList<>();
	private int totalWon = 0;
	private GridPane betCardGridPane = new GridPane(); //Grid for betcard buttons
	private boolean isNewDrawingSession = false;

	//Label showing which draw this is out of how many
	private Label drawRoundLabel = new Label("Drawings will begin shortly...");
	//Label showing current random number
	private Label currentNumLabel = new Label("beginning roll...");
	//Label reminding you of spots you chose
	private Label chosenSpotsLabel = new Label("Your chosen numbers were: ");
	//Label for all numbers as theyre being shown
	private Label allNumsLabel = new Label("Rolled thus far: ");
	//Label for matched numbers
	private Label matchedNumsLabel = new Label(" Matches: ");
	//Label for how much has been won this drawing
	private Label wonThisRoundLabel = new Label("Won this round: Wait til end of drawing...");
	// private PauseTransitionList pauseTransitionList;
	Duration d = Duration.seconds(1); //Change value to change speed of animation
	private PauseTransition pauseList[] = {
		new PauseTransition(d), new PauseTransition(d), new PauseTransition(d), new PauseTransition(d), new PauseTransition(d),
		new PauseTransition(d), new PauseTransition(d), new PauseTransition(d), new PauseTransition(d), new PauseTransition(d),
		new PauseTransition(d), new PauseTransition(d), new PauseTransition(d), new PauseTransition(d), new PauseTransition(d),
		new PauseTransition(d), new PauseTransition(d), new PauseTransition(d), new PauseTransition(d), new PauseTransition(d),};
	
	private void initializeScenes(Stage givenStage) {
		welcomeScene = createWelcomeScene(givenStage);
		rulesScene = createRuleScene(givenStage);
		oddsScene = createOddScene(givenStage);
		gameplayScene = createGameplayScene(givenStage);
		drawingScene = createDrawingScene(givenStage);

		//So much wasted time implementing this only to remove it

		// PauseTransitionList.PauseTransitionNode ptNode = pauseTransitionList.new PauseTransitionNode(new PauseTransition(Duration.seconds(2)));
		// pauseTransitionList = new PauseTransitionList(ptNode);
		// int i = 1;
		// while (i < 19) {
		// 	pauseTransitionList.head.add(new PauseTransition(Duration.seconds(2)));
		// }
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
		welcomeMenuBar.setStyle("-fx-background-color: #FF4C69;");

		//Add the main logo to the welcome screen
		Image kenoImage = new Image("file:./src/main/resources/Keno_Logo.png", true); //Create image object from png file
		ImageView kenoImageView = new ImageView(kenoImage);

		//Add Button to begin the game
		Button playButton = new Button("Play");
		playButton.setStyle("-fx-background-color: #FF4C69; -fx-text-fill: #FFC4CE;");
		playButton.setPadding(new Insets(20,50,20,50));
		playButton.setFont(Font.loadFont("file:src/main/resources/p5hatty.ttf", 55));
		playButton.setOnAction(e->givenStage.setScene(gameplayScene));

		//Add everything to our vbox to show up on welcome screen
		VBox bottomStuff = new VBox(kenoImageView, playButton);
		bottomStuff.setAlignment(Pos.CENTER);
		welcomeVBox.getChildren().addAll(welcomeMenuBar, bottomStuff);
		//welcomeVBox.setPadding(new Insets(30, 30, 30, 30));
		welcomeVBox.setSpacing(10);
		welcomeVBox.setStyle("-fx-background-color: #FFC4CE; -fx-border-color: #FF4C69; -fx-border-width: 8;");
		return welcomeScene; //Send welcome scene
	}

	private Scene createRuleScene(Stage primaryStage) {
		//Rules Scene - Needs Title, Label, and a return button
		ImageView rulesImageView = new ImageView(new Image("file:./src/main/resources/rules_title.png", true));
		//DONE: Add the actual rules
		Label rulesLabel = new Label("Keno is a fast-paced lottery draw-style game that's easy to\n\nplay, with a chance to win great cash prizes every 4 minutes. For\n\neach Keno drawing, 20 numbers out of 80 will be selected as winning\n\nnumbers. You can decide how many of these numbers (called Spots)\n\nand exactly which numbers you will try to match.");
		rulesLabel.setTextAlignment(TextAlignment.CENTER);
		rulesLabel.setStyle("-fx-text-fill: #C78914;");
		rulesLabel.setFont(Font.loadFont("file:src/main/resources/p5hatty.ttf", 25));
		Button returnButton = new Button("Return"); //Should return you to previous scene
		returnButton.setOnAction(e->primaryStage.setScene(prevScene));
		returnButton.setStyle("-fx-background-color: #C78914; -fx-text-fill: #FFEBC4");
		returnButton.setPadding(new Insets(10,30,10,30));
		returnButton.setFont(Font.loadFont("file:src/main/resources/p5hatty.ttf", 30));
		VBox rulesVBox = new VBox(rulesImageView, rulesLabel, returnButton);
		rulesVBox.setAlignment(Pos.TOP_CENTER);
		rulesVBox.setSpacing(10);
		rulesVBox.setPadding(new Insets(20,0,0,0));
		rulesVBox.setStyle("-fx-background-color: #FFEBC4; -fx-border-color: #C78914; -fx-border-width: 8;");
		Scene rulesScene = new Scene(rulesVBox, 700, 400);
		return rulesScene; //Send rules scene
	}
	private Scene createOddScene(Stage primaryStage) {
		//Odds Scene - Needs Title, Label, and a return button
		ImageView oddsImageView = new ImageView(new Image("file:./src/main/resources/odds_title.png", true));
		//DONE: Add the actual odds
		ImageView odds_winnings = new ImageView(new Image("file:./src/main/resources/Winnings_Odds.png", true));
		Button returnButton = new Button("Return"); //Should return you to previous scene
		returnButton.setOnAction(e->primaryStage.setScene(prevScene));
		returnButton.setStyle("-fx-background-color: #AC71B6; -fx-text-fill: #F7C4FF;");
		returnButton.setPadding(new Insets(10,30,10,30));
		returnButton.setFont(Font.loadFont("file:src/main/resources/p5hatty.ttf", 30));
		VBox oddsVBox = new VBox(oddsImageView, odds_winnings, returnButton);
		oddsVBox.setAlignment(Pos.CENTER);
		oddsVBox.setStyle("-fx-background-color: #F7C4FF; -fx-border-color: #AC71B6; -fx-border-width: 8;");
		oddsVBox.setSpacing(10);
		Scene oddsScene = new Scene(oddsVBox, 300, 800);
		return oddsScene; //Send odds scene
	}

	private Scene createGameplayScene(Stage givenStage) {
		//Create a menu for changing the app's appearance
		Label newLookLabel = new Label("New Look");
		Menu newLookMenu = new Menu();
		newLookMenu.setGraphic(newLookLabel);
		//NewLookMenu being pressed, even without mouse, will now trigger new look
		newLookMenu.setOnAction(e->newLookLabel.fireEvent(new MouseEvent(MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true, true, true, true, true, true, true, true, true, null)));
		//Toolbar with previous Menu and new look menu
		Menu rulesAndOddsMenu = createRulesAndOddsMenu(givenStage);
		MenuBar gameplayMenuBar = new MenuBar(rulesAndOddsMenu, newLookMenu);
		gameplayMenuBar.setStyle("-fx-background-color: #4D8BFF;");
		//Image - Lets the user see they are now on the bet card
		ImageView gameplayImageView = new ImageView(new Image("file:./src/main/resources/gameplay_title.png", true));
 
		//Populate GridPane with buttons
		betCardGridPane.setAlignment(Pos.CENTER);
		betCardGridPane.setHgap(3);
		betCardGridPane.setVgap(3);
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 10; j++ ) {
				int currentNum = (i*10)+j+1; //This is the correct number displayed on button
				String numPadding = "";
				if (currentNum < 10)
					numPadding = "0";
				ToggleButton gridButton = new ToggleButton(""+ numPadding + currentNum);
				//Color gridbutton
				gridButton.setStyle("-fx-background-color: " + ToggleButton.normalColor);
				gridButton.setPadding(new Insets(10, 20, 10, 20));
				//Give the buttons behavior on press
				gridButton.setOnAction(e->{
					//If we already have enough spots and a new button is pressed, it should be
					//Ignored unless it is one of the already chosen buttons, in which case it
					//will be unchosen.
					if ((currentSpotCount < chosenSpotCount) || spotsSet.contains(gridButton)) {
						if (gridButton.isSelected()) { //User pressed it again so they must want to unpick this number
							//set it back to unpressed
							gridButton.setSelected(false);
							//Set color back to normal
							gridButton.setStyle("-fx-background-color: " + ToggleButton.normalColor);
							//decrement amount of buttons selected by 1
							currentSpotCount -= 1;
							//Remove from list of chosen numbers
							spotsSet.remove(gridButton);
							//DEBUGGING: Line to be disabled later
							//System.out.println("Currently Chosen Spots: " + spotsSet);

							//number of buttons selected is now LESS than chosenSpotCount, reenable all buttons
							// for (Node gButton : betCardGridPane.getChildren()) {
							// 	ToggleButton castedBtn = (ToggleButton) gButton;
							// 	castedBtn.setDisable(false);
							// }
						}
						else {
							gridButton.setSelected(true);//Set it to pressed so we can toggle
							//Change color to stand out
							gridButton.setStyle("-fx-background-color: " + ToggleButton.highlightedColor);
							//Increment amount of buttons selected by 1
							currentSpotCount += 1;
							//Add to list of chosen numbers
							spotsSet.add(gridButton);
							//DEBUGGING: line to be disabled later
							//System.out.println("Currently Chosen Spots: " + spotsSet);

							//If amount of buttons selected is now = to chosenSpotCount then disable all buttons OTHER than already chosen ones
							// if (currentSpotCount >= chosenSpotCount) {
							// 	for (Node gButton : betCardGridPane.getChildren()) {
							// 		ToggleButton castedBtn = (ToggleButton) gButton; //have to recast to access it
							// 		if (!spotsSet.contains(castedBtn)) { //If the button is not in our set
							// 			gButton.setDisable(true); //Disable it
							// 		}
							// 	}
							// }
						}
					}
					checkIfReadyToBegin(beginDrawButton);
				});
				betCardGridPane.add(gridButton, j, i);
			}
		}
		//Disable gridpane first until spot count is chosen
		betCardGridPane.setDisable(true); 
		//Pick for Me Button
		Button pickForMeButton = new Button("Pick For Me!");
		pickForMeButton.setStyle("-fx-background-color: #4D8BFF");
		pickForMeButton.setPadding(new Insets(15,40,15,40));
		//Make button pick random nums
		pickForMeButton.setOnAction(e->pickBetCardForMe(betCardGridPane));
		//Disable until spot count is chosen
		pickForMeButton.setDisable(true);

		//Spots Label - Tells user to pick how many spots to play
		Label spotsLabel = new Label("Pick how many Spots you would like to play! ");
		//HBox Will hold buttons horizontally
		HBox spotsButtonsHBox = new HBox();
		spotsButtonsHBox.setAlignment(Pos.CENTER);
		spotsButtonsHBox.setSpacing(10);
		//Spots Buttons - Put in a list since they all do pretty much the same thing
		ToggleButton spotButtonList[] = {new ToggleButton("1"), new ToggleButton("4"), new ToggleButton("8"), new ToggleButton("10")};
		for (ToggleButton spotButton : spotButtonList) {
			spotsButtonsHBox.getChildren().add(spotButton); //Add the button to HBox
			spotButton.setStyle("-fx-background-color: #e87564");
			spotButton.setPadding(new Insets(5,30,5,30));
			//Pressing these buttons sets spotCount to the intended Number
			spotButton.setOnAction(e->{
				if (chosenSpotButton != spotButton) { //No need to repeatedly set if it's already set
					//Make the pressed button stand out
					spotButton.setStyle("-fx-background-color: #e24028");
					//make sure any previous pressed are returned to normal
					if (chosenSpotButton != null)
						chosenSpotButton.setStyle("-fx-background-color: #e87564");
					chosenSpotButton = spotButton; //update chosen spot button
					chosenSpotCount = Integer.parseInt(spotButton.getText());
					currentSpotCount = 0;
					//DEBUGGING:
					//System.out.println("Spot button was pressed! chosenSpotCount is now " + chosenSpotCount);
					//Now that spots are chosen, enable grid!
					betCardGridPane.setDisable(false);
					//Enable random pick too
					pickForMeButton.setDisable(false);
					//DONE: Make it so picking a spotcount clears the gridpane selections
					resetGrid(betCardGridPane);
					//pickforme should also clear gridpane. make it a function
					checkIfReadyToBegin(beginDrawButton);
				}
			});
		}

		//Drawings Label
		Label drawLabel = new Label("Pick how many Drawings you would like to play!");
		//Hbox will hold draw buttons horizontally
		HBox drawButtonsHBox = new HBox();
		drawButtonsHBox.setAlignment(Pos.CENTER);
		drawButtonsHBox.setSpacing(10);
		//make our buttons in a list since their behavior is nearly identical
		ToggleButton drawButtonList[] = {new ToggleButton("1"), new ToggleButton("2"), new ToggleButton("3"), new ToggleButton("4")};
		for (ToggleButton drawButton : drawButtonList) {
			//Add the button to the hbox
			drawButtonsHBox.getChildren().add(drawButton);
			//Color it appropriately
			drawButton.setStyle("-fx-background-color: #73e89a");
			drawButton.setPadding(new Insets(5,30,5,30));
			//Upon pressing one of these buttons, chosenDrawCount should change
			drawButton.setOnAction(e->{
				if (drawButton != chosenDrawButton) { //Make sure a new button is being pressed
					//Make pressed button stand out
					drawButton.setStyle("-fx-background-color: #0dd34f");
					//make sure any previous pressed are returned to normal color
					if (chosenDrawButton != null)
						chosenDrawButton.setStyle("-fx-background-color: #73e89a");
					chosenDrawButton = drawButton; //update chosen draw button
					chosenDrawCount = Integer.parseInt(drawButton.getText());
					//DEBUGGING:
					//System.out.println("Draw button was pressed! chosenDrawCount is now " + chosenDrawCount);
					checkIfReadyToBegin(beginDrawButton);
				}
			});

		}

		//Begin Draw Button - Needs to be high up to pass to other functions
		beginDrawButton.setOnAction(e->beginDrawingSequence(givenStage));
		beginDrawButton.setDisable(true); //Disable until spotcount and drawcount are chosen
		beginDrawButton.setStyle("-fx-background-color: #4D8BFF");
		beginDrawButton.setPadding(new Insets(15,40,15,40));

		//Put it all together and send back gameplay scene
		VBox gameplayBelowMenuVbox = new VBox(gameplayImageView, spotsLabel, spotsButtonsHBox, drawLabel, drawButtonsHBox, betCardGridPane, pickForMeButton, beginDrawButton);
		gameplayBelowMenuVbox.setAlignment(Pos.CENTER);
		gameplayBelowMenuVbox.setSpacing(10);
		VBox gameplayVBox = new VBox(gameplayMenuBar, gameplayBelowMenuVbox);
		gameplayVBox.setStyle("-fx-background-color: #C4D9FF; -fx-border-color: #4D8BFF; -fx-border-width: 8;");
		
		newLookLabel.setOnMouseClicked(e->pickNewLook(gameplayImageView, gameplayVBox, gameplayMenuBar, beginDrawButton));

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

	private void pickNewLook(ImageView titleImage, VBox background, MenuBar topBar, Button beginButton) {
		//DONE: Implement New Look Menu Option
		background.setStyle("-fx-background-color: #FFE0C4; -fx-border-color: #FFA34D;");
		topBar.setStyle("-fx-background-color: #FFA34D;");
		beginButton.setStyle("-fx-background-color: #FFA34D;");
		titleImage.setImage(new Image("file:./src/main/resources/gameplay_title_alt.png", true));

	}

	//DONE: Randomized selection of bet card
	private void pickBetCardForMe(GridPane givenGridPane) {
		//should reset grid before picking
		resetGrid(givenGridPane);
		//Create a Random Object to create numbers
		Random generatorRandom = new Random();
		//Pick chosenSpotCount amount of random numbers
		for (int i = 0; i < chosenSpotCount; i++) {
			int randomSpot = generatorRandom.nextInt(79) + 1; //Plus 1 since random starts at 0
			ToggleButton randomSpotButton = (ToggleButton)givenGridPane.getChildren().get(randomSpot);
			//Check if this is a repeat
			if (spotsSet.contains(randomSpotButton)) {
				//DEBUGGING:
				//System.out.println("DUPLICATE RANDOM GIVEN! Trying for another number.");
				i--;//Make sure we still run enough times even if there's a duplicate
				continue;//Don't run the rest of the code, start a new iteration of the loop
			}
			//Set button as selected
			randomSpotButton.setSelected(true);
			//Show it has been chosen visibly
			randomSpotButton.setStyle("-fx-background-color: " + ToggleButton.highlightedColor);
			//increment currentSpotCount
			currentSpotCount += 1;
			//Add the button to our set
			spotsSet.add(randomSpotButton);
		}
		checkIfReadyToBegin(beginDrawButton);
	}

	private void resetGrid(GridPane givenGridPane) {
		if (spotsSet.isEmpty())
			return; //No spots have been selected, there's no need to reset anything
		//DEBUGGING:
		//System.out.println("resetGrid was called successfully");
		for (ToggleButton spotButton : spotsSet) {
			//We need to reset button color
			spotButton.setStyle("-fx-background-color: " + ToggleButton.normalColor);
			//We need to set button back to unpressed
			spotButton.setSelected(false);
		}
		//We need to clear out the spotSet
		spotsSet.clear();
		//We need to reset the currentSpotCount
		currentSpotCount = 0;
	}

	private void checkIfReadyToBegin(Button beginButton) {
		if (chosenSpotCount == currentSpotCount && chosenDrawCount != 0)
			beginButton.setDisable(false);
		else
			beginButton.setDisable(true);
	}

	private Scene createDrawingScene(Stage givenStage) {
		seedRandomList();

		ImageView moneyBagLeft = new ImageView(new Image("file:./src/main/resources/money_bag_logo.png", true));
		ImageView drawingTitleImage = new ImageView(new Image("file:./src/main/resources/drawing_title.png", true));
		ImageView moneyBagRight = new ImageView(new Image("file:./src/main/resources/money_bag_logo.png", true));
		HBox topImagesHBox = new HBox(moneyBagLeft, drawingTitleImage, moneyBagRight);
		topImagesHBox.setAlignment(Pos.CENTER);

		//Label for current keno number being shown
		currentNumLabel.setFont(Font.loadFont("file:./src/main/resources/chinese rocks rg.otf", 85));
		currentNumLabel.setStyle("-fx-text-fill: #448D5A");
		//label for how much has been won in total
		Label wonInTotalLabel = new Label("Won in total: Wait til first drawing finishes...");
		//button for next drawing should be off by default
		nextdrawingButton.setDisable(true);
		nextdrawingButton.setStyle("-fx-background-color: #448D5A;");
		nextdrawingButton.setPadding(new Insets(30,60,30,60));
		//if currentdrawingcount == chosendrawingcount then disable
		//nextdrawingbutton and enable new bet button and exit button
		Button newBetButton = new Button("New Bet");
		Button exitButton = new Button("Exit");
		newBetButton.setStyle("-fx-background-color: #448D5A;");
		newBetButton.setPadding(new Insets(15,40,15,40));
		exitButton.setStyle("-fx-background-color: #8d4444;");
		exitButton.setPadding(new Insets(15,40,15,40));
		//Disable these by default
		newBetButton.setDisable(true);
		newBetButton.setOnAction(e->{
			resetGrid(betCardGridPane);
			givenStage.setScene(gameplayScene);
			isNewDrawingSession = true;
			beginDrawButton.setDisable(true);//avoid user going back and starting withotu selecting something
		});
		exitButton.setDisable(true);
		exitButton.setOnAction(e->givenStage.close()); //Closes window
		//Put them next to each other
		HBox endHBox = new HBox(newBetButton, exitButton);
		endHBox.setAlignment(Pos.CENTER);
		endHBox.setSpacing(20);

		pauseList[0].setOnFinished(e->{
			///When the pause is over
			//remove the already shown number from list and save it to use
			updateDraw();
			pauseList[1].play();
		});
		pauseList[1].setOnFinished(e->{
			updateDraw();
			pauseList[2].play();
		});
		pauseList[2].setOnFinished(e->{
			updateDraw();
			pauseList[3].play();
		});
		pauseList[3].setOnFinished(e->{
			updateDraw();
			pauseList[4].play();
		});
		pauseList[4].setOnFinished(e->{
			updateDraw();
			pauseList[5].play();
		});
		pauseList[5].setOnFinished(e->{
			updateDraw();
			pauseList[6].play();
		});
		pauseList[6].setOnFinished(e->{
			updateDraw();
			pauseList[7].play();
		});
		pauseList[7].setOnFinished(e->{
			updateDraw();
			pauseList[8].play();
		});
		pauseList[8].setOnFinished(e->{
			updateDraw();
			pauseList[9].play();
		});
		pauseList[9].setOnFinished(e->{
			updateDraw();
			pauseList[10].play();
		});
		pauseList[10].setOnFinished(e->{
			updateDraw();
			pauseList[11].play();
		});
		pauseList[11].setOnFinished(e->{
			updateDraw();
			pauseList[12].play();
		});
		pauseList[12].setOnFinished(e->{
			updateDraw();
			pauseList[13].play();
		});
		pauseList[13].setOnFinished(e->{
			updateDraw();
			pauseList[14].play();
		});
		pauseList[14].setOnFinished(e->{
			updateDraw();
			pauseList[15].play();
		});
		pauseList[15].setOnFinished(e->{
			updateDraw();
			pauseList[16].play();
		});
		pauseList[16].setOnFinished(e->{
			updateDraw();
			pauseList[17].play();
		});
		pauseList[17].setOnFinished(e->{
			updateDraw();
			pauseList[18].play();
		});
		pauseList[18].setOnFinished(e->{
			updateDraw();
			pauseList[19].play();
		});
		pauseList[19].setOnFinished(e->{
			updateDraw();
			if (currentDrawCount < chosenDrawCount) {
				nextdrawingButton.setDisable(false); //If more drawings are going to happen, turn on button
				newBetButton.setDisable(true);
				exitButton.setDisable(true);
			}
			else {
				//Else we're done drawing, time to enable end buttons
				newBetButton.setDisable(false);
				exitButton.setDisable(false);
			}
			//show how much was won this round
			int roundCashWon = calculateRoundWinnings();
			wonThisRoundLabel.setText("Won this round: $" + roundCashWon);
			//Show how much has been won in total
			totalWon += roundCashWon;
			wonInTotalLabel.setText("Won in total: $" + totalWon);
		});

		nextdrawingButton.setOnAction(e->{
			//Now first drawing is finished. If they picked more drawings try again
			if (currentDrawCount < chosenDrawCount) {
				beginDrawingSequence(givenStage);
			}
		});

		// PauseTransitionList.PauseTransitionNode traversalNode = pauseTransitionList.head;
		// while (traversalNode != null)
		// {
		// 	PauseTransition p = traversalNode.currentP;
		// 	PauseTransitionList.PauseTransitionNode nextP = traversalNode.next;
		// 	p.setOnFinished(e->{
		// 		//When the pause is over
		// 		//remove the already shown number from list and save it to use
		// 		int currentChosenNum = drawTimeList.remove(0);
		// 		//Display the next number
		// 		currentNumLabel.setText("" + currentChosenNum);
		// 		//Display all numbers revealed thus far
		// 		allNumsLabel.setText(allNumsLabel.getText() + currentChosenNum + " ");
		// 		//Display new matchings
		// 		for (ToggleButton tB : spotsSet) {
		// 			if (Integer.parseInt(tB.getText()) == currentChosenNum) {
		// 				matchedList.add(currentChosenNum);
		// 			}
		// 		}
		// 		String matchesString = "";
		// 		for (Integer match : matchedList) {
		// 			matchesString += match + " ";
		// 		}
		// 		matchedNumsLabel.setText(matchesString);
		// 		//Display how much has been won this drawing
		// 		//Display total won
		// 		if (nextP != null) {
		// 			nextP.currentP.play();
		// 		}

		// 	});

		// 	traversalNode = traversalNode.next;
		// }

		VBox drawingVBox = new VBox(topImagesHBox, drawRoundLabel, currentNumLabel, chosenSpotsLabel, allNumsLabel, matchedNumsLabel, wonThisRoundLabel, wonInTotalLabel, nextdrawingButton, endHBox);
		drawingVBox.setAlignment(Pos.CENTER);
		drawingVBox.setStyle("-fx-background-color: #C2FFD4; -fx-border-color: #448D5A; -fx-border-width: 8");
		drawingVBox.setSpacing(10);
		Scene drawingScene = new Scene(drawingVBox, 700, 700);
		return drawingScene;
	}

	private void beginDrawingSequence(Stage givenStage) {
		if (isNewDrawingSession) {
			currentDrawCount = 0;
		}
		givenStage.setScene(drawingScene);
		clearDrawingBoard();
		pauseList[0].play();
		currentDrawCount += 1;
		// pauseTransitionList.head.currentP.play();
	}
	private void updateDraw() {
		int currentChosenNum = drawTimeList.remove(0);
		//System.out.println("Just removed " + currentChosenNum);
		//Show what draw round this is out of how many
		drawRoundLabel.setText("Drawing "+ currentDrawCount + " of " + chosenDrawCount);
		//Display the next number
		currentNumLabel.setText("" + currentChosenNum);
		//Display previously chosen spots
		String allchosenSpotsString = "";
		for (ToggleButton tB : spotsSet) {
			allchosenSpotsString += tB.getText() + " ";
		}
		chosenSpotsLabel.setText("Your chosen numbers were: " + allchosenSpotsString);
		//Display all numbers revealed thus far
		allNumsLabel.setText(allNumsLabel.getText() + currentChosenNum + " ");
		//Display new matchings
		for (ToggleButton tB : spotsSet) {
			if (Integer.parseInt(tB.getText()) == currentChosenNum) {
				matchedList.add(currentChosenNum);
			}
		}
		matchedNumsLabel.setText(matchedList.size() + " Matches: " + matchedList);
	}

	private void seedRandomList() {
		Random r = new Random();
		for (int i = 0; i < 20; i++) {
			int newNum = r.nextInt(79) + 1;
			if (drawTimeList.contains(newNum)) {
				i--;
				continue;
			}
			drawTimeList.add(newNum);
		}
		//we now have 20 random numbers in drawTimeSet
	}

	private void clearDrawingBoard() {
		chosenSpotsLabel.setText("Your chosen spots were: ");
		allNumsLabel.setText("Rolled in this drawing: ");
		matchedNumsLabel.setText(" Matches: ");
		matchedList.clear();
		wonThisRoundLabel.setText("Won this round: Wait til end of Drawing...");
		nextdrawingButton.setDisable(true);
		isNewDrawingSession = false;
		seedRandomList();
	}

	private int calculateRoundWinnings() {
		int matches = matchedList.size();
		switch (chosenSpotCount) {
			case 1: {
				if (matches == 1)
					return 2;
				break;
			}
			case 4: {
				if (matches == 2)
					return 1;
				else if (matches == 3)
					return 5;
				else if (matches == 4)
					return 75;
				break;
			}
			case 8: {
				if (matches == 4)
					return 2;
				if (matches == 5)
					return 12;
				if (matches == 6)
					return 50;
				if (matches == 7)
					return 750;
				if (matches == 8)
					return 10000;
				break;
			}
			case 10: {
				if (matches == 0)
					return 5;
				if (matches == 5)
					return 2;
				if (matches == 6)
					return 15;
				if (matches == 7)
					return 40;
				if (matches == 8)
					return 450;
				if (matches == 9)
					return 4250;
				if (matches == 10)
					return 100000;
				break;
			}
		}
		return 0;
	}

}
